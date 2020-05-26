package org.tensorflow.lite.examples.detection.customModels.viewHandle;

import org.tensorflow.lite.examples.detection.CameraActivity;
import org.tensorflow.lite.examples.detection.customModels.recipeFetcher.Recipe;

import java.util.List;

public class NutritionalFactView {

    private static CameraActivity handle;
    public static boolean processing;

    private NutritionalFactView(){
    }

    public static void addHandle(CameraActivity cameraActivity){
        if(handle == null)
            handle = cameraActivity;
    }
    public static void drawView(List<Recipe> recipes){
        handle.drawRecipes(recipes);
    }

    public static boolean handleExists(){
        return handle != null;
    }

    public synchronized static boolean isProcessing(){
        return processing;
    }


}
