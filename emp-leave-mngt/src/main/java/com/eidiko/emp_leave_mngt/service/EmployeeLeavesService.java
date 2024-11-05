package com.eidiko.emp_leave_mngt.service;

import com.eidiko.emp_leave_mngt.dto.EmployeeLeavesResults;
import com.eidiko.emp_leave_mngt.entity.EmployeeLeaves;

import java.util.List;

public interface EmployeeLeavesService {

    List<EmployeeLeaves> getAllEmployeeLeaves();

    EmployeeLeavesResults getLeavesResult(long empId);
}
