package com.levserj.toll.Util;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Serhii Levchynskyi on 02.10.2016.
 */
public class EmailSenderTest {

    private EmailSender emailSender = new EmailSender();

    /**
     * Tested with my email, works fine.
     * Ignored cuase won't work without my credentials
     */
    @Ignore
    @Test
    public void sendMyselfEmail(){
        /*emailSender.sendInvoice();*/
    }
}
