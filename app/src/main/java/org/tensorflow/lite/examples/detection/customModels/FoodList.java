package org.tensorflow.lite.examples.detection.customModels;

import java.util.HashMap;
import java.util.HashSet;

public class FoodList {

    private HashSet<String> items;

    public FoodList(){
        this.items = new HashSet<String>();
    }

    public void append(String item){
        //apple, banana, carrot,
        //check if food item
        items.add(ReMappedItems.getRename(item));
    }

    public HashSet getSet() {
        return items;
    }

    public int getSize(){
        return items.size();
    }

}


