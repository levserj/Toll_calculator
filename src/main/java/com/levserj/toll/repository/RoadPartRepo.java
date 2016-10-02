package com.levserj.toll.repository;

import com.levserj.toll.domain.RoadPart;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Serhii Levchynskyi on 02.10.2016.
 */
public interface RoadPartRepo extends MongoRepository<RoadPart, String> {
    RoadPart findByStartPointAndEndPoint(int startPoint, int endPoint);
}
