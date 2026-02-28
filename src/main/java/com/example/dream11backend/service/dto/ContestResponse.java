package com.example.dream11backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestResponse {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Double entryFee;
    private Double prizePool;
    private Integer maxParticipants;
    private Integer currentParticipants;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private List<String> rules;
    private Integer maxTeamsPerUser;
    private Boolean isFull;
    private Boolean isStarted;
    private Boolean isEnded;
    private Integer totalSpotsLeft;
    private Double firstPrize;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Manual builder method since Lombok may not be processing
    public static ContestResponse builder() {
        return new ContestResponse();
    }
    
    public ContestResponse id(Long id) { this.id = id; return this; }
    public ContestResponse name(String name) { this.name = name; return this; }
    public ContestResponse description(String description) { this.description = description; return this; }
    public ContestResponse category(String category) { this.category = category; return this; }
    public ContestResponse entryFee(Double entryFee) { this.entryFee = entryFee; return this; }
    public ContestResponse prizePool(Double prizePool) { this.prizePool = prizePool; return this; }
    public ContestResponse maxParticipants(Integer maxParticipants) { this.maxParticipants = maxParticipants; return this; }
    public ContestResponse currentParticipants(Integer currentParticipants) { this.currentParticipants = currentParticipants; return this; }
    public ContestResponse startTime(LocalDateTime startTime) { this.startTime = startTime; return this; }
    public ContestResponse endTime(LocalDateTime endTime) { this.endTime = endTime; return this; }
    public ContestResponse status(String status) { this.status = status; return this; }
    public ContestResponse rules(List<String> rules) { this.rules = rules; return this; }
    public ContestResponse maxTeamsPerUser(Integer maxTeamsPerUser) { this.maxTeamsPerUser = maxTeamsPerUser; return this; }
    public ContestResponse isFull(Boolean isFull) { this.isFull = isFull; return this; }
    public ContestResponse isStarted(Boolean isStarted) { this.isStarted = isStarted; return this; }
    public ContestResponse isEnded(Boolean isEnded) { this.isEnded = isEnded; return this; }
    public ContestResponse totalSpotsLeft(Integer totalSpotsLeft) { this.totalSpotsLeft = totalSpotsLeft; return this; }
    public ContestResponse firstPrize(Double firstPrize) { this.firstPrize = firstPrize; return this; }
    public ContestResponse createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
    public ContestResponse updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
    
    public ContestResponse build() {
        return this;
    }
}

