package com.example.dream11backend.controller;

import com.example.dream11backend.entity.Match;
import com.example.dream11backend.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("null")
@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.createMatch(match));
    }

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        return matchService.getMatchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody Match match) {
        return ResponseEntity.ok(matchService.updateMatch(id, match));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Match>> getUpcomingMatches() {
        return ResponseEntity.ok(matchService.getUpcomingMatches());
    }

    @GetMapping("/live")
    public ResponseEntity<List<Match>> getLiveMatches() {
        return ResponseEntity.ok(matchService.getLiveMatches());
    }
}
