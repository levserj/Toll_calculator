package com.levserj.toll;


import com.levserj.toll.domain.TripHandler;
import com.levserj.toll.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Serhii Levchynskyi on 01.10.2016.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private UserRepo repo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        if (strings.length<2){
            System.err.println("You should pass two args: <portNumber> and <threadsNumber>");
            System.exit(1);
        }
        int portNumber = Integer.parseInt(strings[0]);
        int threadsNumber = Integer.parseInt(strings[1]);
        ExecutorService pool = null;

        try(ServerSocket serverSocket = new ServerSocket(portNumber)){
            pool = Executors.newFixedThreadPool(threadsNumber);
            System.out.println("Server is online");
            while (true){
                Socket client = serverSocket.accept();
                Runnable worker = new TripHandler(client);
                pool.execute(worker);
            }
        } catch (Exception e){
            System.out.println("Exception while trying to listen or listening to port : " + portNumber);
            System.out.println(e.getMessage());
        } finally {
            if (pool != null) {
                pool.shutdown();
            }
        }
    }
}
