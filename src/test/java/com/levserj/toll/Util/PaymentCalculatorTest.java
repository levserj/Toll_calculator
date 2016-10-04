package com.levserj.toll.Util;

import com.levserj.toll.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Serhii Levchynskyi on 03.10.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaymentCalculatorTest.PaymentTestApplicationConfig.class)
public class PaymentCalculatorTest {

    @Autowired
    private PaymentCalculator pc;

    /**
     * This test is ignored, cause I didn't manage to exclude
     * CommandLineRunner from the context of the test, and
     * the test doesn't run because of while(true) loop in that class.
     * If the port is occupied (App is already running), new instance of
     * ServerLauncher fails to listen the same port,
     * while(true) is broken with Exception and test passes.
     */
    @Ignore
    @Test
    public void ifListOfTwoPointsPassedAsArgumentThePriceOfCorrectRoadPartIsReturned(){
        ArrayList<Integer> checkpoints = new ArrayList<>();
        checkpoints.add(1);
        checkpoints.add(2);
        assertThat(pc.calculatePayment(checkpoints), is(1.0f));
    }

    /**
     * Created by Serhii Levchynskyi on 04.10.2016.
     */
    @Configuration
    @ComponentScan(basePackages = "com.levserj.toll",
            excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ServerLauncher.class)})
    @EnableAutoConfiguration(exclude = Application.class)
    public static class PaymentTestApplicationConfig {
        private static final Logger log = LoggerFactory.getLogger(PaymentTestApplicationConfig.class);
        public static void main(String[] args) {
            log.info("In the TEST Config");
            SpringApplication.run(Application.class, args);
        }

    }
}
