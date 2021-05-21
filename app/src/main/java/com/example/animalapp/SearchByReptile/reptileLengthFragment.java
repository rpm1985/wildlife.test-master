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
import java.util.Arrays;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 */
public class reptileLengthFragment extends Fragment implements View.OnClickListener {


    public reptileLengthFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reptile_length, container, false);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.filter_views);
        if (bundle != null) {
            StringBuilder filter = new StringBuilder();
            if (bundle.containsKey("SpeciesType")) {
                String type = bundle.getString("SpeciesType");
                Log.d("TYPE", type);
                filter.append("Type").append(": ").append(type).append(". ");
            } if (bundle.containsKey("ReptileLength")){
                ArrayList<Integer> passedLength = bundle.getIntegerArrayList("ReptileLength");
                if (passedLength.size() > 2){
                    Log.d("Reptile LENGTH ", Integer.toString(passedLength.get(0)) + ", " + Integer.toString(passedLength.get(1)));
                    filter.append("Length").append(": >").append(passedLength.get(0)).append(", <").append(passedLength.get(1)).append(". ");
                } else {
                    if (passedLength.get(1).equals(0)) {
                        Log.d("Reptile LENGTH ", Integer.toString(passedLength.get(0)));
                        filter.append("Reptile Length").append(": <").append(passedLength.get(0)).append(". ");
                    } else {
                        Log.d("Reptile LENGTH ", Integer.toString(passedLength.get(0)));
                        filter.append("Reptile Length").append(": >").append(passedLength.get(0)).append(". ");
                    }
                }
            }if (bundle.containsKey("ReptileSkinColour")) {
                ArrayList<String> passedColour = bundle.getStringArrayList("ReptileSkinColour");
                Log.d("Reptile SKIN COLOUR", passedColour.toString());
                filter.append("Skin").append(": ").append(String.join(", ", passedColour)).append(". ");
            }if (bundle.containsKey("ReptileMarkings")) {
                ArrayList<String>  passedMarkings = bundle.getStringArrayList("ReptileMarkings");
                Log.d("Reptile MARKINGS", passedMarkings.toString());
                filter.append("Markings").append(": ").append(String.join(", ", passedMarkings)).append(". ");
            }
            passed_detail.setText("Filter: " + filter);
        }

        Button reptile_length_option_one = (Button) view.findViewById(R.id.reptile_length_option_1);
        Button reptile_length_option_two = (Button) view.findViewById(R.id.reptile_length_option_2);
        Button reptile_length_option_three = (Button) view.findViewById(R.id.reptile_length_option_3);

        reptile_length_option_one.setOnClickListener(this);
        reptile_length_option_two.setOnClickListener(this);
        reptile_length_option_three.setOnClickListener(this);

        Button species_back_btn = (Button) view.findViewById(R.id.species_back_button);
        Button species_skip_btn = (Button) view.findViewById(R.id.species_skip_button);
        species_back_btn.setOnClickListener(this);
        species_skip_btn.setOnClickListener(this);

        return view;
    }
    public Bundle setReptileLength(ArrayList<Integer> values, Bundle bundle){
        bundle.putIntegerArrayList("ReptileLength", values);
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

        if (i == R.id.reptile_length_option_1) {
            ArrayList<Integer> values = new ArrayList<>();
            values.addAll(Arrays.asList(15,0));
            setReptileLength(values, bundle);
            Navigation.findNavController(v).navigate(R.id.action_reptileLengthFragment_to_reptileSkinColourFragment,bundle);
        }else if (i == R.id.reptile_length_option_2) {
            ArrayList<Integer> values = new ArrayList<>();
            values.addAll(Arrays.asList(15,30,0));
            setReptileLength(values, bundle);
            Navigation.findNavController(v).navigate(R.id.action_reptileLengthFragment_to_reptileSkinColourFragment,bundle);
        }else if (i == R.id.reptile_length_option_3) {
            ArrayList<Integer> values = new ArrayList<>();
            values.addAll(Arrays.asList(30,1));
            setReptileLength(values, bundle);
            Navigation.findNavController(v).navigate(R.id.action_reptileLengthFragment_to_reptileSkinColourFragment,bundle);
        }
        if (i == R.id.species_back_button) {
            Navigation.findNavController(v).navigate(R.id.action_reptileLengthFragment_to_chooseSpecies,bundle);
        }
        if (i == R.id.species_skip_button) {
            bundle.remove("ReptileLength");
            Navigation.findNavController(v).navigate(R.id.action_reptileLengthFragment_to_reptileSkinColourFragment,bundle);
        }
    }

}
