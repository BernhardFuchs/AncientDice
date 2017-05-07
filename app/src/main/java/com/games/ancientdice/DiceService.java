package com.games.ancientdice;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by berfuc on 28/04/2017.
 */

class DiceService {
    public Map<Integer, Boolean> roll() {

        Map<Integer, Boolean> resultTable = new HashMap<>();

        for (int i = 0; i < 4; i++){
            resultTable.put(i, getRandomBoolean());
        }

        return resultTable;
    }

    private Boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
