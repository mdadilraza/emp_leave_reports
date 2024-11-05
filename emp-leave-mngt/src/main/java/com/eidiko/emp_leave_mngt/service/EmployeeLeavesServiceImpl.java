package com.eidiko.emp_leave_mngt.service;

import com.eidiko.emp_leave_mngt.dto.EmployeeLeavesResults;
import com.eidiko.emp_leave_mngt.dto.LeavesTakenDetails;
import com.eidiko.emp_leave_mngt.entity.Employee;
import com.eidiko.emp_leave_mngt.entity.EmployeeLeaves;
import com.eidiko.emp_leave_mngt.entity.LeaveType;
import com.eidiko.emp_leave_mngt.repository.EmployeeLeavesRepository;
import com.eidiko.emp_leave_mngt.repository.EmployeeRepository;
import com.eidiko.emp_leave_mngt.repository.LeaveTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmployeeLeavesServiceImpl implements EmployeeLeavesService{

    private EmployeeLeavesRepository employeeLeavesRepository;
    private LeaveTypeRepository leaveTypeRepository;
    private EmployeeRepository employeeRepository;


    @Override
    public List<EmployeeLeaves> getAllEmployeeLeaves() {
        return employeeLeavesRepository.findAll();
    }

    @Override
    public EmployeeLeavesResults getLeavesResult(long empId) {

        List<LeaveType> leaveTypes = leaveTypeRepository.findAll();

        Map<Long, String> leaveTypeNames = new HashMap<>();
        leaveTypes.forEach(leaveType -> leaveTypeNames.
                put(leaveType.getId(),
                        leaveType.getName()));

        // Step 2: Initialize a count map for each leave type
        Map<Long, Integer> leaveTypeCounts = new HashMap<>();
        leaveTypes.forEach(leaveType -> leaveTypeCounts.
                put(leaveType.getId(), 0)); // Initialize to 0

        // Step 3: Count the leaves taken by leave type
        List<EmployeeLeaves> empLeaves = employeeLeavesRepository.
                findByEmployeeEmpId(empId);
        empLeaves.forEach(employeeLeave -> {
            Long leaveTypeId = employeeLeave.getLeaveType().getId();
            leaveTypeCounts.put(leaveTypeId, leaveTypeCounts.get(leaveTypeId) + 1);
        });

        // Step 4: Create LeavesTakenDetails list with dynamic names and counts
        List<LeavesTakenDetails> leavesTakenDetails = new ArrayList<>();
        leaveTypeCounts.forEach((leaveTypeId, count) -> {
            String leaveTypeName = leaveTypeNames.get(leaveTypeId);
            leavesTakenDetails.add(new LeavesTakenDetails(leaveTypeName, count));
        });

        // Step 5: Calculate total leave taken and eligible leave
        int eligibleLeave = new EmployeeLeavesResults().getTotalLeavesEligible();  // Fixed eligible leave
        int totalLeaveTaken = leaveTypeCounts.values().
                stream().mapToInt(Integer::intValue).
                sum();
        int remainingLeave = eligibleLeave - totalLeaveTaken;


        Employee employee = employeeRepository.findById(empId).
                orElseThrow(() -> new IllegalArgumentException("employee is not present "));

        // Step 6: Return the result with the collected data
        return new EmployeeLeavesResults(
                empId,
                empLeaves.isEmpty() ? employee.getName() : empLeaves.get(0).
                        getEmployee().getName(),
                eligibleLeave,
                totalLeaveTaken,
                remainingLeave,
                leavesTakenDetails
        );
    }




    //Hard Coded
//@Override
//public EmployeeLeavesResults getLeavesResult(long empId) {
//
//    List<EmployeeLeaves> empLeaves = employeeLeavesRepository.findByEmployeeEmpId(empId);
//    if(empLeaves.isEmpty())
//        throw new IllegalArgumentException("Employee is not Present with the given " +empId);
//
//
//    int countOfCl = 0;
//    int countOfSl = 0;
//    int countOfOl = 0;
//
//    // Count leaves based on leave type
//    for (EmployeeLeaves employeeLeave : empLeaves) {
//        Long leaveTypeId = employeeLeave.getLeaveType().getId();
//
//        switch (leaveTypeId.intValue()) {
//            case 1 -> countOfCl++;
//            case 2 -> countOfSl++;
//            case 3 -> countOfOl++;
//            default -> throw new IllegalArgumentException("Unknown leave type ID: " + leaveTypeId);
//        }
//    }
//
//
//    List<LeavesTakenDetails> leavesTakenDetails = new ArrayList<>();
//    leavesTakenDetails.add(new LeavesTakenDetails("Casual Leave", countOfCl));
//    leavesTakenDetails.add(new LeavesTakenDetails("Sick Leave", countOfSl));
//    leavesTakenDetails.add(new LeavesTakenDetails("Optional Leave", countOfOl));
//
//    // Calculate totals
//    int eligibleLeave = new EmployeeLeavesResults().getTotalLeavesEligible();
//    int totalLeaveTaken = countOfCl + countOfSl + countOfOl;
//    int remainingLeave = eligibleLeave - totalLeaveTaken;
//
//    // Create and return the final result
//    return new EmployeeLeavesResults(
//            empId,
//            empLeaves.get(0).getEmployee().getName(),
//            eligibleLeave,
//            totalLeaveTaken,
//            remainingLeave,
//            leavesTakenDetails
//    );
//}

}
