package com.levserj.toll.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Serhii Levchynskyi on 03.10.2016.
 *
 * Bean to maintain the history of the clients trip in the DB.
 */
@Document
public class Trip {

    private List<Integer> checkpoints;
    private Float payment;
    private String dateAndTime;

    public Trip() {
    }

    public Trip(List<Integer> checkpoints, Float payment, String dateAndTime) {
        this.checkpoints = checkpoints;
        this.payment = payment;
        this.dateAndTime = dateAndTime;
    }

    public List<Integer> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<Integer> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }


    @Override
    public String toString() {
        return "Trip{" +
                "dateAndTime='" + dateAndTime + '\'' +
                ", payment=" + payment +
                ", checkpoints=" + checkpoints +
                '}';
    }
}
