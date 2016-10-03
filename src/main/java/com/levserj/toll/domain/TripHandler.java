package com.levserj.toll.domain;

import com.levserj.toll.Util.EmailSender;
import com.levserj.toll.Util.PaymentCalculator;
import com.levserj.toll.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Serhii Levchynskyi on 02.10.2016.
 */
public class TripHandler implements Runnable {

    private Socket client;

    @Autowired
    private PaymentCalculator calculator;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private UserRepo userRepo;

    public TripHandler(Socket client) {
        this.client = client;
    }

    public TripHandler() {
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));){
            String input;
            int checkPointNum;
            boolean leftPaidZone;
            while ((input=br.readLine())!=null){
                String [] inputStr = input.split("\\s");
                String clientId = inputStr[0];
                checkPointNum = Integer.parseInt(inputStr[1]);
                leftPaidZone = Boolean.parseBoolean(inputStr[2]);
                HashMap<String, List<Integer>> sessions = Session.INSTANCE.getSessions();
                if (!sessions.containsKey(clientId)){
                    List<Integer> checkpoints = new ArrayList<>();
                    checkpoints.add(checkPointNum);
                    sessions.put(clientId, checkpoints);
                } else {
                    sessions.get(clientId).add(checkPointNum);
                }
                if (leftPaidZone){
                    List<Integer> checkpoints = sessions.get(clientId);
                    User user = userRepo.findOne(clientId);
                    float payment = calculator.calculatePayment(checkpoints);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
                    String dateAndTime = sdf.format(Calendar.getInstance().getTime());
                    Trip trip = new Trip(checkpoints, payment, dateAndTime);
                    emailSender.sendInvoice(user, trip);


                    user.getTrips().add(trip);
                    userRepo.save(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
