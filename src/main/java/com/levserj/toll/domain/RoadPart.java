package com.levserj.toll.domain;

/**
 * Created by Serhii Levchynskyi on 02.10.2016.
 *
 * This bean represents part of the road, between two checkpoints,
 * with the price to pass this part.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoadPart roadPart = (RoadPart) o;

        if (getStartPoint() != roadPart.getStartPoint()) return false;
        return getEndPoint() == roadPart.getEndPoint();

    }

    @Override
    public int hashCode() {
        int result = getStartPoint();
        result = 31 * result + getEndPoint();
        return result;
    }
}
