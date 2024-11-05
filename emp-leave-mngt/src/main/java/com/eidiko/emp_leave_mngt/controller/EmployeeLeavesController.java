package com.eidiko.emp_leave_mngt.controller;

import com.eidiko.emp_leave_mngt.dto.EmployeeLeavesResults;
import com.eidiko.emp_leave_mngt.entity.EmployeeLeaves;
import com.eidiko.emp_leave_mngt.repository.EmployeeLeavesRepository;
import com.eidiko.emp_leave_mngt.repository.EmployeeRepository;
import com.eidiko.emp_leave_mngt.service.EmployeeLeavesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employeeLeaves")
@AllArgsConstructor
public class EmployeeLeavesController {

    private EmployeeLeavesService employeeLeavesService;
    private EmployeeLeavesRepository employeeLeavesRepository;

    private EmployeeRepository employeeRepository;

    //REST API TO GET ALL EMPLOYEE WITH THE LEAVE DETAILS
    @GetMapping
    public ResponseEntity<List<EmployeeLeaves>> getAllEmployeeLeaves() {
      return new ResponseEntity<>(employeeLeavesService.getAllEmployeeLeaves(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public List<EmployeeLeaves> getAllLeaves(@PathVariable int id){
        return employeeLeavesRepository.findByEmployeeEmpId(id);
    }

    @GetMapping("/getLeaveResult/{empId}")

    public ResponseEntity<EmployeeLeavesResults> getLeavesResult(@PathVariable  long empId) {

        return new ResponseEntity<>(employeeLeavesService.getLeavesResult(empId) ,HttpStatus.FOUND);
    }


}
