package com.example.dream11backend.service;

import com.example.dream11backend.entity.Match;
import com.example.dream11backend.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Override
    @Transactional
    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    @Override
    @Transactional
    public Match updateMatch(Long id, Match updatedMatch) {
        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));
        
        existingMatch.setTeam1(updatedMatch.getTeam1());
        existingMatch.setTeam2(updatedMatch.getTeam2());
        existingMatch.setMatchDate(updatedMatch.getMatchDate());
        existingMatch.setVenue(updatedMatch.getVenue());
        existingMatch.setStatus(updatedMatch.getStatus());
        
        return matchRepository.save(existingMatch);
    }

    @Override
    @Transactional
    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match not found with id: " + id);
        }
        matchRepository.deleteById(id);
    }

    @Override
    public List<Match> getUpcomingMatches() {
        return matchRepository.findByStatus("SCHEDULED");
    }

    @Override
    public List<Match> getLiveMatches() {
        return matchRepository.findByStatus("LIVE");
    }
}

