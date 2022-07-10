package com.design.patterns.corepatterns.singleton;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SingletonRecordFinderTest {

    @Test
    public void singleTonTotalPopulationTest(){
        SingletonRecordFinder rf = new SingletonRecordFinder();
        // Problem here is that, I need to know the names of cities in database and there population values to test it
        // and we are testing it against live database, which is not ideal in case of test scenarios
        // as this becomes integration test and no longer a unit test
        List<String> names = List.of("Seoul", "Mexico City");
        int tp = rf.getTotalPopulation(names);
        assertEquals(17500000+17400000, tp);
    }
}