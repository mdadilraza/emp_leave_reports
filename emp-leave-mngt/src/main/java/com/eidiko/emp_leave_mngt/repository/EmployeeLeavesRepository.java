package com.eidiko.emp_leave_mngt.repository;

import com.eidiko.emp_leave_mngt.entity.EmployeeLeaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeLeavesRepository extends JpaRepository<EmployeeLeaves , Long> {

  //  @Query("SELECT el FROM EmployeeLeaves el WHERE el.employee.empId =: empId")
    List<EmployeeLeaves> findByEmployeeEmpId( long empId);
}
