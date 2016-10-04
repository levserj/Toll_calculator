package com.levserj.toll.repository;

import com.levserj.toll.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Serhii Levchynskyi on 01.10.2016.
 */
@Repository
public interface UserRepo extends MongoRepository<User, String> {
    User findByEmail();
}
