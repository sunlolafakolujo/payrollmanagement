package com.logicgate.payrollmanagement.staticdata;

public enum ApprovalStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private final String approval;

    ApprovalStatus(String approval) {
        this.approval = approval;
    }

    public String getApproval() {
        return approval;
    }
}
