package com.example.dream11backend.controller;

import com.example.dream11backend.service.ContestService;
import com.example.dream11backend.service.dto.ContestCreateRequest;
import com.example.dream11backend.service.dto.ContestResponse;
import com.example.dream11backend.service.dto.ContestUpdateRequest;
import com.example.dream11backend.service.dto.EligibilityResponse;
import com.example.dream11backend.service.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@SuppressWarnings("null")
@RestController
@RequestMapping("/api/contests")
@RequiredArgsConstructor
public class ContestController {

    private final ContestService contestService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContestResponse> createContest(@Valid @RequestBody ContestCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contestService.createContest(request));
    }

    @GetMapping
    public ResponseEntity<Page<ContestResponse>> getAllContests(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(contestService.getAllContests(pageable));
    }

    @GetMapping("/active")
    public ResponseEntity<Page<ContestResponse>> getActiveContests(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(contestService.getActiveContests(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContestResponse> getContestById(@PathVariable Long id) {
        return ResponseEntity.ok(contestService.getContestById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContestResponse> updateContest(
            @PathVariable Long id,
            @Valid @RequestBody ContestUpdateRequest request) {
        return ResponseEntity.ok(contestService.updateContest(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteContest(@PathVariable Long id) {
        contestService.deleteContest(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<ContestResponse> joinContest(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(contestService.joinContest(id, principal.getName()));
    }

    @PostMapping("/{id}/leave")
    public ResponseEntity<Void> leaveContest(@PathVariable Long id, Principal principal) {
        contestService.leaveContest(id, principal.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<UserResponse>> getParticipants(@PathVariable Long id) {
        return ResponseEntity.ok(contestService.getContestParticipants(id));
    }

    @GetMapping("/{id}/eligibility")
    public ResponseEntity<EligibilityResponse> checkEligibility(@PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(contestService.checkEligibility(id, principal.getName()));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ContestResponse>> getByCategory(
            @PathVariable String category,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(contestService.getContestsByCategory(category, pageable));
    }

    @GetMapping("/my")
    public ResponseEntity<Page<ContestResponse>> getMyContests(
            Principal principal,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(contestService.getUserContests(principal.getName(), pageable));
    }
}
