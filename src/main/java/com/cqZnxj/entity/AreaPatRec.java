package com.cqZnxj.entity;

public class AreaPatRec {
	
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPaId() {
		return paId;
	}
	public void setPaId(Integer paId) {
		this.paId = paId;
	}
	public Integer getPatAccCount() {
		return patAccCount;
	}
	public void setPatAccCount(Integer patAccCount) {
		this.patAccCount = patAccCount;
	}
	public Integer getFinishAccCount() {
		return finishAccCount;
	}
	public void setFinishAccCount(Integer finishAccCount) {
		this.finishAccCount = finishAccCount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getPlId() {
		return plId;
	}
	public void setPlId(Integer plId) {
		this.plId = plId;
	}
	public Integer getPtId() {
		return ptId;
	}
	public void setPtId(Integer ptId) {
		this.ptId = ptId;
	}
	private Integer paId;
	private Integer patAccCount;
	private Integer finishAccCount;
	private String createTime;
	private Integer plId;
	private Integer ptId;
}