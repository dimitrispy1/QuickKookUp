package org.tensorflow.lite.examples.detection.customModels.recipeFetcher;


import org.tensorflow.lite.examples.detection.customModels.FoodList;
import org.tensorflow.lite.examples.detection.customModels.http.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RecipeService {

    private static final String GET_RECIPES = "https://www.allrecipes.com/search/results/?wt=";

    private HttpClient httpClient;

    public RecipeService() {
        this.httpClient = new HttpClient();
    }

    public List<Recipe> getRecipesUsingIngredients(HashSet<String> items) throws IOException {

        List<String> ingredients = new ArrayList<String>(items);
        String url = GET_RECIPES;
        for(int i = 0; i < ingredients.size(); i++){
            url = url + ingredients.get(i);
            if(i + 1 != ingredients.size())
                url = url + ",";
        }

        String httpResponse = httpClient.get(url);

        List<Recipe> recipes = RecipeApiParser.getRecipes(httpResponse, 5);

        for(Recipe recipe : recipes){
            attachNutritionalFacts(recipe);
        }

        return recipes;
    }

    public void attachNutritionalFacts(Recipe recipe) throws IOException {
        String httpResponse = httpClient.get(recipe.getLink());
        RecipeApiParser.attachNutritionalFacts(httpResponse, recipe);
    }
}
