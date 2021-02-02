package com.organicpy.myapplication.data;

import java.util.List;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 28-01-2021
 */
public class Task {
    private String name;
    private boolean isComplete;
    private String operat;
    private List<Integer> dummyData;

    public Task(String name, boolean isComplete, String operat, List<Integer> dummyData) {
        this.name = name;
        this.isComplete = isComplete;
        this.operat = operat;
        this.dummyData = dummyData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getOperat() {
        return operat;
    }

    public void setOperat(String operat) {
        this.operat = operat;
    }

    public List<Integer> getDummyData() {
        return dummyData;
    }

    public void setDummyData(List<Integer> dummyData) {
        this.dummyData = dummyData;
    }
}
