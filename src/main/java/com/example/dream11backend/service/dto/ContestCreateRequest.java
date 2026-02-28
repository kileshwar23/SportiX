package com.example.dream11backend.service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContestCreateRequest {
    @NotBlank(message = "Contest name is required")
    @Size(min = 3, max = 100, message = "Contest name must be between 3 and 100 characters")
    private String name;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    private String category;
    
    @NotNull(message = "Entry fee is required")
    @Positive(message = "Entry fee must be positive")
    private Double entryFee;
    
    @NotNull(message = "Prize pool is required")
    @Positive(message = "Prize pool must be positive")
    private Double prizePool;
    
    @NotNull(message = "Maximum participants is required")
    @Min(value = 2, message = "Contest must allow at least 2 participants")
    @Max(value = 100000, message = "Maximum participants cannot exceed 100,000")
    private Integer maxParticipants;
    
    @Future(message = "Start time must be in the future")
    private LocalDateTime startTime;
    
    @Future(message = "End time must be in the future")
    private LocalDateTime endTime;
    
    private List<String> rules;
    
    @Min(value = 1, message = "Max teams per user must be at least 1")
    private Integer maxTeamsPerUser = 1;
    
    private Double firstPrize;
    
    // Manual getters since Lombok may not be processing annotations
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getEntryFee() { return entryFee; }
    public void setEntryFee(Double entryFee) { this.entryFee = entryFee; }
    public Double getPrizePool() { return prizePool; }
    public void setPrizePool(Double prizePool) { this.prizePool = prizePool; }
    public Integer getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(Integer maxParticipants) { this.maxParticipants = maxParticipants; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public List<String> getRules() { return rules; }
    public void setRules(List<String> rules) { this.rules = rules; }
    public Integer getMaxTeamsPerUser() { return maxTeamsPerUser; }
    public void setMaxTeamsPerUser(Integer maxTeamsPerUser) { this.maxTeamsPerUser = maxTeamsPerUser; }
    public Double getFirstPrize() { return firstPrize; }
    public void setFirstPrize(Double firstPrize) { this.firstPrize = firstPrize; }
}

