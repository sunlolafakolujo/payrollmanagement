package com.logicgate.payrollmanagement.staticdata;

public enum Title {
    MR("Mr."),
    MRS("Mrs."),
    MASTER("Master"),
    MISS("Miss"),
    MS("Ms.");

    private final String title;

    Title(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
