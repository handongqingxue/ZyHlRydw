package com.cqZnxj.entity;

public class PatLineAreaAccSet {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPlId() {
		return plId;
	}
	public void setPlId(Integer plId) {
		this.plId = plId;
	}
	public Integer getPaId() {
		return paId;
	}
	public void setPaId(Integer paId) {
		this.paId = paId;
	}
	public String getPdaIds() {
		return pdaIds;
	}
	public void setPdaIds(String pdaIds) {
		this.pdaIds = pdaIds;
	}
	private Integer plId;
	private Integer paId;
	private String pdaIds;
}