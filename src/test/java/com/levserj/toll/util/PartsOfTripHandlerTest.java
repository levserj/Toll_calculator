package com.levserj.toll.util;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * Created by Serhii Levchynskyi on 06.10.2016.
 */
public class PartsOfTripHandlerTest {

    @Test
    public void BufferedReaderParsingTest(){
        String input = "qqq 1 false";
        BufferedReader br = Mockito.mock(BufferedReader.class);
        try {
            when(br.readLine()).thenReturn(input);
            String readStr = br.readLine();
            String [] inputStr = readStr.split("\\s");
            String clientId = inputStr[0];
            int checkPointNum = Integer.parseInt(inputStr[1]);
            boolean leftPaidZone = Boolean.parseBoolean(inputStr[2]);
            assertThat(clientId.equals("qqq"), is(true));
            assertThat(checkPointNum == 1, is(true));
            assertThat(leftPaidZone, is(false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
