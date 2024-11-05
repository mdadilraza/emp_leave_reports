package com.eidiko.emp_leave_mngt.repository;
import com.eidiko.emp_leave_mngt.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LeaveTypeRepository extends JpaRepository<LeaveType , Long> {


}
