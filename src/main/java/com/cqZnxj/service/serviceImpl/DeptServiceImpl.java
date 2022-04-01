package com.cqZnxj.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptDao;

	@Override
	public List<Dept> queryTreeList() {
		// TODO Auto-generated method stub
		return deptDao.queryTreeList();
	}
}
