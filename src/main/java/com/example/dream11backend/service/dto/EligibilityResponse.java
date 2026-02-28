package com.example.dream11backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EligibilityResponse {
    private Boolean eligible;
    private String reason;
    private Boolean hasSufficientBalance;
    private Boolean contestNotFull;
    private Boolean contestNotStarted;
    private Boolean notAlreadyJoined;
    private Boolean meetsTeamRequirements;
    private Double currentBalance;
    private Double requiredBalance;
    private Integer spotsLeft;
    private Integer userTeamsCount;
    private Integer maxTeamsAllowed;
    
    // Manual builder method since Lombok may not be processing
    public static EligibilityResponse builder() {
        return new EligibilityResponse();
    }
    
    public EligibilityResponse eligible(Boolean eligible) { this.eligible = eligible; return this; }
    public EligibilityResponse reason(String reason) { this.reason = reason; return this; }
    public EligibilityResponse hasSufficientBalance(Boolean hasSufficientBalance) { this.hasSufficientBalance = hasSufficientBalance; return this; }
    public EligibilityResponse contestNotFull(Boolean contestNotFull) { this.contestNotFull = contestNotFull; return this; }
    public EligibilityResponse contestNotStarted(Boolean contestNotStarted) { this.contestNotStarted = contestNotStarted; return this; }
    public EligibilityResponse notAlreadyJoined(Boolean notAlreadyJoined) { this.notAlreadyJoined = notAlreadyJoined; return this; }
    public EligibilityResponse meetsTeamRequirements(Boolean meetsTeamRequirements) { this.meetsTeamRequirements = meetsTeamRequirements; return this; }
    public EligibilityResponse currentBalance(Double currentBalance) { this.currentBalance = currentBalance; return this; }
    public EligibilityResponse requiredBalance(Double requiredBalance) { this.requiredBalance = requiredBalance; return this; }
    public EligibilityResponse spotsLeft(Integer spotsLeft) { this.spotsLeft = spotsLeft; return this; }
    public EligibilityResponse userTeamsCount(Integer userTeamsCount) { this.userTeamsCount = userTeamsCount; return this; }
    public EligibilityResponse maxTeamsAllowed(Integer maxTeamsAllowed) { this.maxTeamsAllowed = maxTeamsAllowed; return this; }
    
    public EligibilityResponse build() {
        return this;
    }
}

