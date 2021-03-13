package com.example.fitnesslog.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Calculators {
    public static float CalculateBMI(float weight, float height, boolean metrics ){
        if (metrics){
            return weight*100f*100f/height/height;
        }
        return weight*703f/height/height;
    }
    public static String GetBmiExplanation(float bmi){
        if (bmi < 18.5){
            return "Your are underweight";
        }else if (bmi < 25.0){
            return "Your are at normal or healthy Weight";

        }else if (bmi < 30){
            return "Your are overweight";

        }else{
            return "Your are obese";
        }
    }
    public static float CalculateFat(boolean isMale, float sumOfSkinfold, int age){
        if (isMale){
            return CalculateMaleFat(sumOfSkinfold,age);
        }
        return CalculateFemaleFat(sumOfSkinfold, age);
    }
    public static float CalculateMaleFat(float sumOfSkinfold, int age){
        return 495f / (1.10938f - 0.0008267f * sumOfSkinfold + 0.0000016f * sumOfSkinfold * sumOfSkinfold - 0.0002574f * age) - 450f;
    }
    public static float CalculateFemaleFat(float sumOfSkinfold, int age){
        return 495f / (1.0994921f - 0.0009929f * sumOfSkinfold + 0.0000023f * sumOfSkinfold * sumOfSkinfold - 0.0001392f * age) - 450f;
    }


    public static float Calculate1Rm(float lift, int repetition){
        ArrayList<Float> oneRm = new ArrayList<Float>();
        oneRm.add(1f);
        oneRm.add(0.97f);
        oneRm.add(0.94f);
        oneRm.add(0.92f);
        oneRm.add(0.89f);
        oneRm.add(0.86f);
        oneRm.add(0.83f);
        oneRm.add(0.81f);
        oneRm.add(0.78f);
        oneRm.add(0.75f);
        oneRm.add(0.73f);
        oneRm.add(0.71f);
        oneRm.add(0.70f);
        oneRm.add(0.68f);
        oneRm.add(0.67f);
        oneRm.add(0.65f);
        oneRm.add(0.64f);
        oneRm.add(0.63f);
        oneRm.add(0.61f);
        oneRm.add(0.60f);
        return lift / oneRm.get(repetition - 1);
    }

}
