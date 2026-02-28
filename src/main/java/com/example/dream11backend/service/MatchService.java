package com.example.dream11backend.service;

import com.example.dream11backend.entity.Match;
import java.util.List;
import java.util.Optional;

public interface MatchService {
    Match createMatch(Match match);
    List<Match> getAllMatches();
    Optional<Match> getMatchById(Long id);
    Match updateMatch(Long id, Match updatedMatch);
    void deleteMatch(Long id);
    List<Match> getUpcomingMatches();
    List<Match> getLiveMatches();
}

