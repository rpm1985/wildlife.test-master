package com.example.animalapp.SearchByMammal;


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
public class mammalHeightFragment extends Fragment implements View.OnClickListener {


    public mammalHeightFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mammal_height, container, false);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.filter_views);
        if (bundle != null) {
            StringBuilder filter = new StringBuilder();
            if (bundle.containsKey("SpeciesType")) {
                String type = bundle.getString("SpeciesType");
                Log.d("TYPE", type);
                filter.append("Type").append(": ").append(type).append(". ");
            } if (bundle.containsKey("MammalHeight")){
                ArrayList<Integer> passedHeight = bundle.getIntegerArrayList("MammalHeight");
                if (passedHeight.size() > 2){
                    Log.d("Mammal HEIGHT ", Integer.toString(passedHeight.get(0)) + ", " + Integer.toString(passedHeight.get(1)));
                    filter.append("Height").append(": >").append(passedHeight.get(0)).append(", <").append(passedHeight.get(1)).append(". ");
                } else {
                    if (passedHeight.get(1).equals(0)) {
                        Log.d("Mammal HEIGHT ", Integer.toString(passedHeight.get(0)));
                        filter.append("Mammal Height").append(": <").append(passedHeight.get(0)).append(". ");
                    } else {
                        Log.d("Mammal HEIGHT ", Integer.toString(passedHeight.get(0)));
                        filter.append("Mammal Height").append(": >").append(passedHeight.get(0)).append(". ");
                    }
                }
            }if (bundle.containsKey("MammalHeadColour")) {
                ArrayList<String> passedColour = bundle.getStringArrayList("MammalHeadColour");
                Log.d("Mammal HEAD COLOUR", passedColour.toString());
                filter.append("Head").append(": ").append(String.join(", ", passedColour)).append(". ");
            }if (bundle.containsKey("MammalFurColour")) {
                ArrayList<String>  passedColour = bundle.getStringArrayList("MammalFurColour");
                Log.d("Mammal FUR COLOUR", passedColour.toString());
                filter.append("Fur").append(": ").append(String.join(", ", passedColour)).append(". ");
            }
            passed_detail.setText("Filter: " + filter);
        }

        Button mammal_height_option_one = (Button) view.findViewById(R.id.mammal_height_option_1);
        Button mammal_height_option_two = (Button) view.findViewById(R.id.mammal_height_option_2);
        Button mammal_height_option_three = (Button) view.findViewById(R.id.mammal_height_option_3);

        mammal_height_option_one.setOnClickListener(this);
        mammal_height_option_two.setOnClickListener(this);
        mammal_height_option_three.setOnClickListener(this);

        Button species_back_btn = (Button) view.findViewById(R.id.species_back_button);
        Button species_skip_btn = (Button) view.findViewById(R.id.species_skip_button);
        species_back_btn.setOnClickListener(this);
        species_skip_btn.setOnClickListener(this);

        return view;
    }
    public Bundle setMammalHeight(ArrayList<Integer> values, Bundle bundle){
        bundle.putIntegerArrayList("MammalHeight", values);
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

        if (i == R.id.mammal_height_option_1) {
            ArrayList<Integer> values = new ArrayList<>();
            values.addAll(Arrays.asList(15,0));
            setMammalHeight(values, bundle);
            Navigation.findNavController(v).navigate(R.id.action_mammalHeightFragment_to_mammalHeadColourFragment,bundle);
        }else if (i == R.id.mammal_height_option_2) {
            ArrayList<Integer> values = new ArrayList<>();
            values.addAll(Arrays.asList(15,30,0));
            setMammalHeight(values, bundle);
            Navigation.findNavController(v).navigate(R.id.action_mammalHeightFragment_to_mammalHeadColourFragment,bundle);
        }else if (i == R.id.mammal_height_option_3) {
            ArrayList<Integer> values = new ArrayList<>();
            values.addAll(Arrays.asList(30,1));
            setMammalHeight(values, bundle);
            Navigation.findNavController(v).navigate(R.id.action_mammalHeightFragment_to_mammalHeadColourFragment,bundle);
        }
        if (i == R.id.species_back_button) {
            Navigation.findNavController(v).navigate(R.id.action_mammalHeightFragment_to_chooseSpecies,bundle);
        }
        if (i == R.id.species_skip_button) {
            bundle.remove("MammalHeight");
            Navigation.findNavController(v).navigate(R.id.action_mammalHeightFragment_to_mammalHeadColourFragment,bundle);
        }
    }
}
