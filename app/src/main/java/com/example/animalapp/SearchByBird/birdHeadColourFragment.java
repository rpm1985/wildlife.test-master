package com.example.animalapp.SearchByBird;


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

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class birdHeadColourFragment extends Fragment implements View.OnClickListener{
    ArrayList<String> filterItems = new ArrayList<>();


    public birdHeadColourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bird_head_colour, container, false);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.filter_view);
        if (bundle != null) {
            StringBuilder filter = new StringBuilder();
            if (bundle.containsKey("SpeciesType")) {
                String type = bundle.getString("SpeciesType");
                Log.d("TYPE", type);
                filter.append("Type").append(": ").append(type).append(". ");
            }
            if (bundle.containsKey("BirdHeight")){
                ArrayList<Integer> passedHeight = bundle.getIntegerArrayList("BirdHeight");
                if (passedHeight.size() > 2){
                    Log.d("BIRD HEIGHT ", Integer.toString(passedHeight.get(0)) + ", " + Integer.toString(passedHeight.get(1)));
                    filter.append("Height").append(": >").append(passedHeight.get(0)).append(", <").append(passedHeight.get(1)).append(". ");
                } else {
                    if (passedHeight.get(1).equals(0)) {
                        Log.d("BIRD HEIGHT ", Integer.toString(passedHeight.get(0)));
                        filter.append("Bird Height").append(": >").append(passedHeight.get(0)).append(". ");
                    } else {
                        Log.d("BIRD HEIGHT ", Integer.toString(passedHeight.get(0)));
                        filter.append("Bird Height").append(": <").append(passedHeight.get(0)).append(". ");
                    }
                }
            }if (bundle.containsKey("BirdHeadColour")) {
                ArrayList<String> passedColour = bundle.getStringArrayList("BirdHeadColour");
                Log.d("BIRD HEAD COLOUR", passedColour.toString());
                filter.append("Head").append(": ").append(String.join(", ", passedColour)).append(". ");
            }if (bundle.containsKey("BirdWingColour")) {
                ArrayList<String>  passedColour = bundle.getStringArrayList("BirdWingColour");
                Log.d("BIRD WING COLOUR", passedColour.toString());
                filter.append("Wing").append(": ").append(String.join(", ", passedColour)).append(". ");
            }if (bundle.containsKey("BirdBellyColour")) {
                ArrayList<String> passedColour = bundle.getStringArrayList("BirdBellyColour");
                Log.d("BIRD BELLY COLOUR", passedColour.toString());
                filter.append("Belly").append(": ").append(String.join(", ", passedColour)).append(". ");
            }
            passed_detail.setText("Filter: " + filter);
        }

        Button bird_colour_option_white = (Button) view.findViewById(R.id.bird_colour_option_white);
        Button bird_colour_option_yellow = (Button) view.findViewById(R.id.bird_colour_option_yellow);
        Button bird_colour_option_grey = (Button) view.findViewById(R.id.bird_colour_option_grey);

        Button bird_colour_option_red = (Button) view.findViewById(R.id.bird_colour_option_red);
        Button bird_colour_option_blue = (Button) view.findViewById(R.id.bird_colour_option_blue);
        Button bird_colour_option_black = (Button) view.findViewById(R.id.bird_colour_option_black);

        Button bird_colour_option_green = (Button) view.findViewById(R.id.bird_colour_option_green);
        Button bird_colour_option_orange = (Button) view.findViewById(R.id.bird_colour_option_orange);
        Button bird_colour_option_brown = (Button) view.findViewById(R.id.bird_colour_option_brown);

        bird_colour_option_white.setOnClickListener(this);
        bird_colour_option_yellow.setOnClickListener(this);
        bird_colour_option_grey.setOnClickListener(this);
        bird_colour_option_red.setOnClickListener(this);
        bird_colour_option_blue.setOnClickListener(this);
        bird_colour_option_black.setOnClickListener(this);
        bird_colour_option_green.setOnClickListener(this);
        bird_colour_option_orange.setOnClickListener(this);
        bird_colour_option_brown.setOnClickListener(this);


        Button species_back_btn = (Button) view.findViewById(R.id.species_back_button);
        Button species_skip_btn = (Button) view.findViewById(R.id.species_skip_button);
        species_back_btn.setOnClickListener(this);
        species_skip_btn.setOnClickListener(this);
        return view;
    }
    public void setHeadColour(ArrayList<String> values, Bundle bundle, View view){
        bundle.putStringArrayList("BirdHeadColour", values);
        Navigation.findNavController(view).navigate(R.id.action_birdHeadColourFragment_to_birdWingColourFragment,bundle);

    }

    @Override
    public void onClick(View v) {
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            Log.i("NULL BUNDLE", "NOT WORKED");
            bundle = new Bundle();
        }

        int i = v.getId();
        if (i == R.id.bird_colour_option_white) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("White"));
            setHeadColour(colours, bundle, v);

        } if (i == R.id.bird_colour_option_yellow) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Yellow"));
            setHeadColour(colours, bundle, v);

        } if (i == R.id.bird_colour_option_grey) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Grey"));
            setHeadColour(colours, bundle, v);

        } if (i == R.id.bird_colour_option_red) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Red"));
            setHeadColour(colours, bundle, v);

        } if (i == R.id.bird_colour_option_blue) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Blue"));
            setHeadColour(colours, bundle, v);

        } if (i == R.id.bird_colour_option_black) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Black"));
            setHeadColour(colours, bundle, v);

        }if (i == R.id.bird_colour_option_green) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Green"));
            setHeadColour(colours, bundle, v);
        }if (i == R.id.bird_colour_option_brown) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Brown"));
            setHeadColour(colours, bundle, v);
        }if (i == R.id.bird_colour_option_orange) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Orange"));
            setHeadColour(colours, bundle, v);
        }
        if (i == R.id.species_back_button) {
//            bundle.remove("BirdHeadColour");
            Navigation.findNavController(v).navigate(R.id.action_birdHeadColourFragment_to_birdHeightFragment,bundle);
        }
        if (i == R.id.species_skip_button) {
            bundle.remove("BirdHeadColour");
            Navigation.findNavController(v).navigate(R.id.action_birdHeadColourFragment_to_birdWingColourFragment,bundle);
        }

    }

}

