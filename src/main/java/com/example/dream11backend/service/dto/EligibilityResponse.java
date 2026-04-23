package com.example.dream11backend.service.dto;

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

    public EligibilityResponse() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final EligibilityResponse obj = new EligibilityResponse();
        public Builder eligible(Boolean v) { obj.eligible = v; return this; }
        public Builder reason(String v) { obj.reason = v; return this; }
        public Builder hasSufficientBalance(Boolean v) { obj.hasSufficientBalance = v; return this; }
        public Builder contestNotFull(Boolean v) { obj.contestNotFull = v; return this; }
        public Builder contestNotStarted(Boolean v) { obj.contestNotStarted = v; return this; }
        public Builder notAlreadyJoined(Boolean v) { obj.notAlreadyJoined = v; return this; }
        public Builder meetsTeamRequirements(Boolean v) { obj.meetsTeamRequirements = v; return this; }
        public Builder currentBalance(Double v) { obj.currentBalance = v; return this; }
        public Builder requiredBalance(Double v) { obj.requiredBalance = v; return this; }
        public Builder spotsLeft(Integer v) { obj.spotsLeft = v; return this; }
        public Builder userTeamsCount(Integer v) { obj.userTeamsCount = v; return this; }
        public Builder maxTeamsAllowed(Integer v) { obj.maxTeamsAllowed = v; return this; }
        public EligibilityResponse build() { return obj; }
    }

    public Boolean getEligible() { return eligible; }
    public void setEligible(Boolean eligible) { this.eligible = eligible; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public Boolean getHasSufficientBalance() { return hasSufficientBalance; }
    public void setHasSufficientBalance(Boolean hasSufficientBalance) { this.hasSufficientBalance = hasSufficientBalance; }
    public Boolean getContestNotFull() { return contestNotFull; }
    public void setContestNotFull(Boolean contestNotFull) { this.contestNotFull = contestNotFull; }
    public Boolean getContestNotStarted() { return contestNotStarted; }
    public void setContestNotStarted(Boolean contestNotStarted) { this.contestNotStarted = contestNotStarted; }
    public Boolean getNotAlreadyJoined() { return notAlreadyJoined; }
    public void setNotAlreadyJoined(Boolean notAlreadyJoined) { this.notAlreadyJoined = notAlreadyJoined; }
    public Boolean getMeetsTeamRequirements() { return meetsTeamRequirements; }
    public void setMeetsTeamRequirements(Boolean meetsTeamRequirements) { this.meetsTeamRequirements = meetsTeamRequirements; }
    public Double getCurrentBalance() { return currentBalance; }
    public void setCurrentBalance(Double currentBalance) { this.currentBalance = currentBalance; }
    public Double getRequiredBalance() { return requiredBalance; }
    public void setRequiredBalance(Double requiredBalance) { this.requiredBalance = requiredBalance; }
    public Integer getSpotsLeft() { return spotsLeft; }
    public void setSpotsLeft(Integer spotsLeft) { this.spotsLeft = spotsLeft; }
    public Integer getUserTeamsCount() { return userTeamsCount; }
    public void setUserTeamsCount(Integer userTeamsCount) { this.userTeamsCount = userTeamsCount; }
    public Integer getMaxTeamsAllowed() { return maxTeamsAllowed; }
    public void setMaxTeamsAllowed(Integer maxTeamsAllowed) { this.maxTeamsAllowed = maxTeamsAllowed; }
}
