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
import java.util.Arrays;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class BirdHeightFragment extends Fragment implements View.OnClickListener {



    public BirdHeightFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bird_height, container, false);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.filter_views);
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


        Button bird_height_option_one = (Button) view.findViewById(R.id.bird_height_option_1);
        Button bird_height_option_two = (Button) view.findViewById(R.id.bird_height_option_2);
        Button bird_height_option_three = (Button) view.findViewById(R.id.bird_height_option_3);

        bird_height_option_one.setOnClickListener(this);
        bird_height_option_two.setOnClickListener(this);
        bird_height_option_three.setOnClickListener(this);

        Button species_back_btn = (Button) view.findViewById(R.id.species_back_button);
        Button species_skip_btn = (Button) view.findViewById(R.id.species_skip_button);
        species_back_btn.setOnClickListener(this);
        species_skip_btn.setOnClickListener(this);


        return view;
    }
    public Bundle setBirdHeight(ArrayList<Integer> values, Bundle bundle){
        bundle.putIntegerArrayList("BirdHeight", values);
        return bundle;
    }

    @Override
    public void onClick(View v) {

        Bundle bundle = this.getArguments();
        if (bundle == null) {
            Log.i("NULL BUNDLE", "NOT WORKED");
            bundle = new Bundle();
        }
        int i = v.getId();

        if (i == R.id.bird_height_option_1) {
            ArrayList<Integer> values = new ArrayList<>();
            values.addAll(Arrays.asList(15,0));
            setBirdHeight(values, bundle);
            Navigation.findNavController(v).navigate(R.id.action_birdHeightFragment_to_birdHeadColourFragment,bundle);
        }else if (i == R.id.bird_height_option_2) {
            ArrayList<Integer> values = new ArrayList<>();
            values.addAll(Arrays.asList(15,30,0));
            setBirdHeight(values, bundle);
            Navigation.findNavController(v).navigate(R.id.action_birdHeightFragment_to_birdHeadColourFragment,bundle);
        }else if (i == R.id.bird_height_option_3) {
            ArrayList<Integer> values = new ArrayList<>();
            values.addAll(Arrays.asList(30,1));
            setBirdHeight(values, bundle);
            Navigation.findNavController(v).navigate(R.id.action_birdHeightFragment_to_birdHeadColourFragment,bundle);
        }
        if (i == R.id.species_back_button) {
//            bundle.remove("BirdHeight");
            Navigation.findNavController(v).navigate(R.id.action_birdHeightFragment_to_chooseSpecies,bundle);
        }
        if (i == R.id.species_skip_button) {
            bundle.remove("BirdHeight");
            Navigation.findNavController(v).navigate(R.id.action_birdHeightFragment_to_birdHeadColourFragment,bundle);
        }


    }
}
