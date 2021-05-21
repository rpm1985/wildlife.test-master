package com.example.animalapp;


import android.os.AsyncTask;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.animalapp.Database.Animal;
import com.example.animalapp.Database.AnimalDatabase;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpeciesIdentifier extends Fragment {
    AnimalDatabase db;


    public SpeciesIdentifier() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_species_identifier);
        View view = inflater.inflate(R.layout.fragment_species_identifier, container, false);

        Log.d("THINGS IN SPECIES FRAG", view.toString());
        db = AnimalDatabase.getDatabase(getContext());

        addData();

        return view;
    }
    public AnimalDatabase getDb() {
        return db;
    }
    private void addData() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.animalDAO().clearAnimal();

                List<Animal> list = null;
                try {
                    list = readAnimalListFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                db.animalDAO().insertAll(list);

//                final List<Animal> allAnimals = db.animalDAO().getAllAnimals();

//                Log.d("List All Animals: ", "AllAnimals: " + allAnimals);
            }
        });
    }
    private List<Animal> readAnimalListFile() throws IOException {

        List<Animal> animalList = new ArrayList<>();

        InputStream is = getResources().openRawResource(R.raw.animal_list);
        InputStreamReader csvStreamReader = new InputStreamReader(is);

        CSVReader reader = new CSVReader(csvStreamReader);
        reader.skip(1);

        String[] record = null;
        int num = 0;


        while ((record = reader.readNext()) != null) {
//            Animal animal = new Animal(record[0], record[1], record[2],
//                    record[3], record[4], record[5], record[6],
//                    record[7], record[8], record[9], record[10], record[11]);
            Animal animal = new Animal();
            animal.setType(record[0]);
            animal.setName(record[1]);
            animal.setScientificName(record[2]);
//            animal.setMinBodyLengthCm(record[3]);
//            animal.setMaxBodyLengthCm(record[4]);
//            animal.setMinWingspanCm(record[5]);
//            animal.setMaxWingspanCm(record[6]);
            animal.setMinBodyLengthCm(Integer.parseInt(record[3]));
            animal.setMaxBodyLengthCm(Integer.parseInt(record[4]));

            try{
                if (record[5] != null && record[6] != null) {
                    animal.setMinWingspanCm(Integer.parseInt(record[5]));
                    animal.setMaxWingspanCm(Integer.parseInt(record[6]));
                } else {
                    animal.setMinWingspanCm(num);
                    animal.setMaxWingspanCm(num);
                }
            }catch (NumberFormatException e) {
                Log.getStackTraceString(e);
            }

//                animal.setMinWingspanCm(Integer.parseInt(record[5]));
//                animal.setMaxWingspanCm(Integer.parseInt(record[6]));
            animal.setDescription(record[7]);
            animal.setHabitat(record[8]);
            animal.setBestTime(record[9]);
            animal.setBestWalk(record[10]);
            animal.setFoodSource(record[11]);
            animal.setAnimalImage(record[12]);
            animal.setHeadColour(record[14]);
            animal.setWingColour(record[15]);
            animal.setBellyColour(record[16]);
            animal.setFurColour(record[17]);
            animal.setSkinColour(record[18]);
            animal.setMarkings(record[19]);

            animalList.add(animal);
//                Log.d("Read file: ", "Animal List: " + animal);

        }
        return animalList;
    }
}
