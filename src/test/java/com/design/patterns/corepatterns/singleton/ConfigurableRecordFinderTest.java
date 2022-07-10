package com.design.patterns.corepatterns.singleton;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurableRecordFinderTest {

    @Test
    public void dependentPopulationTest(){
        DummyDatabase db = new DummyDatabase();
        ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
        assertEquals(4, rf.getTotalPopulation(List.of("alpha", "gamma")));
    }
}

class DummyDatabase implements Database{

    private Map<String, Integer> data = new HashMap<>();

    public DummyDatabase() {
        this.data = Map.of("alpha", 1, "beta", 2, "gamma", 3);
    }

    @Override
    public int getPopulation(String name) {
        return data.get(name);
    }
}