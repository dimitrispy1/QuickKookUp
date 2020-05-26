package org.tensorflow.lite.examples.detection.customModels.recipeFetcher;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RecipeApiParser {

    public static List<Recipe> getRecipes(String htmlResponse, int numOfRecipes)
    {
        List<Recipe> recipes = new ArrayList<Recipe>();

        for(int i =0; i < numOfRecipes; i++)
        {
            String str1 = "<h3 class=\"fixed-recipe-card__h3\">";
            int len1 = str1.length();
            String str2 = "<a href=\"";
            int len2 = str2.length();

            htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str1) + len1).trim();
            htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str2) + len2);
            String link = htmlResponse.substring(0, htmlResponse.indexOf("\""));
            String str3 = "<span class=\"fixed-recipe-card__title-link\">";
            int len3 = str3.length();
            htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str3) + len3);
            String name = htmlResponse.substring(0,htmlResponse.indexOf("<"));
            Recipe recipe = new Recipe(link, name);
            recipes.add(recipe);
        }
        return recipes;
    }

    public static void attachNutritionalFacts(String htmlResponse, Recipe recipe){
        String str1 = "itemprop=\"calories\">";
        int len1 = str1.length();


        if(htmlResponse.indexOf("section-body") != -1)
            handleSecondHtmlParseCase(htmlResponse,recipe);
        else {
            if (htmlResponse.indexOf(str1) != -1) {
                htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str1) + len1);
                recipe.setCalories(htmlResponse.substring(0, htmlResponse.indexOf(" ")));
            }

            String str2 = "itemprop=\"fatContent\">";
            int len2 = str2.length();

            String str3 = "=\"true\">";
            int len3 = str3.length();

            if (htmlResponse.indexOf(str2) != -1) {
                htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str2) + len2);
                String fatContent = htmlResponse.substring(0, htmlResponse.indexOf("<"));

                htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str3) + len3);
                recipe.setFatContent(fatContent + htmlResponse.substring(0, htmlResponse.indexOf(" ")));
            }

            String str4 = "itemprop=\"carbohydrateContent\">";
            int len4 = str4.length();

            if (htmlResponse.indexOf(str4) != -1) {
                htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str4) + len4);
                String carbsContent = htmlResponse.substring(0, htmlResponse.indexOf("<"));

                htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str3) + len3);
                recipe.setCarbohydrateContent(carbsContent + htmlResponse.substring(0, htmlResponse.indexOf(" ")));
            }

            String str5 = "itemprop=\"proteinContent\">";
            int len5 = str5.length();

            if (htmlResponse.indexOf(str5) != -1) {
                htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str5) + len5);
                String proteinContent = htmlResponse.substring(0, htmlResponse.indexOf("<"));

                htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str3) + len3);
                recipe.setProteinContent(proteinContent + htmlResponse.substring(0, htmlResponse.indexOf(" ")));
            }

            String str6 = "itemprop=\"cholesterolContent\">";
            int len6 = str6.length();

            if (htmlResponse.indexOf(str6) != -1) {
                htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str6) + len6);
                String cholesterolContent = htmlResponse.substring(0, htmlResponse.indexOf("<"));

                htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str3) + len3);
                recipe.setCholesterolContent(cholesterolContent + htmlResponse.substring(0, htmlResponse.indexOf(" ")));
            }

            String str7 = "itemprop=\"sodiumContent\">";
            int len7 = str7.length();

            if (htmlResponse.indexOf(str7) != -1) {
                htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str7) + len7);
                String sodiumContent = htmlResponse.substring(0, htmlResponse.indexOf("<"));

                htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str3) + len3);
                recipe.setSodiumContent(sodiumContent + htmlResponse.substring(0, htmlResponse.indexOf(" ")));
            }
        }

    }



    private static void handleSecondHtmlParseCase(String htmlResponse, Recipe recipe){
        String str1 = "\"section-label\">Per Serving:</div>";
        String str2 = "<div class=\"section-body\">";
        int len1 = str1.length();
        int len2 = str2.length();
        htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str1) + len1).trim();
        htmlResponse = htmlResponse.substring(htmlResponse.indexOf(str2) + len2);
        String nutritionalValues = htmlResponse.substring(0, htmlResponse.indexOf("<a href")).trim();
        String[] values = nutritionalValues.split(";           ");

        recipe.setCalories(values[0].substring(0, values[0].indexOf(" ")));
        recipe.setFatContent(values[1].substring(0, values[1].indexOf("g")+1));
        recipe.setCholesterolContent(values[2].substring(0, values[2].indexOf("g")+1));
        String[] otherValues = values[3].split("sodium.          ");
        recipe.setSodiumContent(otherValues[0].substring(0, otherValues[0].indexOf("g")+1));
        recipe.setCarbohydrateContent(otherValues[1].substring(0, otherValues[1].indexOf("g")+1));
        recipe.setProteinContent(values[4].substring(0, values[4].indexOf("g")+1));
    }

}
