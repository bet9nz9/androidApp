package com.example.kursach.model;

import java.math.BigDecimal;

public class VacancyDataModel {

    private Long id;
    private BigDecimal coast;
    private String shortDesc;
    private String fullDesc;
    private Boolean isSelected;
    private Long executorId;
    private Long employeeId;

    public VacancyDataModel(Long id, BigDecimal coast, String shortDesc, String fullDesc, Boolean isSelected, Long executorId, Long employeeId) {
        this.id = id;
        this.coast = coast;
        this.shortDesc = shortDesc;
        this.fullDesc = fullDesc;
        this.isSelected = isSelected;
        this.executorId = executorId;
        this.employeeId = employeeId;
    }

    public VacancyDataModel(BigDecimal coast, String shortDesc, String fullDesc, Boolean isSelected, Long executorId, Long employeeId) {
        this.coast = coast;
        this.shortDesc = shortDesc;
        this.fullDesc = fullDesc;
        this.isSelected = isSelected;
        this.executorId = executorId;
        this.employeeId = employeeId;
    }

    public VacancyDataModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCoast() {
        return coast;
    }

    public void setCoast(BigDecimal coast) {
        this.coast = coast;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getFullDesc() {
        return fullDesc;
    }

    public void setFullDesc(String fullDesc) {
        this.fullDesc = fullDesc;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Long executorId) {
        this.executorId = executorId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
