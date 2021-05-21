package com.example.animalapp.SearchByBird;

import android.icu.util.LocaleData;
import android.util.Log;

import com.example.animalapp.Database.Animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MammalSearchTools {

     static ArrayList<Animal> searchBySize(ArrayList<Animal> animalList, List<Integer> sizesList){
        Log.d("STARTING", "THIS SEARCH BY SIZE HAS STARTED "+ sizesList);
        ArrayList<Animal> resultList = new ArrayList<>();
        for (Animal animal :
                animalList) {
            if (sizesList.size() > 2){
                Log.d("FIRST VALUE EVAL", Integer.toString(sizesList.get(0)) + " " + Integer.toString(animal.getMinBodyLengthCm()));
                Log.d("SECOND VALUE EVAL", Integer.toString(sizesList.get(1)) + " " + Integer.toString(animal.getMaxBodyLengthCm()));
                Log.d("FIRST EXPRESSION EVAL", Boolean.toString((sizesList.get(0) > animal.getMinBodyLengthCm())));
                Log.d("SECOND EXPRESSION EVAL", Boolean.toString((sizesList.get(1) < animal.getMaxBodyLengthCm())));
                if(((sizesList.get(0) >= animal.getMinBodyLengthCm() && sizesList.get(0) <= animal.getMaxBodyLengthCm())
                        ||(sizesList.get(1) >= animal.getMinBodyLengthCm() && sizesList.get(1) <= animal.getMaxBodyLengthCm()))
                        && (animal.getType().equalsIgnoreCase("Mammal")) ){
                    Log.d("WOKROG","JKLM");
                    resultList.add(animal);
                }
//                if((sizesList.get(0) > animal.getMinBodyLengthCm()) && (sizesList.get(1) < animal.getMaxBodyLengthCm()) && (animal.getType().equalsIgnoreCase("Mammal"))) {
//                    resultList.add(animal);
//                }
                Log.d("MID", "THIS PROCESS HAS WORKED");

            } else{
                if(sizesList.get(1).equals(0)){
                    if((sizesList.get(0) > animal.getMinBodyLengthCm())&& (animal.getType().equalsIgnoreCase("Mammal"))){
                        resultList.add(animal);
                    }
                }
                if(sizesList.get(1).equals(1)){
                    if((sizesList.get(0) < animal.getMaxBodyLengthCm()) && (animal.getType().equalsIgnoreCase("Mammal"))){
                        resultList.add(animal);
                    }
                }

                Log.d("MID", "THIS PROCESS HAS WORKED");
            }
        }
//        for (Animal animal: resultList){
//            Log.d("RSEULT ANIMAL", animal.toString());
//        }
        return resultList;

    }
     static ArrayList<Animal> searchByHeadColour(ArrayList<Animal> animalList, List<String> headColours){
        ArrayList<Animal> resultList = new ArrayList<>();
        Log.d("STARTING", "THIS SEARCH BY HEAD COLOUR HAS STARTED "+ headColours);
        for (Animal animal :
                animalList) {
            ArrayList<String> colours = new ArrayList<>(Arrays.asList(animal.getHeadColour().split(";")));
            if (colours.containsAll(headColours)) {
                resultList.add(animal);
            }
        }
        return resultList;

    }

    static ArrayList<Animal> searchByFurColour(ArrayList<Animal> animalList, List<String> furColours){
        ArrayList<Animal> resultList = new ArrayList<>();
        Log.d("STARTING", "THIS SEARCH BY Fur COLOUR HAS STARTED "+ furColours);
        for (Animal animal :
                animalList) {
            ArrayList<String> colours = new ArrayList<>(Arrays.asList(animal.getFurColour().split(";")));
            if (colours.containsAll(furColours)) {
                resultList.add(animal);
            }
        }
        return resultList;

    }




}
