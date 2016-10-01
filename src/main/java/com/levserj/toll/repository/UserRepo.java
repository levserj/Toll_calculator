package com.levserj.toll.repository;

import com.levserj.toll.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Serhii Levchynskyi on 01.10.2016.
 */
public interface UserRepo extends MongoRepository<User, String> {
    User findByEmail();
}
