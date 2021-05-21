package com.example.animalapp;


import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseSpecies extends Fragment implements View.OnClickListener {


    public ChooseSpecies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_species, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            bundle.remove("BirdHeight");
            bundle.remove("BirdColour");
            bundle.remove("SpeciesType");

        }
        Button species_bird_btn = (Button) view.findViewById(R.id.species_bird_button);
        Button species_mammal_btn = (Button) view.findViewById(R.id.species_mammal_button);
        Button species_reptile_btn = (Button) view.findViewById(R.id.species_reptile_button);
        Button species_amphibian_btn = (Button) view.findViewById(R.id.species_amphibian_button);
        species_bird_btn.setOnClickListener(this);
        species_mammal_btn.setOnClickListener(this);
        species_reptile_btn.setOnClickListener(this);
        species_amphibian_btn.setOnClickListener(this);




        return view;
    }

    public void working(){
        Log.d("WOACH", "THIS METHOD WAS ACCESSED");
    }


    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        int i = v.getId();
        if (i == R.id.species_bird_button) {
            bundle.putString("SpeciesType", "Bird");
            Navigation.findNavController(v).navigate(R.id.action_chooseSpecies_to_birdHeightFragment,bundle);

        } if (i == R.id.species_mammal_button) {
            bundle.putString("SpeciesType", "Mammal");
            Navigation.findNavController(v).navigate(R.id.action_chooseSpecies_to_mammalHeightFragment,bundle);
        } if (i == R.id.species_reptile_button) {
            bundle.putString("SpeciesType", "Reptile");
            Navigation.findNavController(v).navigate(R.id.action_chooseSpecies_to_reptileLengthFragment,bundle);
        } if (i == R.id.species_amphibian_button) {
            bundle.putString("SpeciesType", "Amphibian");
            Navigation.findNavController(v).navigate(R.id.action_chooseSpecies_to_reptileLengthFragment,bundle);
        }


    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, someFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
//        getChildFragmentManager().beginTransaction().remove(getTargetFragment()).commit();
//        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
