package com.example.dream11backend.service;

import com.example.dream11backend.exception.*;
import com.example.dream11backend.service.dto.ContestCreateRequest;
import com.example.dream11backend.service.dto.ContestResponse;
import com.example.dream11backend.service.dto.ContestUpdateRequest;
import com.example.dream11backend.service.dto.EligibilityResponse;
import com.example.dream11backend.service.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContestService {
    ContestResponse createContest(ContestCreateRequest request);
    Page<ContestResponse> getAllContests(Pageable pageable);
    ContestResponse getContestById(Long id) throws ContestNotFoundException;
    ContestResponse joinContest(Long contestId, String username) throws 
        ContestNotFoundException, ContestFullException, 
        ContestAlreadyStartedException, UserAlreadyJoinedException, 
        InsufficientBalanceException;
    void deleteContest(Long id) throws ContestNotFoundException, ContestInProgressException;
    Page<ContestResponse> getActiveContests(Pageable pageable);
    Page<ContestResponse> getUserContests(String username, Pageable pageable);
    Page<ContestResponse> getContestsByCategory(String category, Pageable pageable);
    Page<ContestResponse> getContestsByEntryFeeRange(Double minFee, Double maxFee, Pageable pageable);
    ContestResponse updateContest(Long id, ContestUpdateRequest updateRequest) throws ContestNotFoundException;
    void leaveContest(Long contestId, String username) throws 
        ContestNotFoundException, UserNotInContestException, ContestAlreadyStartedException;
    List<UserResponse> getContestParticipants(Long contestId) throws ContestNotFoundException;
    EligibilityResponse checkEligibility(Long contestId, String username) throws ContestNotFoundException;
}

