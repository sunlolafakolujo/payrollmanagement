package com.logicgate.payrollmanagement.leave.model;

import com.logicgate.payrollmanagement.baseaudit.BaseAudit;
import com.logicgate.payrollmanagement.employee.model.Employee;
import com.logicgate.payrollmanagement.staticdata.ApprovalStatus;
import com.logicgate.payrollmanagement.staticdata.LeaveType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLeave extends BaseAudit {
    @Id
    @SequenceGenerator(name = "employee_leave_generator",
            sequenceName = "employee_leave_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "employee_leave_generator")
    private Long id;
    private String leaveRef;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer eligibleLeaveDays=22;
    private Integer numberOfDays; //throw exception if >eligibleLeaveDays

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus=ApprovalStatus.PENDING;
    private String approval;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;
}
