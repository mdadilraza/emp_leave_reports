package com.eidiko.emp_leave_mngt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeavesTakenDetails {

    private String leaveTypeName ;
    private int count ;
}
