package com.levserj.toll.domain;

import com.levserj.toll.Util.EmailSender;
import com.levserj.toll.Util.PaymentCalculator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Serhii Levchynskyi on 02.10.2016.
 */
public class Trip implements Runnable {

    private ArrayList<Integer> checkPoints = new ArrayList<>();
    private Socket client;

    @Autowired
    private PaymentCalculator calculator;
    @Autowired
    private EmailSender emailSender;

    public Trip(Socket client) {
        this.client = client;
    }

    public Trip() {
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));){
            String input;
            int checkPointNum;
            boolean leftPaidZone;
            while ((input=br.readLine())!=null){
                String [] inputStr = input.split("\\s");
                checkPointNum = Integer.parseInt(inputStr[1]);
                leftPaidZone = Boolean.parseBoolean(inputStr[2]);
                checkPoints.add(checkPointNum);
                if (leftPaidZone){
                    float payment = calculator.calculatePayment(checkPoints);
                    String clientId = inputStr[0];
                    emailSender.sendInvoice(clientId, payment);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
