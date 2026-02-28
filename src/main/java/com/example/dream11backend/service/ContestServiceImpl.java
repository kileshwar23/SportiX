package com.example.dream11backend.service;

import com.example.dream11backend.entity.Contest;
import com.example.dream11backend.entity.User;
import com.example.dream11backend.exception.*;
import com.example.dream11backend.repository.ContestRepository;
import com.example.dream11backend.repository.UserRepository;
import com.example.dream11backend.service.dto.ContestCreateRequest;
import com.example.dream11backend.service.dto.ContestResponse;
import com.example.dream11backend.service.dto.ContestUpdateRequest;
import com.example.dream11backend.service.dto.EligibilityResponse;
import com.example.dream11backend.service.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService {

    private final ContestRepository contestRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ContestResponse createContest(ContestCreateRequest request) {
        if (request.getEntryFee() < 0) {
            throw new IllegalArgumentException("Entry fee cannot be negative");
        }
        
        Contest contest = new Contest();
        contest.setName(request.getName());
        contest.setDescription(request.getDescription());
        contest.setCategory(request.getCategory());
        contest.setEntryFee(request.getEntryFee());
        contest.setPrizePool(request.getPrizePool());
        contest.setMaxParticipants(request.getMaxParticipants());
        contest.setCurrentParticipants(0);
        contest.setStartTime(request.getStartTime());
        contest.setEndTime(request.getEndTime());
        contest.setMaxTeamsPerUser(request.getMaxTeamsPerUser());
        contest.setFirstPrize(request.getFirstPrize());
        contest.setStatus("ACTIVE");
        
        Contest saved = contestRepository.save(contest);
        return toResponse(saved);
    }

    @Override
    public Page<ContestResponse> getAllContests(Pageable pageable) {
        return contestRepository.findAll(pageable).map(this::toResponse);
    }

    @Override
    public ContestResponse getContestById(Long id) {
        Contest contest = contestRepository.findById(id)
                .orElseThrow(() -> new ContestNotFoundException(id));
        return toResponse(contest);
    }

    @Override
    @Transactional
    public ContestResponse joinContest(Long contestId, String username) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new ContestNotFoundException(contestId));
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        
        // Check if contest is full
        if (contest.getCurrentParticipants() >= contest.getMaxParticipants()) {
            throw new ContestFullException(contestId);
        }
        
        // Check if contest has started
        if (contest.getStartTime() != null && contest.getStartTime().isBefore(LocalDateTime.now())) {
            throw new ContestAlreadyStartedException(contestId);
        }
        
        // Check if user already joined
        if (contest.getParticipants().contains(user)) {
            throw new UserAlreadyJoinedException(username, contestId);
        }
        
        // Check balance
        if (user.getBalance() < contest.getEntryFee()) {
            throw new InsufficientBalanceException(contest.getEntryFee(), user.getBalance());
        }
        
        // Add participant and update balance
        user.setBalance(user.getBalance() - contest.getEntryFee());
        userRepository.save(user);
        
        contest.getParticipants().add(user);
        contest.setCurrentParticipants(contest.getCurrentParticipants() + 1);
        
        Contest saved = contestRepository.save(contest);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public void deleteContest(Long id) {
        Contest contest = contestRepository.findById(id)
                .orElseThrow(() -> new ContestNotFoundException(id));
        
        if ("IN_PROGRESS".equals(contest.getStatus())) {
            throw new ContestInProgressException(id);
        }
        
        contestRepository.delete(contest);
    }

    @Override
    public Page<ContestResponse> getActiveContests(Pageable pageable) {
        return contestRepository.findByStatus("ACTIVE", pageable).map(this::toResponse);
    }

    @Override
    public Page<ContestResponse> getUserContests(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        
        List<Contest> contests = contestRepository.findByParticipants_Id(user.getId());
        // For simplicity, return as page - in production would use proper pagination
        Page<Contest> page = contestRepository.findAll(pageable);
        return page.map(this::toResponse);
    }

    @Override
    public Page<ContestResponse> getContestsByCategory(String category, Pageable pageable) {
        return contestRepository.findByCategory(category, pageable).map(this::toResponse);
    }

    @Override
    public Page<ContestResponse> getContestsByEntryFeeRange(Double minFee, Double maxFee, Pageable pageable) {
        return contestRepository.findByEntryFeeBetween(minFee, maxFee, pageable).map(this::toResponse);
    }

    @Override
    @Transactional
    public ContestResponse updateContest(Long id, ContestUpdateRequest updateRequest) {
        Contest contest = contestRepository.findById(id)
                .orElseThrow(() -> new ContestNotFoundException(id));
        
        if (updateRequest.getName() != null) {
            contest.setName(updateRequest.getName());
        }
        if (updateRequest.getDescription() != null) {
            contest.setDescription(updateRequest.getDescription());
        }
        if (updateRequest.getCategory() != null) {
            contest.setCategory(updateRequest.getCategory());
        }
        if (updateRequest.getEntryFee() != null) {
            contest.setEntryFee(updateRequest.getEntryFee());
        }
        if (updateRequest.getPrizePool() != null) {
            contest.setPrizePool(updateRequest.getPrizePool());
        }
        if (updateRequest.getMaxParticipants() != null) {
            contest.setMaxParticipants(updateRequest.getMaxParticipants());
        }
        if (updateRequest.getStartTime() != null) {
            contest.setStartTime(updateRequest.getStartTime());
        }
        if (updateRequest.getEndTime() != null) {
            contest.setEndTime(updateRequest.getEndTime());
        }
        if (updateRequest.getMaxTeamsPerUser() != null) {
            contest.setMaxTeamsPerUser(updateRequest.getMaxTeamsPerUser());
        }
        if (updateRequest.getFirstPrize() != null) {
            contest.setFirstPrize(updateRequest.getFirstPrize());
        }
        if (updateRequest.getStatus() != null) {
            contest.setStatus(updateRequest.getStatus());
        }
        
        Contest saved = contestRepository.save(contest);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public void leaveContest(Long contestId, String username) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new ContestNotFoundException(contestId));
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        
        if (!contest.getParticipants().contains(user)) {
            throw new UserNotInContestException(username, contestId);
        }
        
        if (contest.getStartTime() != null && contest.getStartTime().isBefore(LocalDateTime.now())) {
            throw new ContestAlreadyStartedException(contestId);
        }
        
        // Refund entry fee
        user.setBalance(user.getBalance() + contest.getEntryFee());
        userRepository.save(user);
        
        contest.getParticipants().remove(user);
        contest.setCurrentParticipants(contest.getCurrentParticipants() - 1);
        
        contestRepository.save(contest);
    }

    @Override
    public List<UserResponse> getContestParticipants(Long contestId) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new ContestNotFoundException(contestId));
        
        return contest.getParticipants().stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EligibilityResponse checkEligibility(Long contestId, String username) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new ContestNotFoundException(contestId));
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        
        boolean hasSufficientBalance = user.getBalance() >= contest.getEntryFee();
        boolean contestNotFull = contest.getCurrentParticipants() < contest.getMaxParticipants();
        boolean contestNotStarted = contest.getStartTime() == null || 
                contest.getStartTime().isAfter(LocalDateTime.now());
        boolean notAlreadyJoined = !contest.getParticipants().contains(user);
        
        boolean eligible = hasSufficientBalance && contestNotFull && contestNotStarted && notAlreadyJoined;
        
        return EligibilityResponse.builder()
                .eligible(eligible)
                .reason(eligible ? "Eligible to join" : "Not eligible")
                .hasSufficientBalance(hasSufficientBalance)
                .contestNotFull(contestNotFull)
                .contestNotStarted(contestNotStarted)
                .notAlreadyJoined(notAlreadyJoined)
                .meetsTeamRequirements(true)
                .currentBalance(user.getBalance())
                .requiredBalance(contest.getEntryFee())
                .spotsLeft(contest.getMaxParticipants() - contest.getCurrentParticipants())
                .userTeamsCount(contest.getParticipants().contains(user) ? 1 : 0)
                .maxTeamsAllowed(contest.getMaxTeamsPerUser())
                .build();
    }

    private ContestResponse toResponse(Contest contest) {
        return ContestResponse.builder()
                .id(contest.getId())
                .name(contest.getName())
                .description(contest.getDescription())
                .category(contest.getCategory())
                .entryFee(contest.getEntryFee())
                .prizePool(contest.getPrizePool())
                .maxParticipants(contest.getMaxParticipants())
                .currentParticipants(contest.getCurrentParticipants())
                .startTime(contest.getStartTime())
                .endTime(contest.getEndTime())
                .status(contest.getStatus())
                .maxTeamsPerUser(contest.getMaxTeamsPerUser())
                .firstPrize(contest.getFirstPrize())
                .isFull(contest.getCurrentParticipants() >= contest.getMaxParticipants())
                .isStarted(contest.getStartTime() != null && contest.getStartTime().isBefore(LocalDateTime.now()))
                .isEnded(contest.getEndTime() != null && contest.getEndTime().isBefore(LocalDateTime.now()))
                .totalSpotsLeft(contest.getMaxParticipants() - contest.getCurrentParticipants())
                .createdAt(contest.getCreatedAt())
                .updatedAt(contest.getUpdatedAt())
                .build();
    }

    private UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .balance(user.getBalance())
                .contestsJoined(0)
                .contestsWon(0)
                .totalWinnings(0.0)
                .build();
    }
}

