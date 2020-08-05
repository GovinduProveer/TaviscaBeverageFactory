package com.beverage.application.beverageFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.beverage.application.exception.InvalidIngredientsException;
import com.beverage.application.dataProvider.DataProvider;
import com.beverage.application.exception.InvalidOrderException;

public class TaviscaBeverageFactory {

    Map<String, List<String>> beverages = DataProvider.getBeverages();
    Map<String, Double> beverageRates = DataProvider.getBeverageRates();
    Map<String, Double> ingredientRates = DataProvider.getIngredientRates();

    public double getOrderCost(String order) {
        double cost = 0.0d;
        List<String> orderIngredients = getIngredientFromOrder(order.trim());
        for (String ingredient : orderIngredients) {
            List<String> beverageWithIngredients = getIngredientFromBeverage(ingredient);
            isValidOrder(ingredient);
            cost = cost + calculateCost(beverageWithIngredients);
        }
        return cost;
    }

    private void isValidOrder(String ingredient) {
        List<String> beverageIngredients = getIngredientFromBeverage(ingredient);

        if (!beverages.containsKey(beverageIngredients.get(0)))
            throw new InvalidOrderException("*************** Invalid beverage menu -> " + ingredient + " . Please order with valid menu item. ***************");

        List<String> allIngredients = beverages.get(beverageIngredients.get(0));

        Optional<String> duplicateIngredient = beverageIngredients.stream().filter(i -> Collections.frequency(beverageIngredients, i) > 1).findFirst();
        if (duplicateIngredient.isPresent())
            throw new InvalidIngredientsException("*************** There is duplicate ingredient -> " + duplicateIngredient.get() + "Please order with valid menu item. ***************");

        List<String> ingredients = beverageIngredients.subList(1, beverageIngredients.size());
        boolean validIngredients = ingredients.stream().allMatch(t -> allIngredients.stream().anyMatch(t::contains));

        if (!validIngredients)
            throw new InvalidIngredientsException("*************** There is an invalid ingredient in order. Please check and order again. ***************");

        if (beverageIngredients.size() == allIngredients.size() + 1)
            throw new InvalidOrderException("*************** Invalid Order. You cannot exclude all items from " + ingredient + " ***************");

    }

    private Double calculateCost(List<String> beverageWithIngredients) {
        Double beveragePrice = beverageRates.get(beverageWithIngredients.get(0));
        Double ingredientsPrice = 0.0d;
        if (beverageWithIngredients.size() > 1)
            for (int i = 1; i < beverageWithIngredients.size(); i++) {
                ingredientsPrice = ingredientsPrice + ingredientRates.get(beverageWithIngredients.get(i));
            }
        return beveragePrice - ingredientsPrice;
    }


    private List<String> getIngredientFromOrder(String order1) {
        return Arrays.stream(order1.split("(?!,\\s*-),")).map(String::trim).map(String::toLowerCase).collect(Collectors.toList());
    }

    private List<String> getIngredientFromBeverage(String beverage) {
        return Arrays.stream(beverage.split(",")).map(s -> s.replace("-", "")).map(String::trim).collect(Collectors.toList());
    }

}
