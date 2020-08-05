package com.beverage.application;

import lombok.extern.slf4j.Slf4j;

import com.beverage.application.beverageFactory.TaviscaBeverageFactory;

@Slf4j
public class BeverageFactoryApplication {
    public static void main(String[] args){
        TaviscaBeverageFactory taviscaBeverageFactory = new TaviscaBeverageFactory();
        
        //input beverage here with ingrediants
        String beverageOrder = " Chai ,-milk, -water, Mojito";

        final double cost = taviscaBeverageFactory.getOrderCost(beverageOrder);

        log.info("*************************** Your total cost for beverage is ${} ***************************", cost);
    }
}
