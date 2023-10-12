package com.logicgate.payrollmanagement.staticdata;

public enum Relationship {
    FATHER("Father"),
    MOTHER("Mother"),
    SIBLING("Sibling"),
    SPOUSE("Spouse"),
    COUSIN("Cousin"),
    OTHERS("Other");

    private final String relationship;

    Relationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRelationship() {
        return relationship;
    }
}
