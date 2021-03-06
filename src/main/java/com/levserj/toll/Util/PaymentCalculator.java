package com.levserj.toll.util;

import com.levserj.toll.domain.RoadPart;
import com.levserj.toll.repository.RoadPartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Serhii Levchynskyi on 02.10.2016.
 *
 * Simple util class, which searches DB for all the RoadParts
 * according to checkpoints list, sums up and returns their Passprices.
 */
@Component
public class PaymentCalculator {

    @Autowired
    private RoadPartRepo repo;

    public Float calculatePayment(List<Integer> checkpoints) {

        Float payment = 0f;
        for (int i = 0; i < checkpoints.size()-1; i++) {
            RoadPart part = repo.findByStartPointAndEndPoint(checkpoints.get(i), checkpoints.get(i+1));
            payment += part.getPassPrice();
        }
        return payment;
    }
}
