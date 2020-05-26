package org.tensorflow.lite.examples.detection.customModels;

import android.os.AsyncTask;
import android.util.Log;

import org.tensorflow.lite.examples.detection.customModels.recipeFetcher.Recipe;
import org.tensorflow.lite.examples.detection.customModels.recipeFetcher.RecipeService;
import org.tensorflow.lite.examples.detection.customModels.viewHandle.NutritionalFactView;

import java.io.IOException;
import java.util.List;

public class HandleRecipeTask extends AsyncTask<FoodList, Object, List<Recipe>> {


    public HandleRecipeTask(){

    }

    @Override
    protected List<Recipe> doInBackground(FoodList... foodLists) {

        if (foodLists[0].getSize() > 0) {
            NutritionalFactView.processing = true;

            List<Recipe> recipes = null;
            try {
                recipes = new RecipeService().getRecipesUsingIngredients(foodLists[0].getSet());
                Log.e("MSG", "Recipes obtained");
                Log.e("CONTENT", recipes.toString());
                return recipes;
            } catch (IOException e) {
                e.printStackTrace();
                NutritionalFactView.processing = false;

            }
        }
        return null;
    }

    protected void onPostExecute( List<Recipe> result){
        if(result == null)
            return;

        NutritionalFactView.drawView(result);
        NutritionalFactView.processing = false;
    }
}
