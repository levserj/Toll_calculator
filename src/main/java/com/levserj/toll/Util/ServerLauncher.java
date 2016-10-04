package com.levserj.toll.Util;

import com.levserj.toll.domain.TripHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Serhii Levchynskyi on 04.10.2016.
 *
 * run() method of this class creates ServerSocket to listen for
 * connections on the port you specify as the first argument and
 * ThreadPool with number of threads equal to second argument.
 * If no arguments provided default values 8055 and 10 will be used.
 */
@Component
public class ServerLauncher implements CommandLineRunner {

    @Autowired
    private ApplicationContext ctx;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... strings) throws Exception {
        log.info("In the run method of ServerLauncher");
        int portNumber;
        int threadsNumber;
        if (strings.length > 1){
            portNumber = Integer.parseInt(strings[0]);
            threadsNumber = Integer.parseInt(strings[1]);
        } else {
            portNumber = 8055;
            threadsNumber = 10;
        }
        ExecutorService pool = null;

        try(ServerSocket serverSocket = new ServerSocket(portNumber)){
            pool = Executors.newFixedThreadPool(threadsNumber);
            log.info(String.format("Server is online and listening on the port %d", portNumber));
            /**
             * Infinite loop creates Socket accepting connection and
             * passes it as an argument to new Runnable object TripHandler.
             * Runnable passed to one of the threads in the pool for execution.
             */
            while (true){
                Socket client = serverSocket.accept();
                Runnable worker = ctx.getBean(TripHandler.class, client);
                pool.execute(worker);
            }
        } catch (Exception e){
            log.error(String.format("Exception while trying to listen or listening to port : %d", portNumber));
            log.error(e.getMessage());
        } finally {
            if (pool != null) {
                pool.shutdown();
            }
        }
    }
}
