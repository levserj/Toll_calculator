package com.levserj.toll.domain;

/**
 * Created by Serhii Levchynskyi on 02.10.2016.
 */
public class RoadPart {

    private String id;
    private int startPoint;
    private int endPoint;
    private float passPrice;

    public RoadPart() {
    }

    public RoadPart(int startPoint, int endPoint, float passPrice) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.passPrice = passPrice;
    }

    public RoadPart(String id, int startPoint, int endPoint, float passPrice) {
        this.id = id;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.passPrice = passPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    public float getPassPrice() {
        return passPrice;
    }

    public void setPassPrice(float passPrice) {
        this.passPrice = passPrice;
    }
}
