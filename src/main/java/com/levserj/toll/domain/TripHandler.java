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
 *
 * This class handles the work of processing clients messages
 * from the checkpoints. Receives Socket in the constructor and
 * reads necessary data.
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
        try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()))){
            /**
             * Client-side supposed to send three values:
             * clientId, number of checkpoint and a flag,
             * which is true, if the client left the paid zone.
             */
            String input;
            int checkPointNum;
            boolean leftPaidZone;
            // Parsing input stream to get the values
            while ((input=br.readLine())!=null){
                String [] inputStr = input.split("\\s");
                String clientId = inputStr[0];
                checkPointNum = Integer.parseInt(inputStr[1]);
                leftPaidZone = Boolean.parseBoolean(inputStr[2]);
                // Get the Sessions map
                HashMap<String, List<Integer>> sessions = Session.INSTANCE.getSessions();
                // Before putting new clients trip info in the map, new list of checkpoints should be created
                if (!sessions.containsKey(clientId)){
                    List<Integer> checkpoints = new ArrayList<>();
                    checkpoints.add(checkPointNum);
                    sessions.put(clientId, checkpoints);

                    // If it's not the first checkpoint, client passes, his id(key) is already in the map
                } else {
                    sessions.get(clientId).add(checkPointNum);
                }
                // Main work is done when client leaves paid zone.
                if (leftPaidZone){
                    List<Integer> checkpoints = sessions.get(clientId);
                    User user = userRepo.findOne(clientId);
                    // Call to calculatePayment method to get the payment
                    float payment = calculator.calculatePayment(checkpoints);
                    // Date is needed to add the email and history(trip)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
                    String dateAndTime = sdf.format(Calendar.getInstance().getTime());
                    // Trip object for this trip is created
                    Trip trip = new Trip(checkpoints, payment, dateAndTime);
                    // Trip is saved to DB through user list of trips
                    user.getTrips().add(trip);
                    userRepo.save(user);
                    // Send an email to the user
                    emailSender.sendInvoice(user, trip);
                    // Cleaning the user from the sessions map, new record for new trip will be started on next visit
                    sessions.remove(clientId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
