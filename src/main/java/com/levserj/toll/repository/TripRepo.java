package com.levserj.toll.repository;

import com.levserj.toll.domain.Trip;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Serhii Levchynskyi on 03.10.2016.
 */
public interface TripRepo extends CrudRepository<Trip, String> {
}
