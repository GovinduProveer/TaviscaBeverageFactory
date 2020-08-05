package com.beverage.application.dataProvider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataProvider {

    private static final String COFFEE = "coffee";
    private static final String CHAI = "chai";
    private static final String BANANA_SMOOTHIE = "banana smoothie";
    private static final String STRAWBERRY_SHAKE = "strawberry shake";
    private static final String MOJITO = "mojito";

    private static final String MILK = "milk";
    private static final String SUGAR = "sugar";
    private static final String SODA = "soda";
    private static final String MINT = "mint";
    private static final String WATER = "water";

    private static final Double COFFEE_INGREDIENT_RATE = 3.0d;
    private static final Double TEA_INGREDIENT_RATE = 2.0d;
    private static final Double BANANA_INGREDIENT_RATE = 4.0d;
    private static final Double STRAWBERRIES_INGREDIENT_RATE = 5.0d;
    private static final Double LEMON_INGREDIENT_RATE = 5.5d;

    public static Map<String, List<String>> getBeverages() {

        Map<String, List<String>> beveragesMap = new HashMap<>();
        beveragesMap.put(COFFEE, Arrays.asList(MILK, SUGAR, WATER));
        beveragesMap.put(CHAI, Arrays.asList(MILK, SUGAR, WATER));
        beveragesMap.put(BANANA_SMOOTHIE, Arrays.asList(MILK, SUGAR, WATER));
        beveragesMap.put(STRAWBERRY_SHAKE, Arrays.asList(WATER, MILK, SUGAR));
        beveragesMap.put(MOJITO, Arrays.asList(SUGAR, WATER, SODA, MINT));
        return beveragesMap;
    }

    public static Map<String, Double> getIngredientRates() {

        Map<String, Double> ingredientRates = new HashMap<>();
        ingredientRates.put(MILK, 1.0d);
        ingredientRates.put(SUGAR, 0.5d);
        ingredientRates.put(SODA, 0.5d);
        ingredientRates.put(MINT, 0.5d);
        ingredientRates.put(WATER, 0.5d);

        return ingredientRates;
    }

    public static Map<String, Double> getBeverageRates() {

        Map<String, Double> itemRates = new HashMap<>();

        itemRates.put(COFFEE, COFFEE_INGREDIENT_RATE + getIngredientRates().get(MILK)
                + getIngredientRates().get(WATER) + getIngredientRates().get(SUGAR));

        itemRates.put(CHAI, TEA_INGREDIENT_RATE + +getIngredientRates().get(MILK)
                + getIngredientRates().get(WATER) + getIngredientRates().get(SUGAR));

        itemRates.put(BANANA_SMOOTHIE, BANANA_INGREDIENT_RATE + getIngredientRates().get(MILK)
                + getIngredientRates().get(WATER) + getIngredientRates().get(SUGAR));

        itemRates.put(STRAWBERRY_SHAKE, STRAWBERRIES_INGREDIENT_RATE + getIngredientRates().get(MILK)
                + getIngredientRates().get(WATER) + getIngredientRates().get(SUGAR));

        itemRates.put(MOJITO, LEMON_INGREDIENT_RATE + getIngredientRates().get(SODA)
                + getIngredientRates().get(WATER) + getIngredientRates().get(SUGAR)
                + getIngredientRates().get(MINT));


        return itemRates;
    }
}
