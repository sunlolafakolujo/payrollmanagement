package com.logicgate.payrollmanagement.staticdata;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SalaryType {
    MONTHLY("Monthly"),
    DAY_RATE("Day Rate");

    @Getter
    private final String salaryType;
}
