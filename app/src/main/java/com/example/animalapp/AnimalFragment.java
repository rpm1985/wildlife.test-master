package com.example.animalapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.animalapp.SearchByBird.SpeciesIdentifierResult;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimalFragment extends Fragment implements View.OnClickListener{


    public AnimalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_animal_menu);
        View view = inflater.inflate(R.layout.fragment_animal_dictionary, container, false);
        Button species_identifier_btn = (Button) view.findViewById(R.id.species_identifier_button);
        species_identifier_btn.setOnClickListener(this);

        Button animals_seen = (Button) view.findViewById(R.id.animals_seen);
        animals_seen.setOnClickListener(this);


        return view;

    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        int i = v.getId();
        if (i == R.id.species_identifier_button) {
            fragment = new SpeciesIdentifierResult();
            Bundle bundle = new Bundle();
            bundle.putString("Mode", "Search");
            fragment.setArguments(bundle);
            replaceFragment(fragment);

        }
        if (i == R.id.animals_seen) {
            fragment = new AnimalListFragment();
            replaceFragment(fragment);

        }

    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
