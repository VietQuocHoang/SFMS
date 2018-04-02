package com.sample.sfms.model.dashboard;

import java.util.ArrayList;
import java.util.List;

public class PieChartModel {

    private List<PieChartDataModel> data;
    private String title;

    public PieChartModel() {
        data = new ArrayList<>();
    }

    public void addData(PieChartDataModel dataModel) {
        data.add(dataModel);
    }


    public List<PieChartDataModel> getData() {
        return data;
    }

    public void setData(List<PieChartDataModel> data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (PieChartDataModel p : data) {
            stringBuilder.append(p.toString());
            if (data.indexOf(p) < (data.size() - 1)) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public String getNameList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (PieChartDataModel p : data) {
            stringBuilder.append(p.getName());
            if (data.indexOf(p) < (data.size() - 1)) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public String getValueList() {
        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("[");
        for (PieChartDataModel p : data) {
            stringBuilder.append(p.getValue());
            if (data.indexOf(p) < (data.size() - 1)) {
                stringBuilder.append(", ");
            }
        }
//        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
