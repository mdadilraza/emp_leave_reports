package com.eidiko.emp_leave_mngt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLeavesResults {

    private long id ;
    private String name ;
    private int totalLeavesEligible=10;
    private int leavesTaken ;
    private int balanceLeave;
    private List<LeavesTakenDetails> leavesTakenDetails;

}
