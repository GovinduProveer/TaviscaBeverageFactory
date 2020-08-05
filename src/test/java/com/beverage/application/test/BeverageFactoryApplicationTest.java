package com.beverage.application.test;

import org.junit.Assert;
import org.junit.Test;

import com.beverage.application.beverageFactory.TaviscaBeverageFactory;
import com.beverage.application.exception.InvalidIngredientsException;
import com.beverage.application.exception.InvalidOrderException;

public class BeverageFactoryApplicationTest {
    
    TaviscaBeverageFactory beverageFactory = new TaviscaBeverageFactory();

    @Test(expected = InvalidOrderException.class)
    public void testForBlankOrder() {
        String order = "";
        Assert.assertEquals(0.0d, beverageFactory.getOrderCost(order), 0.0d);
    }

    @Test
    public void testForNormalOrderWithIngredients() {
        Assert.assertEquals(3.0d, beverageFactory.getOrderCost("Chai, -sugar, -water"), 0.0d);
    }

    @Test
    public void testForOrderWithNoExclusions() {
        String order = "Chai, Coffee";
        Assert.assertEquals(9.0d, beverageFactory.getOrderCost(order), 0.0d);
    }
    
    @Test()
    public void testForOrderWithMultipleBeveragesWithExclusion() {
        String order = "Chai, -milk, -water, Coffee, -sugar, Mojito,-mint";
        Assert.assertEquals(14.0d, beverageFactory.getOrderCost(order), 0.0d);
    }
    

    @Test(expected = InvalidOrderException.class)
    public void testForOrderWithZeroIngredient() {
        String order = "Chai, -milk,-sugar, -water";
        Assert.assertEquals(0.0d, beverageFactory.getOrderCost(order), 0.0d);
    }

    @Test(expected = InvalidIngredientsException.class)
    public void testInvalidIngredientInOrder() {
        String order = "Mojito,-money,-water";
        Assert.assertEquals(0.0d, beverageFactory.getOrderCost(order), 0.0d);
    }
}
