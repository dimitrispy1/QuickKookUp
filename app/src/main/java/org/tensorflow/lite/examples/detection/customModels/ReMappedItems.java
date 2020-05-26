package org.tensorflow.lite.examples.detection.customModels;

import java.util.HashMap;
import java.util.HashSet;

public class ReMappedItems {

    private static HashMap<String, String> items = new HashMap<>();
    private static HashSet<String> veggies = new HashSet<>();

    static {
        items.put("orange", "lemon");
        items.put("apple", "tomato");
        items.put("hot dog", "sausage");
        //items.put("donut", "avocado");
        items.put("banana", "zucchini");
        items.put("wine glass", "wine");
        items.put("bird", "chicken");
        items.put("carrot", "carrot");
        items.put("broccoli", "broccoli");
        items.put("cow", "beef");

        for(String key: items.keySet()){
            veggies.add(items.get(key));
        }

    }

    private ReMappedItems(){}

    public synchronized static boolean isVegetable(String key){
        return veggies.contains(key);
    }

    public static String getRename(String key){
        if(items.containsKey(key))
            return items.get(key);
        else
            return key;
    }

    public static HashMap<String, String> makeCopy(){

        HashMap<String, String> copy = new HashMap<>();
        for(String key: items.keySet()){
            copy.put(key, items.get(key));
        }

        return copy;
    }

}
