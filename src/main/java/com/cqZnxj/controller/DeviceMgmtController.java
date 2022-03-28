package com.cqZnxj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqZnxj.entity.*;
import com.cqZnxj.service.*;
import com.cqZnxj.util.*;

/**
 * �豸������
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/deviceMgmt")
public class DeviceMgmtController {

	@Autowired
	private PatrolDeviceService patrolDeviceService;
	@Autowired
	private PatrolDeviceTypeService patrolDeviceTypeService;
	@Autowired
	private PatrolDeviceAccountService patrolDeviceAccountService;
	public static final String MODULE_NAME="deviceMgmt";
	//http://localhost:8080/CqZnxj/deviceMgmt/type/list
	
	@RequestMapping(value="/type/new")
	public String goTypeNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/type/new";
	}

	@RequestMapping(value="/type/edit")
	public String goTypeEdit(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		PatrolDeviceType pdt=patrolDeviceTypeService.selectById(id);
		request.setAttribute("pdt", pdt);
		
		return MODULE_NAME+"/type/edit";
	}
	
	@RequestMapping(value="/type/list")
	public String goTypeList(HttpServletRequest request) {
		
		return MODULE_NAME+"/type/list";
	}

	@RequestMapping(value="/type/detail")
	public String goTypeDetail(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		PatrolDeviceType pdt=patrolDeviceTypeService.selectById(id);
		request.setAttribute("pdt", pdt);
		
		return MODULE_NAME+"/type/detail";
	}
	
	@RequestMapping(value="/device/new")
	public String goDeviceNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/device/new";
	}
	
	@RequestMapping(value="/device/edit")
	public String goDeviceEdit(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolDevice pd=patrolDeviceService.selectById(id);
		request.setAttribute("pd", pd);
		
		return MODULE_NAME+"/device/edit";
	}
	
	@RequestMapping(value="/device/list")
	public String goDeviceList(HttpServletRequest request) {
		
		return MODULE_NAME+"/device/list";
	}
	
	@RequestMapping(value="/device/detail")
	public String goDeviceDetail(HttpServletRequest request) {

		String id = request.getParameter("id");
		PatrolDevice pd=patrolDeviceService.selectById(id);
		request.setAttribute("pd", pd);
		
		return MODULE_NAME+"/device/detail";
	}
	
	@RequestMapping(value="/account/new")
	public String goAccountNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/account/new";
	}
	
	@RequestMapping(value="/account/list")
	public String goAccountList(HttpServletRequest request) {
		
		return MODULE_NAME+"/account/list";
	}
	
	@RequestMapping(value="/newType")
	@ResponseBody
	public Map<String, Object> newType(PatrolDeviceType pdt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceTypeService.add(pdt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "�����豸���ͳɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "�����豸����ʧ�ܣ�");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/checkIfExistDeviceByTypeIds",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String checkIfExistDeviceByTypeIds(String typeIds,String typeNames) {
		//TODO ��Է���Ķ�̬����ʵʱ��������
		List<PatrolDeviceType> pdtList=patrolDeviceService.checkIfExistByTypeIds(typeIds,typeNames);
		PlanResult plan=new PlanResult();
		String json;
		if(pdtList.size()>0) {
			plan.setStatus(1);
			StringBuilder msgSB=new StringBuilder();
			for (int i = 0; i < pdtList.size(); i++) {
				PatrolDeviceType pdt = pdtList.get(i);
				msgSB.append(",");
				msgSB.append(pdt.getName());
			}
			msgSB.append("���������豸������ɾ���豸");
			String msgStr = msgSB.toString();
			plan.setMsg(msgStr.substring(1, msgStr.length()));
			plan.setData(pdtList);
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(0);
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}

	@RequestMapping(value="/deleteType",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteType(String ids) {
		//TODO ��Է���Ķ�̬����ʵʱ��������
		int count=patrolDeviceTypeService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("ɾ���豸����ʧ��");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("ɾ���豸���ͳɹ�");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
	
	@RequestMapping(value="/editType")
	@ResponseBody
	public Map<String, Object> editType(PatrolDeviceType pdt) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceTypeService.edit(pdt);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "�༭�豸���ͳɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "�༭�豸����ʧ�ܣ�");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryTypeList")
	@ResponseBody
	public Map<String, Object> queryTypeList(String name,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolDeviceTypeService.queryForInt(name);
			List<PatrolDeviceType> pdtList=patrolDeviceTypeService.queryList(name, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", pdtList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/newDevice")
	@ResponseBody
	public Map<String, Object> newDevice(PatrolDevice pd) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceService.add(pd);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "�����豸�ɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "�����豸ʧ�ܣ�");
		}
		return jsonMap;
	}

	@RequestMapping(value="/deleteDevice",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteDevice(String ids) {
		//TODO ��Է���Ķ�̬����ʵʱ��������
		int count=patrolDeviceService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("ɾ���豸ʧ��");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("ɾ���豸�ɹ�");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}

	@RequestMapping(value="/editDevice")
	@ResponseBody
	public Map<String, Object> editDevice(PatrolDevice pd) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count=patrolDeviceService.edit(pd);
		if(count>0) {
			jsonMap.put("message", "ok");
			jsonMap.put("info", "�༭�豸�ɹ���");
		}
		else {
			jsonMap.put("message", "no");
			jsonMap.put("info", "�༭�豸ʧ�ܣ�");
		}
		return jsonMap;
	}
	
	@RequestMapping(value="/queryDeviceList")
	@ResponseBody
	public Map<String, Object> queryDeviceList(String name,String pdtName,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolDeviceService.queryForInt(name,pdtName);
			List<PatrolDeviceType> pdList=patrolDeviceService.queryList(name, pdtName, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", pdList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryAccountList")
	@ResponseBody
	public Map<String, Object> queryAccountList(String deviceNo,String pdName,String pdtName,String createTimeStart,String createTimeEnd,
			String startTimeStart,String startTimeEnd,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try {
			int count = patrolDeviceAccountService.queryForInt(deviceNo,pdName,pdtName,createTimeStart,createTimeEnd,startTimeStart,startTimeEnd);
			List<PatrolDeviceAccount> pdaList=patrolDeviceAccountService.queryList(deviceNo,pdName, pdtName,createTimeStart,createTimeEnd,startTimeStart,startTimeEnd, page, rows, sort, order);
			
			jsonMap.put("total", count);
			jsonMap.put("rows", pdaList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	
	@RequestMapping(value="/queryTypeCBBList")
	@ResponseBody
	public Map<String, Object> queryTypeCBBList() {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<PatrolDeviceType> pdtList=patrolDeviceTypeService.queryCBBList();
		
		jsonMap.put("rows", pdtList);
		
		return jsonMap;
	}
}