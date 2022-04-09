package com.cqZnxj.service.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqZnxj.dao.*;
import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;

@Service
public class PatrolLineServiceImpl implements PatrolLineService {

	@Autowired
	private PatrolLineMapper patrolLineDao;

	@Override
	public int add(PatrolLine pl) {
		// TODO Auto-generated method stub
		return patrolLineDao.add(pl);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=patrolLineDao.deleteByIds(idList);
		return count;
	}

	@Override
	public int edit(PatrolLine pl) {
		// TODO Auto-generated method stub
		return patrolLineDao.edit(pl);
	}

	@Override
	public int queryForInt(String name, String createTimeStart, String createTimeEnd) {
		// TODO Auto-generated method stub
		return patrolLineDao.queryForInt(name, createTimeStart, createTimeEnd);
	}

	@Override
	public List<PatrolLine> queryList(String name, String createTimeStart, String createTimeEnd, int page, int rows,
			String sort, String order) {
		// TODO Auto-generated method stub
		return patrolLineDao.queryList(name, createTimeStart, createTimeEnd, (page-1)*rows, rows, sort, order);
	}

	@Override
	public List<PatrolLine> queryCBBList() {
		// TODO Auto-generated method stub
		return patrolLineDao.queryCBBList();
	}
}
