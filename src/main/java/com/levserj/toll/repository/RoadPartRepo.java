package com.levserj.toll.repository;

import com.levserj.toll.domain.RoadPart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Serhii Levchynskyi on 02.10.2016.
 */
@Repository
public interface RoadPartRepo extends MongoRepository<RoadPart, String> {
    RoadPart findByStartPointAndEndPoint(int startPoint, int endPoint);
}
