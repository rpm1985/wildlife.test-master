package com.example.animalapp.SearchByReptile;


import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.animalapp.R;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 */
public class reptileSkinColourFragment extends Fragment implements View.OnClickListener {


    public reptileSkinColourFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reptile_skin_colour, container, false);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.filter_view);
        if (bundle != null) {
            StringBuilder filter = new StringBuilder();
            if (bundle.containsKey("SpeciesType")) {
                String type = bundle.getString("SpeciesType");
                Log.d("TYPE", type);
                filter.append("Type").append(": ").append(type).append(". ");
            }
            if (bundle.containsKey("ReptileLength")){
                ArrayList<Integer> passedHeight = bundle.getIntegerArrayList("ReptileLength");
                if (passedHeight.size() > 2){
                    Log.d("Reptile LENGTH ", Integer.toString(passedHeight.get(0)) + ", " + Integer.toString(passedHeight.get(1)));
                    filter.append("Length").append(": >").append(passedHeight.get(0)).append(", <").append(passedHeight.get(1)).append(". ");
                } else {
                    if (passedHeight.get(1).equals(0)) {
                        Log.d("Reptile LENGTH ", Integer.toString(passedHeight.get(0)));
                        filter.append("Reptile Length").append(": <").append(passedHeight.get(0)).append(". ");
                    } else {
                        Log.d("Reptile LENGTH ", Integer.toString(passedHeight.get(0)));
                        filter.append("Reptile LENGTH").append(": >").append(passedHeight.get(0)).append(". ");
                    }
                }
            }if (bundle.containsKey("ReptileSkinColour")) {
                ArrayList<String> passedColour = bundle.getStringArrayList("ReptileSkinColour");
                Log.d("Reptile SKIN COLOUR", passedColour.toString());
                filter.append("Skin").append(": ").append(String.join(", ", passedColour)).append(". ");
            }if (bundle.containsKey("ReptileMarkings")) {
                ArrayList<String>  passedMarkings = bundle.getStringArrayList("ReptileMarkings");
                Log.d("Reptile MARKINGS ", passedMarkings.toString());
                filter.append("Markings").append(": ").append(String.join(", ", passedMarkings)).append(". ");
            }
            passed_detail.setText("Filter: " + filter);
        }

        Button reptile_colour_option_brown = (Button) view.findViewById(R.id.reptile_colour_option_brown);
        Button reptile_colour_option_yellow = (Button) view.findViewById(R.id.reptile_colour_option_yellow);
        Button reptile_colour_option_orange = (Button) view.findViewById(R.id.reptile_colour_option_orange);

        Button reptile_colour_option_grey = (Button) view.findViewById(R.id.reptile_colour_option_grey);
        Button reptile_colour_option_red = (Button) view.findViewById(R.id.reptile_colour_option_red);
        Button reptile_colour_option_silver = (Button) view.findViewById(R.id.reptile_colour_option_silver);

        Button reptile_colour_option_gold = (Button) view.findViewById(R.id.reptile_colour_option_gold);
        Button reptile_colour_option_green = (Button) view.findViewById(R.id.reptile_colour_option_green);
        Button reptile_colour_option_black = (Button) view.findViewById(R.id.reptile_colour_option_black);
        
        reptile_colour_option_brown.setOnClickListener(this);
        reptile_colour_option_yellow.setOnClickListener(this);
        reptile_colour_option_orange.setOnClickListener(this);
        reptile_colour_option_grey.setOnClickListener(this);
        reptile_colour_option_red.setOnClickListener(this);
        reptile_colour_option_silver.setOnClickListener(this);
        reptile_colour_option_gold.setOnClickListener(this);
        reptile_colour_option_green.setOnClickListener(this);
        reptile_colour_option_black.setOnClickListener(this);

        Button species_back_btn = (Button) view.findViewById(R.id.species_back_button);
        Button species_skip_btn = (Button) view.findViewById(R.id.species_skip_button);
        species_back_btn.setOnClickListener(this);
        species_skip_btn.setOnClickListener(this);
        return view;
    }
    public void setSkinColour(ArrayList<String> values, Bundle bundle, View view){
        bundle.putStringArrayList("ReptileSkinColour", values);
        Navigation.findNavController(view).navigate(R.id.action_reptileSkinColourFragment_to_reptileMarkingsFragment,bundle);

    }

    @Override
    public void onClick(View v) {
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            Log.i("NULL BUNDLE", "NOT WORKED");
            bundle = new Bundle();
        }

        int i = v.getId();
        if (i == R.id.reptile_colour_option_brown) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Brown"));
            setSkinColour(colours, bundle, v);

        } if (i == R.id.reptile_colour_option_yellow) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Yellow"));
            setSkinColour(colours, bundle, v);

        } if (i == R.id.reptile_colour_option_orange) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Orange"));
            setSkinColour(colours, bundle, v);

        } if (i == R.id.reptile_colour_option_grey) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Grey"));
            setSkinColour(colours, bundle, v);

        } if (i == R.id.reptile_colour_option_red) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Red"));
            setSkinColour(colours, bundle, v);

        } if (i == R.id.reptile_colour_option_silver) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Silver"));
            setSkinColour(colours, bundle, v);
        } if (i == R.id.reptile_colour_option_gold) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Gold"));
            setSkinColour(colours, bundle, v);

        } if (i == R.id.reptile_colour_option_green) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Green"));
            setSkinColour(colours, bundle, v);

        } if (i == R.id.reptile_colour_option_black) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Black"));
            setSkinColour(colours, bundle, v);

        } if (i == R.id.species_back_button) {
            Navigation.findNavController(v).navigate(R.id.action_reptileSkinColourFragment_to_reptileLengthFragment,bundle);

        } if (i == R.id.species_skip_button) {
            bundle.remove("ReptileSkinColour");
            Navigation.findNavController(v).navigate(R.id.action_reptileSkinColourFragment_to_reptileMarkingsFragment,bundle);
        }

    }

}
