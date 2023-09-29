package com.logicgate.payrollmanagement.leave.model;

import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.staticdata.ApprovalStatus;
import com.logicgate.payrollmanagement.staticdata.LeaveType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostEmployeeLeave {
    private String leaveRef;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer eligibleLeaveDays;
    private Integer numberOfDays;
    private ApprovalStatus approvalStatus=ApprovalStatus.PENDING;
    private String approval;
    private Employee employee;
}
