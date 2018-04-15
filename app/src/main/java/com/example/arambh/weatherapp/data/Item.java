package com.example.arambh.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Arambh on 02-Oct-17.
 */

public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

    }
}
