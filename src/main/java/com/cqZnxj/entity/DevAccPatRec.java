package com.cqZnxj.entity;

public class DevAccPatRec {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPdaId() {
		return pdaId;
	}
	public void setPdaId(Integer pdaId) {
		this.pdaId = pdaId;
	}
	public Integer getAprId() {
		return aprId;
	}
	public void setAprId(Integer aprId) {
		this.aprId = aprId;
	}
	public Integer getPatParCount() {
		return patParCount;
	}
	public void setPatParCount(Integer patParCount) {
		this.patParCount = patParCount;
	}
	public Integer getFinishParCount() {
		return finishParCount;
	}
	public void setFinishParCount(Integer finishParCount) {
		this.finishParCount = finishParCount;
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
	public Integer getPsId() {
		return psId;
	}
	public void setPsId(Integer psId) {
		this.psId = psId;
	}
	private Integer pdaId;
	private Integer aprId;
	private Integer patParCount;
	private Integer finishParCount;
	private String createTime;
	private Integer plId;
	private Integer ptId;
	private Integer psId;
}
