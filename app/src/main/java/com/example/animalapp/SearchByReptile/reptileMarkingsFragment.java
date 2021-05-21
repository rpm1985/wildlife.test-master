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
public class reptileMarkingsFragment extends Fragment implements View.OnClickListener {


    public reptileMarkingsFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reptile_markings, container, false);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.filter_view);
        if (bundle != null) {
            StringBuilder filter = new StringBuilder();
            if (bundle.containsKey("SpeciesType")) {
                String type = bundle.getString("SpeciesType");
                Log.d("TYPE", type);
                filter.append("Type").append(": ").append(type).append(". ");
            }if (bundle.containsKey("ReptileLength")){
                ArrayList<Integer> passedHeight = bundle.getIntegerArrayList("ReptileLength");
                if (passedHeight.size() > 2){
                    Log.d("Reptile LENGTH ", Integer.toString(passedHeight.get(0)) + ", " + Integer.toString(passedHeight.get(1)));
                    filter.append("Height").append(": >").append(passedHeight.get(0)).append(", <").append(passedHeight.get(1)).append(". ");
                } else {
                    if (passedHeight.get(1).equals(0)) {
                        Log.d("Reptile LENGTH ", Integer.toString(passedHeight.get(0)));
                        filter.append("Reptile Height").append(": <").append(passedHeight.get(0)).append(". ");
                    } else {
                        Log.d("Reptile LENGTH ", Integer.toString(passedHeight.get(0)));
                        filter.append("Reptile Height").append(": >").append(passedHeight.get(0)).append(". ");
                    }
                }
            }if (bundle.containsKey("ReptileHeadColour")) {
                ArrayList<String> passedColour = bundle.getStringArrayList("ReptileHeadColour");
                Log.d("Reptile HEAD COLOUR", passedColour.toString());
                filter.append("Head").append(": ").append(String.join(", ", passedColour)).append(". ");
            }if (bundle.containsKey("ReptileMarkingsColour")) {
                ArrayList<String>  passedMarkings = bundle.getStringArrayList("ReptileMarkingsColour");
                Log.d("Reptile MARKINGS", passedMarkings.toString());
                filter.append("Markings").append(": ").append(String.join(", ", passedMarkings)).append(". ");
            }

            passed_detail.setText("Filter: " + filter);
        }

        Button reptile_stripes = (Button) view.findViewById(R.id.reptile_stripes);
        Button reptile_spots = (Button) view.findViewById(R.id.reptile_spots);
        Button reptile_streaks = (Button) view.findViewById(R.id.reptile_streaks);

        Button reptile_warty = (Button) view.findViewById(R.id.reptile_warty);

        reptile_stripes.setOnClickListener(this);
        reptile_spots.setOnClickListener(this);
        reptile_streaks.setOnClickListener(this);
        reptile_warty.setOnClickListener(this);

        Button species_back_btn = (Button) view.findViewById(R.id.species_back_button);
        Button species_skip_btn = (Button) view.findViewById(R.id.species_skip_button);
        species_back_btn.setOnClickListener(this);
        species_skip_btn.setOnClickListener(this);
        return view;
    }
    public void setMarkings(ArrayList<String> values, Bundle bundle, View view){
        bundle.putStringArrayList("ReptileMarkings", values);
        Navigation.findNavController(view).navigate(R.id.action_reptileMarkingsFragment_to_reptileResultFragment,bundle);

    }

    @Override
    public void onClick(View v) {
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            Log.i("NULL BUNDLE", "NOT WORKED");
            bundle = new Bundle();
        }

        int i = v.getId();
        if (i == R.id.reptile_stripes) {
            ArrayList<String> markings = new ArrayList<>();
            markings.add(("Stripes"));
            setMarkings(markings, bundle, v);

        } if (i == R.id.reptile_spots) {
            ArrayList<String> markings = new ArrayList<>();
            markings.add(("Spots"));
            setMarkings(markings, bundle, v);
            
        } if (i == R.id.reptile_streaks) {
            ArrayList<String> markings = new ArrayList<>();
            markings.add(("Streaks"));
            setMarkings(markings, bundle, v);
            
        } if (i == R.id.reptile_warty) {
            ArrayList<String> markings = new ArrayList<>();
            markings.add(("Warty"));
            setMarkings(markings, bundle, v);
            
        }
        if (i == R.id.species_back_button) {
            Navigation.findNavController(v).navigate(R.id.action_reptileMarkingsFragment_to_reptileSkinColourFragment,bundle);
        }
        if (i == R.id.species_skip_button) {
            bundle.remove("ReptileMarkingsColour");
            Navigation.findNavController(v).navigate(R.id.action_reptileMarkingsFragment_to_reptileResultFragment,bundle);
        }

    }

}
