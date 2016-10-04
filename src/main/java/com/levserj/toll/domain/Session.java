package com.levserj.toll.domain;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Serhii Levchynskyi on 03.10.2016.
 *
 * Singleton to maintain state of each clients trip,
 * between the connections from different checkpoints.
 * Contains HasMap<userId, List<passedCheckpoints>>.
 * key-value pair is deleted from the map after client
 * leaves paid zone.
 */
public enum Session {
    INSTANCE;

    private final static HashMap<String, List<Integer>> sessions = new HashMap<>();

    public HashMap<String, List<Integer>> getSessions() {
        return sessions;
    }
}
