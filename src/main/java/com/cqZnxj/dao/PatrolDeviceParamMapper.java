package com.cqZnxj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqZnxj.entity.PatrolDeviceParam;

public interface PatrolDeviceParamMapper {

	int queryForInt(@Param("pdtName") String pdtName, @Param("pdName") String pdName, @Param("pdaNo") String pdaNo, @Param("name") String name, @Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd);

	List<PatrolDeviceParam> queryList(@Param("pdtName") String pdtName, @Param("pdName") String pdName, @Param("pdaNo") String pdaNo, @Param("name") String name, 
			@Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	int add(PatrolDeviceParam pdp);

	PatrolDeviceParam selectById(String id);

	int edit(PatrolDeviceParam pdp);

}
