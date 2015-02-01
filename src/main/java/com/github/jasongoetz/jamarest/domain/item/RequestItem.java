package com.github.jasongoetz.jamarest.domain.item;

import java.util.Map;

public class RequestItem {

    private String globalId;
    private Integer project;
    protected Integer itemType;
    private Integer childItemType;
    private Location location;
    private Map<String, Object> fields;

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public Integer getProject() {
        return project;
    }

    public void setProject(Integer project) {
        this.project = project;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getChildItemType() {
        return childItemType;
    }

    public void setChildItemType(Integer childItemType) {
        this.childItemType = childItemType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }

}
