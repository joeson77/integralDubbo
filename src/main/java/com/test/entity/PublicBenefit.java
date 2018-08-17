package com.test.entity;

public class PublicBenefit {
    private Integer activeId;

    private String activeName;

    private String startTime;

    private String endTime;

    private Integer activeType;

    private Integer activeNeedIntegral;

    private Integer activeStatus;
    
    public Integer havingIntegral;

    private String ex1;

    private String ex2;

    private String ex3;

    public Integer getActiveId() {
        return activeId;
    }

    public void setActiveId(Integer activeId) {
        this.activeId = activeId;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName == null ? null : activeName.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getActiveType() {
        return activeType;
    }

    public void setActiveType(Integer activeType) {
        this.activeType = activeType;
    }

    public Integer getActiveNeedIntegral() {
        return activeNeedIntegral;
    }

    public void setActiveNeedIntegral(Integer activeNeedIntegral) {
        this.activeNeedIntegral = activeNeedIntegral;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }
    
    public Integer getHavingIntegral() {
		return havingIntegral;
	}

	public void setHavingIntegral(Integer havingIntegral) {
		this.havingIntegral = havingIntegral;
	}

	public String getEx1() {
        return ex1;
    }

    public void setEx1(String ex1) {
        this.ex1 = ex1 == null ? null : ex1.trim();
    }

    public String getEx2() {
        return ex2;
    }

    public void setEx2(String ex2) {
        this.ex2 = ex2 == null ? null : ex2.trim();
    }

    public String getEx3() {
        return ex3;
    }

    public void setEx3(String ex3) {
        this.ex3 = ex3 == null ? null : ex3.trim();
    }
}