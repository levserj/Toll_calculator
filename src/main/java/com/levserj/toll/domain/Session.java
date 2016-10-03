package com.levserj.toll.domain;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Serhii Levchynskyi on 03.10.2016.
 */
public enum Session {
    INSTANCE;

    private final HashMap<String, List<Integer>> sessions = new HashMap<>();

    public HashMap<String, List<Integer>> getSessions() {
        return sessions;
    }
}
