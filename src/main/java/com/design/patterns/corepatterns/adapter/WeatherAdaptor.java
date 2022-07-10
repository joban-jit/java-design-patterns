package com.design.patterns.corepatterns.adapter;

public class WeatherAdaptor {

    public int findTemperature(int zipCode){
        String city = null;
        if(zipCode==19406){
            city = "King Of Prussia";
        }
        WeatherFinder finder = new WeatherFinderImpl();
        return finder.find(city);
    }
}
