package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.*;

public interface StaffMapper {

	List<Staff> queryCBBList(@Param("deptId") String deptId);

}
