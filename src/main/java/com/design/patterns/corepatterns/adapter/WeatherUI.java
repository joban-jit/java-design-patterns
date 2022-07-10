package com.design.patterns.corepatterns.adapter;

public class WeatherUI {

    public void showTemperature(int zipCode){
        WeatherAdaptor adaptor = new WeatherAdaptor();
        System.out.println(adaptor.findTemperature(zipCode));
    }

    public static void main(String[] args) {
        WeatherUI ui = new WeatherUI();
        ui.showTemperature(19406);
    }
}
