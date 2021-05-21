package com.example.animalapp.SearchByBird;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animalapp.Database.Animal;
import com.example.animalapp.Database.AnimalDatabase;

import com.example.animalapp.MainActivity;
import com.example.animalapp.R;
import com.example.animalapp.SpeciesIdentifier;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpeciesIdentifierResult extends Fragment implements View.OnClickListener {
    Bundle bundle;
    int numberOfFilters = 0;
    ArrayList<String> filters = new ArrayList<>();
    ListView list;
    View view;

    ChipGroup[] birdFilterChipGroups;
    ChipGroup[] mammalFilterChipGroups;
    ChipGroup[] reptileAmphibianFilterChipGroups;

    List<String> birdFilters = Arrays.asList("BirdHeight", "BirdHeadColour", "BirdBellyColour", "BirdWingColour");
    List<String> mammalFilters = Arrays.asList("MammalHeight", "MammalHeadColour", "MammalFurColour");
    List<String> reptileAmphibianFilters = Arrays.asList("ReptileAmphibianHeight", "ReptileAmphibianSkinColour");
    public SpeciesIdentifierResult() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_species_identifier_result, container, false);
        final ChipGroup chipGroup = (ChipGroup) view.findViewById(R.id.species_type_chips);
        final ChipGroup birdHeightChipGroup = (ChipGroup) view.findViewById(R.id.bird_choices_height);
        final ChipGroup birdHeadColourChips =(ChipGroup) view.findViewById(R.id.bird_choices_colour_head);
        final ChipGroup birdBellyColourChips =(ChipGroup) view.findViewById(R.id.bird_choices_colour_belly);
        final ChipGroup birdFeatherColourChips =(ChipGroup) view.findViewById(R.id.bird_choices_colour_feather);
        birdFilterChipGroups = new ChipGroup[] {birdHeightChipGroup,birdHeadColourChips,birdBellyColourChips,birdFeatherColourChips};
//        Bundle bundle = this.getArguments();

        final ChipGroup mammalHeightChipGroup = (ChipGroup) view.findViewById(R.id.mammal_choices_height);
        final ChipGroup mammalFurColourChipGroup = (ChipGroup) view.findViewById(R.id.mammal_choices_colour_fur);
        final ChipGroup mammalHeadColourChipGroup = (ChipGroup) view.findViewById(R.id.mammal_choices_colour_head);
        mammalFilterChipGroups = new ChipGroup[] {mammalHeightChipGroup,mammalFurColourChipGroup,mammalHeadColourChipGroup};

        final ChipGroup reptileAmphibianHeightChipGroup = (ChipGroup) view.findViewById(R.id.reptile_amphibian_choices_height);
        final ChipGroup reptileAmphibianSkinColourChipGroup = (ChipGroup) view.findViewById(R.id.reptile_amphibian_choices_colour_skin);
        final ChipGroup reptileAmphibianMarkingsChipGroup = (ChipGroup) view.findViewById(R.id.reptile_amphibian_choices_markings);
        reptileAmphibianFilterChipGroups = new ChipGroup[] {reptileAmphibianHeightChipGroup,reptileAmphibianSkinColourChipGroup,reptileAmphibianMarkingsChipGroup};


        list = view.findViewById(R.id.result_list_view);
        bundle = this.getArguments();
        Button species_back_btn = (Button) view.findViewById(R.id.species_back_button);
        species_back_btn.setOnClickListener(this);
        updateResultList(new Bundle());
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {
                Chip chip = ((Chip) chipGroup.findViewById(i));
                Log.d("SPEaRTAER","----------------------------------------------");

                Log.d("CHIP ID", Integer.toString(i));

                if (chip != null) {
                    Log.d("CHIP ID", chipGroup.findViewById(i).toString());
                    Log.d("CHIP VIEW", chip.toString());
                    Log.d("BEFORE FOR LOOP", "THIS IS BEING RUN");

                    for (int value = 0; value < chipGroup.getChildCount(); ++value) {
                        Log.d("SPEaRTAER FOR LOOP","----------------------------------------------");
                        Log.d("INSIDE FOR LOOP", "THIS IS BEING RUN");
                        Log.d("INSIDE FOR LOOP", chipGroup.getChildAt(value).toString());
                        Chip chipo =(Chip) chipGroup.getChildAt(value);
                        Log.d("CHIPO VALUE", chipo.toString());
                        Log.d("CHIPO ID", Integer.toString(chipo.getId()));
                        chipo.setClickable(chipo.getId() != chipGroup.getCheckedChipId());
                        chipo.setChecked(chipo.getId() == chipGroup.getCheckedChipId());
                        Log.d("VIEW ID", Integer.toString(view.findViewById(R.id.chip_type_bird).getId()));
                        Log.d("CHIP ID AFTER",  Integer.toString(chip.getId()));
                        Log.d("CHIP GROUP ID AFTER",  Integer.toString(chipGroup.getCheckedChipId()));
//                        if (chip.getId() != chipGroup.getCheckedChipId()){
//                            Log.d("CLICK STATE", "No LONGER CLICKABLE");
//                            chip.setClickable(false);
//
//                        } else{
//                            Log.d("CLICK STATE", "CLICKABLE");
//                            chip.setClickable(true);
//                            //                        chip.setClickable(false);
//                        }
//                        ((Chip) chipGroup.getChildAt(value)).setChecked(true);
                    }
                    Log.d("EXPRESSION EVALUATION", Boolean.toString((chip.getId() == view.findViewById(R.id.chip_type_bird).getId()) && chip.isChecked()));
                    ScrollView scrollBirdView = (ScrollView) view.findViewById(R.id.identifer_bird_scroll_list);
                    ScrollView scrollMammalView = (ScrollView) view.findViewById(R.id.identifer_mammal_scroll_list);
                    ScrollView scrollReptileAmphibianView = (ScrollView) view.findViewById(R.id.identifer_reptile_amphibian_scroll_list);
                    if ((chip.getId() == view.findViewById(R.id.chip_type_bird).getId()) && chip.isChecked()){
                        Log.d("THE TWO CHIPS ARE EQUAL", "YES ");
//                        Bundle bundle = replaceBundleFiltersBySpecies("Bird");
//                        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.bird_choices_list);
//                        linearLayout.setVisibility(View.VISIBLE);
                        scrollBirdView.setVisibility(View.VISIBLE);
                        scrollMammalView.setVisibility(View.GONE);
                        scrollReptileAmphibianView.setVisibility(View.GONE);
//                        bundle.putString("SpeciesType","Bird");
                        replaceBundleFiltersBySpecies("Bird");
                        updateResultList(bundle);
                    }else if ((chip.getId() == view.findViewById(R.id.chip_type_mammal).getId()) && chip.isChecked()){
                        Log.d("THE TWO CHIPS ARE EQUAL", "YES ");
//                        Bundle bundle = replaceBundleFiltersBySpecies("Mammal");
//                        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.bird_choices_list);
//                        linearLayout.setVisibility(View.INVISIBLE);
//                        LinearLayout mammalLinearLayout = (LinearLayout) view.findViewById(R.id.mammal_choices_list);
//                        mammalLinearLayout.setVisibility(View.VISIBLE);
                        scrollBirdView.setVisibility(View.GONE);
                        scrollMammalView.setVisibility(View.VISIBLE);
                        scrollReptileAmphibianView.setVisibility(View.GONE);
//                        bundle.putString("SpeciesType","Mammal");
                        replaceBundleFiltersBySpecies("Mammal");
                        updateResultList(bundle);
                    }
                    else if ((chip.getId() == view.findViewById(R.id.chip_type_reptile).getId()) && chip.isChecked()){
                        Log.d("THE TWO CHIPS ARE EQUAL", "YES ");
//                        Bundle bundle = replaceBundleFiltersBySpecies("Reptile");

//                        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.bird_choices_list);
//                        linearLayout.setVisibility(View.INVISIBLE);
                        scrollBirdView.setVisibility(View.GONE);
                        scrollMammalView.setVisibility(View.GONE);
                        scrollReptileAmphibianView.setVisibility(View.VISIBLE);
//                        bundle.putString("SpeciesType","Reptile");
                        replaceBundleFiltersBySpecies("ReptileAmphibian");
                        updateResultList(bundle);
                    }
                    else if ((chip.getId() == view.findViewById(R.id.chip_type_invertebrate).getId()) && chip.isChecked()){
                        Log.d("THE TWO CHIPS ARE EQUAL", "YES ");
//                        Bundle bundle = replaceBundleFiltersBySpecies("Invertebrate");

//                        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.bird_choices_list);
//                        linearLayout.setVisibility(View.INVISIBLE);

                        scrollBirdView.setVisibility(View.GONE);
                        scrollMammalView.setVisibility(View.GONE);
                        scrollReptileAmphibianView.setVisibility(View.GONE);
//                        bundle.putString("SpeciesType","Invertebrate");
                        replaceBundleFiltersBySpecies("Invertebrate");
                        updateResultList(bundle);
                    }
                }else{
                    Log.d("CHIP VIEW", "THIS DIDN'T WORK");
                }
            }
        });
        reptileAmphibianHeightChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {
                Chip reptileAmphibianHeightChip = ((Chip) chipGroup.findViewById(i));

                if (reptileAmphibianHeightChip != null) {
                    if ((reptileAmphibianHeightChip.getId() == view.findViewById(R.id.reptile_amphibian_choices_height_less_15).getId()) && reptileAmphibianHeightChip.isChecked()){
                        Log.d("HEIGHT", reptileAmphibianHeightChip.getText().toString());
//                        Bundle bundle = getArguments();
                        if (bundle != null) {
                            if(bundle.containsKey("SpeciesType")){
                                if (bundle.getString("SpeciesType").equals("ReptileAmphibian")){
                                    Log.d("WORKING", "YES");
                                    ArrayList<Integer> values = new ArrayList<>();
                                    values.addAll(Arrays.asList(15,0));
                                    bundle.putIntegerArrayList("ReptileAmphibianHeight", values);
                                    updateResultList(bundle);
                                }
                            }
                        }
                    }if ((reptileAmphibianHeightChip.getId() == view.findViewById(R.id.reptile_amphibian_choices_height_between_15_30).getId()) && reptileAmphibianHeightChip.isChecked()){
                        Log.d("HEIGHT", reptileAmphibianHeightChip.getText().toString());
                        if (bundle != null) {
                            if(bundle.containsKey("SpeciesType")){
                                if (bundle.getString("SpeciesType").equals("ReptileAmphibian")){
                                    Log.d("WORKING", "YES");
                                    ArrayList<Integer> values = new ArrayList<>();
                                    values.addAll(Arrays.asList(15,30,0));
                                    bundle.putIntegerArrayList("ReptileAmphibianHeight", values);
                                    updateResultList(bundle);
                                }
                            }
                        }
                    }if ((reptileAmphibianHeightChip.getId() == view.findViewById(R.id.reptile_amphibian_choices_height_more_30).getId()) && reptileAmphibianHeightChip.isChecked()){
                        Log.d("HEIGHT", reptileAmphibianHeightChip.getText().toString());
                        if (bundle != null) {
                            if(bundle.containsKey("SpeciesType")){
                                if (bundle.getString("SpeciesType").equals("ReptileAmphibian")){
                                    Log.d("WORKING", "YES");
                                    ArrayList<Integer> values = new ArrayList<>();
                                    values.addAll(Arrays.asList(30,1));
                                    bundle.putIntegerArrayList("ReptileAmphibianHeight", values);
                                    updateResultList(bundle);
                                }
                            }
                        }
                    }
                } else {
                    if (bundle != null) {
                        bundle.remove("ReptileAmphibianHeight");
                        updateResultList(bundle);
                    }

                }
            }
        });
        Log.d("I AM HERE", "WHY NOT WOKRING");
        Log.d("COUNT COLOUR", Integer.toString(reptileAmphibianSkinColourChipGroup.getChildCount()));
        for (int i = 0; i < reptileAmphibianSkinColourChipGroup.getChildCount(); i++) {
            Log.d("NO IDEA", Integer.toString(i));
            final Chip reptileAmphibianSkinColourChip = (Chip) reptileAmphibianSkinColourChipGroup.getChildAt(i);
            if (reptileAmphibianSkinColourChip != null) {
                Log.d("COMPARISON ONE CHIP", reptileAmphibianSkinColourChip.getText().toString());
                reptileAmphibianSkinColourChip.setOnCheckedChangeListener(new Chip.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                        Log.d("WHAT", "THIS WAS CLIECKED");
                        if((reptileAmphibianSkinColourChip.getId() == view.findViewById(R.id.reptile_amphibian_skin_colour_black).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianSkinColour");
                            updateResultList(bundle);
                        }if((reptileAmphibianSkinColourChip.getId() == view.findViewById(R.id.reptile_amphibian_skin_colour_blue).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianSkinColour");
                            updateResultList(bundle);
                        }if((reptileAmphibianSkinColourChip.getId() == view.findViewById(R.id.reptile_amphibian_skin_colour_brown).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianSkinColour");
                            updateResultList(bundle);
                        }if((reptileAmphibianSkinColourChip.getId() == view.findViewById(R.id.reptile_amphibian_skin_colour_green).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianSkinColour");
                            updateResultList(bundle);
                        }if((reptileAmphibianSkinColourChip.getId() == view.findViewById(R.id.reptile_amphibian_skin_colour_grey).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianSkinColour");
                            updateResultList(bundle);
                        }if((reptileAmphibianSkinColourChip.getId() == view.findViewById(R.id.reptile_amphibian_skin_colour_orange).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianSkinColour");
                            updateResultList(bundle);
                        }if((reptileAmphibianSkinColourChip.getId() == view.findViewById(R.id.reptile_amphibian_skin_colour_red).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianSkinColour");
                            updateResultList(bundle);
                        }if((reptileAmphibianSkinColourChip.getId() == view.findViewById(R.id.reptile_amphibian_skin_colour_white).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianSkinColour");
                            updateResultList(bundle);
                        }if((reptileAmphibianSkinColourChip.getId() == view.findViewById(R.id.reptile_amphibian_skin_colour_yellow).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianSkinColour");
                            updateResultList(bundle);
                        }

                        // Handle the toggle.
                    }
                });
            }
        }
        Log.d("I AM HERE", "WHY NOT WOKRING");
        Log.d("COUNT COLOUR", Integer.toString(reptileAmphibianMarkingsChipGroup.getChildCount()));
        for (int i = 0; i < reptileAmphibianMarkingsChipGroup.getChildCount(); i++) {
            Log.d("NO IDEA", Integer.toString(i));
            final Chip reptileAmphibianMarkingsChip = (Chip) reptileAmphibianMarkingsChipGroup.getChildAt(i);
            if (reptileAmphibianMarkingsChip != null) {
                Log.d("COMPARISON ONE CHIP", reptileAmphibianMarkingsChip.getText().toString());
                reptileAmphibianMarkingsChip.setOnCheckedChangeListener(new Chip.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                        Log.d("WHAT", "THIS WAS CLIECKED");
                        if((reptileAmphibianMarkingsChip.getId() == view.findViewById(R.id.reptile_amphibian_marking_warts).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianMarking");
                            updateResultList(bundle);
                        }if((reptileAmphibianMarkingsChip.getId() == view.findViewById(R.id.reptile_amphibian_marking_spots).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianMarking");
                            updateResultList(bundle);
                        }if((reptileAmphibianMarkingsChip.getId() == view.findViewById(R.id.reptile_amphibian_marking_stripes).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianMarking");
                            updateResultList(bundle);
                        }if((reptileAmphibianMarkingsChip.getId() == view.findViewById(R.id.reptile_amphibian_marking_streaks).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "ReptileAmphibianMarking");
                            updateResultList(bundle);
                        }

                        // Handle the toggle.
                    }
                });
            }
        }
        mammalHeightChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {
                Chip mammalHeightChip = ((Chip) chipGroup.findViewById(i));

                if (mammalHeightChip != null) {
                    if ((mammalHeightChip.getId() == view.findViewById(R.id.mammal_choices_height_less_15).getId()) && mammalHeightChip.isChecked()){
                        Log.d("HEIGHT", mammalHeightChip.getText().toString());
//                        Bundle bundle = getArguments();
                        if (bundle != null) {
                            if(bundle.containsKey("SpeciesType")){
                                if (bundle.getString("SpeciesType").equals("Mammal")){
                                    Log.d("WORKING", "YES");
                                    ArrayList<Integer> values = new ArrayList<>();
                                    values.addAll(Arrays.asList(15,0));
                                    bundle.putIntegerArrayList("MammalHeight", values);
                                    updateResultList(bundle);
                                }
                            }
                        }
                    }if ((mammalHeightChip.getId() == view.findViewById(R.id.mammal_choices_height_between_15_30).getId()) && mammalHeightChip.isChecked()){
                        Log.d("HEIGHT", mammalHeightChip.getText().toString());
                        if (bundle != null) {
                            if(bundle.containsKey("SpeciesType")){
                                if (bundle.getString("SpeciesType").equals("Mammal")){
                                    Log.d("WORKING", "YES");
                                    ArrayList<Integer> values = new ArrayList<>();
                                    values.addAll(Arrays.asList(15,30,0));
                                    bundle.putIntegerArrayList("MammalHeight", values);
                                    updateResultList(bundle);
                                }
                            }
                        }
                    }if ((mammalHeightChip.getId() == view.findViewById(R.id.mammal_choices_height_between_30_45).getId()) && mammalHeightChip.isChecked()){
                        Log.d("HEIGHT", mammalHeightChip.getText().toString());
                        if (bundle != null) {
                            if(bundle.containsKey("SpeciesType")){
                                if (bundle.getString("SpeciesType").equals("Mammal")){
                                    Log.d("WORKING", "YES");
                                    ArrayList<Integer> values = new ArrayList<>();
                                    values.addAll(Arrays.asList(30,45,0));
                                    bundle.putIntegerArrayList("MammalHeight", values);
                                    updateResultList(bundle);
                                }
                            }
                        }
                    }if ((mammalHeightChip.getId() == view.findViewById(R.id.mammal_choices_height_more_45).getId()) && mammalHeightChip.isChecked()){
                        Log.d("HEIGHT", mammalHeightChip.getText().toString());
                        if (bundle != null) {
                            if(bundle.containsKey("SpeciesType")){
                                if (bundle.getString("SpeciesType").equals("Mammal")){
                                    Log.d("WORKING", "YES");
                                    ArrayList<Integer> values = new ArrayList<>();
                                    values.addAll(Arrays.asList(45,1));
                                    bundle.putIntegerArrayList("MammalHeight", values);
                                    updateResultList(bundle);
                                }
                            }
                        }
                    }
                } else {
                    if (bundle != null) {
                        bundle.remove("MammalHeight");
                        updateResultList(bundle);
                    }

                }
            }
        });
        Log.d("I AM HERE", "WHY NOT WOKRING");
        Log.d("COUNT COLOUR", Integer.toString(mammalHeadColourChipGroup.getChildCount()));
        for (int i = 0; i < mammalHeadColourChipGroup.getChildCount(); i++) {
            Log.d("NO IDEA", Integer.toString(i));
            final Chip mammalHeadColourChip = (Chip) mammalHeadColourChipGroup.getChildAt(i);
            if (mammalHeadColourChip != null) {
                Log.d("COMPARISON ONE CHIP", mammalHeadColourChip.getText().toString());
                mammalHeadColourChip.setOnCheckedChangeListener(new Chip.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                        Log.d("WHAT", "THIS WAS CLIECKED");
                        if((mammalHeadColourChip.getId() == view.findViewById(R.id.mammal_head_colour_option_grey).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "MammalHeadColour");
                            updateResultList(bundle);
                        }if((mammalHeadColourChip.getId() == view.findViewById(R.id.mammal_head_colour_option_white).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "MammalHeadColour");
                            updateResultList(bundle);
                        }if((mammalHeadColourChip.getId() == view.findViewById(R.id.mammal_head_colour_option_red).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "MammalHeadColour");
                            updateResultList(bundle);
                        }if((mammalHeadColourChip.getId() == view.findViewById(R.id.mammal_head_colour_option_light_brown).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "MammalHeadColour");
                            updateResultList(bundle);
                        }if((mammalHeadColourChip.getId() == view.findViewById(R.id.mammal_head_colour_option_brown).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "MammalHeadColour");
                            updateResultList(bundle);
                        }if((mammalHeadColourChip.getId() == view.findViewById(R.id.mammal_head_colour_option_black).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "MammalHeadColour");
                            updateResultList(bundle);
                        }

                        // Handle the toggle.
                    }
                });
            }
        }
        Log.d("I AM HERE", "WHY NOT WOKRING");
        Log.d("COUNT COLOUR", Integer.toString(mammalFurColourChipGroup.getChildCount()));
        for (int i = 0; i < mammalFurColourChipGroup.getChildCount(); i++) {
            Log.d("NO IDEA", Integer.toString(i));
            final Chip mammalFurColourChip = (Chip) mammalFurColourChipGroup.getChildAt(i);
            if (mammalFurColourChip != null) {
                Log.d("COMPARISON ONE CHIP", mammalFurColourChip.getText().toString());
                mammalFurColourChip.setOnCheckedChangeListener(new Chip.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                        Log.d("WHAT", "THIS WAS CLIECKED");
                        if((mammalFurColourChip.getId() == view.findViewById(R.id.mammal_fur_colour_option_grey).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "MammalFurColour");
                            updateResultList(bundle);
                        }if((mammalFurColourChip.getId() == view.findViewById(R.id.mammal_fur_colour_option_white).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "MammalFurColour");
                            updateResultList(bundle);
                        }if((mammalFurColourChip.getId() == view.findViewById(R.id.mammal_fur_colour_option_red).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "MammalFurColour");
                            updateResultList(bundle);
                        }if((mammalFurColourChip.getId() == view.findViewById(R.id.mammal_fur_colour_option_light_brown).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "MammalFurColour");
                            updateResultList(bundle);
                        }if((mammalFurColourChip.getId() == view.findViewById(R.id.mammal_fur_colour_option_brown).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "MammalFurColour");
                            updateResultList(bundle);
                        }if((mammalFurColourChip.getId() == view.findViewById(R.id.mammal_fur_colour_option_black).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "MammalFurColour");
                            updateResultList(bundle);
                        }

                        // Handle the toggle.
                    }
                });
            }
        }
        birdHeightChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {
                Chip heightChip = ((Chip) chipGroup.findViewById(i));

                if (heightChip != null) {
                    if ((heightChip.getId() == view.findViewById(R.id.bird_choices_height_less_15).getId()) && heightChip.isChecked()){
                        Log.d("HEIGHT", heightChip.getText().toString());
//                        Bundle bundle = getArguments();
                        if (bundle != null) {
                            if(bundle.containsKey("SpeciesType")){
                                if (bundle.getString("SpeciesType").equals("Bird")){
                                    Log.d("WORKING", "YES");
                                    ArrayList<Integer> values = new ArrayList<>();
                                    values.addAll(Arrays.asList(15,0));
                                    bundle.putIntegerArrayList("BirdHeight", values);
                                    updateResultList(bundle);
                                }
                            }
                        }
                    }if ((heightChip.getId() == view.findViewById(R.id.bird_choices_height_between_15_30).getId()) && heightChip.isChecked()){
                        Log.d("HEIGHT", heightChip.getText().toString());
                        if (bundle != null) {
                            if(bundle.containsKey("SpeciesType")){
                                if (bundle.getString("SpeciesType").equals("Bird")){
                                    Log.d("WORKING", "YES");
                                    ArrayList<Integer> values = new ArrayList<>();
                                    values.addAll(Arrays.asList(15,30,0));
                                    bundle.putIntegerArrayList("BirdHeight", values);
                                    updateResultList(bundle);
                                }
                            }
                        }
                    }if ((heightChip.getId() == view.findViewById(R.id.bird_choices_height_more_30).getId()) && heightChip.isChecked()){
                        Log.d("HEIGHT", heightChip.getText().toString());
                        if (bundle != null) {
                            if(bundle.containsKey("SpeciesType")){
                                if (bundle.getString("SpeciesType").equals("Bird")){
                                    Log.d("WORKING", "YES");
                                    ArrayList<Integer> values = new ArrayList<>();
                                    values.addAll(Arrays.asList(30,1));
                                    bundle.putIntegerArrayList("BirdHeight", values);
                                    updateResultList(bundle);
                                }
                            }
                        }
                    }
                } else {
                    if (bundle != null) {
                        bundle.remove("BirdHeight");
                        updateResultList(bundle);
                    }

                }
            }
        });

//        birdHeadColourChips.clearCheck();
        Log.d("I AM HERE", "WHY NOT WOKRING");
        Log.d("COUNT COLOUR", Integer.toString(birdHeadColourChips.getChildCount()));
        for (int i = 0; i < birdHeadColourChips.getChildCount(); i++) {
            Log.d("NO IDEA", Integer.toString(i));
            final Chip birdHeadColourChip = (Chip) birdHeadColourChips.getChildAt(i);
            if (birdHeadColourChip != null) {
                Log.d("COMPARISON ONE CHIP", birdHeadColourChip.getText().toString());
                birdHeadColourChip.setOnCheckedChangeListener(new Chip.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                        Log.d("WHAT", "THIS WAS CLIECKED");
                        if((birdHeadColourChip.getId() == view.findViewById(R.id.bird_head_colour_blue).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdHeadColour");
                            updateResultList(bundle);
                        }if((birdHeadColourChip.getId() == view.findViewById(R.id.bird_head_colour_white).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdHeadColour");
                            updateResultList(bundle);
                        }if((birdHeadColourChip.getId() == view.findViewById(R.id.bird_head_colour_yellow).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdHeadColour");
                            updateResultList(bundle);
                        }if((birdHeadColourChip.getId() == view.findViewById(R.id.bird_head_colour_black).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdHeadColour");
                            updateResultList(bundle);
                        }if((birdHeadColourChip.getId() == view.findViewById(R.id.bird_head_colour_brown).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdHeadColour");
                            updateResultList(bundle);
                        }if((birdHeadColourChip.getId() == view.findViewById(R.id.bird_head_colour_green).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdHeadColour");
                            updateResultList(bundle);
                        }if((birdHeadColourChip.getId() == view.findViewById(R.id.bird_head_colour_orange).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked,"BirdHeadColour");
                            updateResultList(bundle);
                        }if((birdHeadColourChip.getId() == view.findViewById(R.id.bird_head_colour_grey).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked,"BirdHeadColour");
                            updateResultList(bundle);
                        }if((birdHeadColourChip.getId() == view.findViewById(R.id.bird_head_colour_red).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked,"BirdHeadColour");
                            updateResultList(bundle);
                        }

                        // Handle the toggle.
                    }
                });
            }
        }


        Log.d("I AM HERE", "WHY NOT WOKRING");
        Log.d("COUNT COLOUR", Integer.toString(birdBellyColourChips.getChildCount()));
        for (int i = 0; i < birdBellyColourChips.getChildCount(); i++) {
            Log.d("NO IDEA", Integer.toString(i));
            final Chip birdBellyColourChip = (Chip) birdBellyColourChips.getChildAt(i);
            if (birdBellyColourChip != null) {
                Log.d("COMPARISON ONE CHIP", birdBellyColourChip.getText().toString());
                birdBellyColourChip.setOnCheckedChangeListener(new Chip.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                        Log.d("WHAT", "THIS WAS CLIECKED");
                        if((birdBellyColourChip.getId() == view.findViewById(R.id.bird_belly_colour_blue).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdBellyColour");
                            updateResultList(bundle);
                        }if((birdBellyColourChip.getId() == view.findViewById(R.id.bird_belly_colour_white).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdBellyColour");
                            updateResultList(bundle);
                        }if((birdBellyColourChip.getId() == view.findViewById(R.id.bird_belly_colour_yellow).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdBellyColour");
                            updateResultList(bundle);
                        }if((birdBellyColourChip.getId() == view.findViewById(R.id.bird_belly_colour_black).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdBellyColour");
                            updateResultList(bundle);
                        }if((birdBellyColourChip.getId() == view.findViewById(R.id.bird_belly_colour_brown).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdBellyColour");
                            updateResultList(bundle);
                        }if((birdBellyColourChip.getId() == view.findViewById(R.id.bird_belly_colour_green).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdBellyColour");
                            updateResultList(bundle);
                        }if((birdBellyColourChip.getId() == view.findViewById(R.id.bird_belly_colour_orange).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked,"BirdBellyColour");
                            updateResultList(bundle);
                        }if((birdBellyColourChip.getId() == view.findViewById(R.id.bird_belly_colour_grey).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked,"BirdBellyColour");
                            updateResultList(bundle);
                        }if((birdBellyColourChip.getId() == view.findViewById(R.id.bird_belly_colour_red).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked,"BirdBellyColour");
                            updateResultList(bundle);
                        }

                        // Handle the toggle.
                    }
                });
            }
        }

//        birdFeatherColourChips.clearCheck();
        Log.d("I AM HERE", "WHY NOT WOKRING");
        Log.d("COUNT COLOUR", Integer.toString(birdFeatherColourChips.getChildCount()));
        for (int i = 0; i < birdFeatherColourChips.getChildCount(); i++) {
            Log.d("NO IDEA", Integer.toString(i));
            final Chip birdFeatherColourChip = (Chip) birdFeatherColourChips.getChildAt(i);
            if (birdFeatherColourChip != null) {
                Log.d("COMPARISON ONE CHIP", birdFeatherColourChip.getText().toString());
                birdFeatherColourChip.setOnCheckedChangeListener(new Chip.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                        Log.d("WHAT", "THIS WAS CLIECKED");
                        if((birdFeatherColourChip.getId() == view.findViewById(R.id.bird_feather_colour_blue).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdWingColour");
                            updateResultList(bundle);
                        }if((birdFeatherColourChip.getId() == view.findViewById(R.id.bird_feather_colour_white).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdWingColour");
                            updateResultList(bundle);
                        }if((birdFeatherColourChip.getId() == view.findViewById(R.id.bird_feather_colour_yellow).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdWingColour");
                            updateResultList(bundle);
                        }if((birdFeatherColourChip.getId() == view.findViewById(R.id.bird_feather_colour_black).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdWingColour");
                            updateResultList(bundle);
                        }if((birdFeatherColourChip.getId() == view.findViewById(R.id.bird_feather_colour_brown).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdWingColour");
                            updateResultList(bundle);
                        }if((birdFeatherColourChip.getId() == view.findViewById(R.id.bird_feather_colour_green).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked, "BirdWingColour");
                            updateResultList(bundle);
                        }if((birdFeatherColourChip.getId() == view.findViewById(R.id.bird_feather_colour_orange).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked,"BirdWingColour");
                            updateResultList(bundle);
                        }if((birdFeatherColourChip.getId() == view.findViewById(R.id.bird_feather_colour_grey).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked,"BirdWingColour");
                            updateResultList(bundle);
                        }if((birdFeatherColourChip.getId() == view.findViewById(R.id.bird_feather_colour_red).getId())){
                            addOrRemoveFromBundleOnClick(v, isChecked,"BirdWingColour");
                            updateResultList(bundle);
                        }

                        // Handle the toggle.
                    }
                });
            }
        }
        return view;
    }
    public void addOrRemoveFromBundleOnClick(CompoundButton v, boolean isChecked, String filterType){
        ArrayList<String> birdHeights = new ArrayList<>();
        if(bundle.containsKey(filterType)){
            birdHeights = bundle.getStringArrayList(filterType);
        }
        if (isChecked){
            Log.d("WHAT", "THIS WAS TICKED");
            birdHeights.add(v.getText().toString());
            bundle.putStringArrayList(filterType, birdHeights);

        }else{
            Log.d("WHAT", "THIS WAS UNTICKED");
            birdHeights.remove(v.getText().toString());
            bundle.putStringArrayList(filterType, birdHeights);
        }
    }
    public Bundle replaceBundleFiltersBySpecies(String speciesType){
//        Bundle bundle = getArguments();
        if (bundle != null) {
            if(speciesType.equals("Bird")){
                bundle.putString("SpeciesType","Bird");
                removeFilterFromBundle( mammalFilters);
                removeFilterFromBundle( reptileAmphibianFilters);
//                if (bundle.containsKey("BirdHeadColour")) {
//                    bundle.remove("BirdHeadColour");
//                }if (bundle.containsKey("BirdHeight")) {
//                    bundle.remove("BirdHeight");
//                }if (bundle.containsKey("BirdBellyColour")) {
//                    bundle.remove("BirdBellyColour");
//                }if (bundle.containsKey("BirdFeatherColour")) {
//                    bundle.remove("BirdFeatherColour");
//                }
            }
            if(speciesType.equals("Mammal")){
                bundle.putString("SpeciesType","Mammal");
                removeFilterFromBundle( birdFilters);
                removeFilterFromBundle( reptileAmphibianFilters);}
            if(speciesType.equals("ReptileAmphibian")){
                bundle.putString("SpeciesType","ReptileAmphibian");
                removeFilterFromBundle(birdFilters);
                removeFilterFromBundle( mammalFilters);}
            if(speciesType.equals("Invertebrate")){
                bundle.putString("SpeciesType","Invertebrate");
                removeFilterFromBundle( birdFilters);
                removeFilterFromBundle( mammalFilters);
                removeFilterFromBundle( reptileAmphibianFilters);}
        }
        return bundle;
    }
    public void removeFilterFromBundle( List<String> filterList){
        for (String filter :
                filterList) {
            if (bundle.containsKey(filter)){
                bundle.remove(filter);
            }
        }
        for (ChipGroup chips :
                birdFilterChipGroups) {
            uncheckAllChips(chips);
        }
        for (ChipGroup chips :
                mammalFilterChipGroups) {
            uncheckAllChips(chips);
        }
        for (ChipGroup chips :
                reptileAmphibianFilterChipGroups) {
            uncheckAllChips(chips);
        }


    }
    public void uncheckAllChips (ChipGroup chips){
        for (int i = 0; i < chips.getChildCount(); i++) {
            Chip chip = (Chip) chips.getChildAt(i);
            chip.setChecked(false);

        }
    }
    public void updateResultList(Bundle bundle){
        filters.clear();
        if (bundle != null) {
            if (bundle.containsKey("SpeciesType")) {
                filters.add("SpeciesType");
                Log.d("FILTER VALUE", bundle.getString("SpeciesType"));
                if (bundle.getString("SpeciesType").equals("Bird")) {
                    if (bundle.containsKey("BirdHeight")) {
                        filters.add("BirdHeight");

                    }
                    if (bundle.containsKey("BirdHeadColour")) {
                        filters.add("BirdHeadColour");

                    }
                    if (bundle.containsKey("BirdWingColour")) {
                        filters.add("BirdWingColour");

                    }
                    if (bundle.containsKey("BirdBellyColour")) {
                        filters.add("BirdBellyColour");

                    }
                }
                if (bundle.getString("SpeciesType").equals("Mammal")) {
                    if (bundle.containsKey("MammalHeight")) {
                        filters.add("MammalHeight");
                    }
                    if (bundle.containsKey("MammalHeadColour")) {
                        filters.add("MammalHeadColour");

                    }if (bundle.containsKey("MammalFurColour")) {
                        filters.add("MammalFurColour");

                    }
                }
                if (bundle.getString("SpeciesType").equals("ReptileAmphibian")) {
                    if (bundle.containsKey("ReptileAmphibianHeight")) {
                        filters.add("ReptileAmphibianHeight");
                    }if (bundle.containsKey("ReptileAmphibianSkinColour")) {
                        filters.add("ReptileAmphibianSkinColour");
                    }if (bundle.containsKey("ReptileAmphibianMarking")) {
                        filters.add("ReptileAmphibianMarking");
                    }
                }
            }
        }
        Log.d("STATE OF FILTERS", filters.toString());
        ArrayList<Animal> resultAnimals = new ArrayList<>(searchUsingFilters(bundle));
        if (!resultAnimals.isEmpty()) {
            TextView error_result = (TextView) view.findViewById(R.id.result_message);
            error_result.setText("Possible Animals Found From Filter(s) "+ Integer.toString(resultAnimals.size()));
            List<String> resultAnimalNames = new ArrayList<>();
            List<String> resultAnimalTypes = new ArrayList<>();
            List<String> resultAnimalScientificNouns = new ArrayList<>();
            List<String> resultAnimalImages = new ArrayList<>();
            for (Animal animal :
                    resultAnimals) {
                resultAnimalNames.add(animal.getName());
                resultAnimalTypes.add(animal.getType());
                resultAnimalScientificNouns.add(animal.getScientificName());
                resultAnimalImages.add(animal.getAnimalImage());
            }
            CustomAdapter adapter = new CustomAdapter(getContext(), resultAnimalNames, resultAnimalTypes, resultAnimalScientificNouns, resultAnimalImages);
            list.setAdapter(adapter);
            list.setEnabled(true);
        }else {
            list.setAdapter(null);
            TextView error_result = (TextView) view.findViewById(R.id.result_message);

            error_result.setText("No Animals Of the Specified Filter(s) Could be Found" );
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    public ArrayList<Animal> searchByType(ArrayList<Animal> animalList, String type){
        Log.d("STARTING", "THIS PROCESS SEARCH BY TYPE HAS STARTED "+ type);
        ArrayList<Animal> resultList = new ArrayList<>();
        for (Animal animal :
                animalList) {
            if (type.equals("ReptileAmphibian")){
                if (animal.getType().equalsIgnoreCase("Reptile") || animal.getType().equalsIgnoreCase("Amphibian")){
                    resultList.add(animal);
                    Log.d("MID", "THIS PROCESS HAS WORKED");
                }
            }
            else if (animal.getType().equalsIgnoreCase(type)){
                resultList.add(animal);
                Log.d("MID", "THIS PROCESS HAS WORKED");
            }
        }
//        for (Animal animal: resultList){
//            Log.d("RSEULT ANIMAL", animal.toString());
//        }
        return resultList;
    }

    public ArrayList<Animal> searchUsingFilters(Bundle bundle){
//        NavHostFragment navHostFragment = (NavHostFragment) getParentFragment();
//        Fragment parent = (Fragment) navHostFragment.getParentFragment();
//        parent.getView().findViewById(R.id.species_identifier_nav_graph);


//        Log.d("THINFY", parent.toString());
//        Log.d("WHO", navHostFragment.toString());

        AnimalDatabase db= ((MainActivity)getActivity()).getDb();

        ArrayList<Animal> allAnimals = new ArrayList<>(db.animalDAO().getAllAnimals());
        ArrayList<Animal> resultList = allAnimals;

        for (String filter: filters) {
            if (filter.equalsIgnoreCase("SpeciesType")){
                Log.d("FILTER VALUE", bundle.getString("SpeciesType"));
                resultList = searchByType(resultList, bundle.getString("SpeciesType"));
                Log.d("AFTER BY TYPE", resultList.size() + "");

            }
            if (filter.equalsIgnoreCase("BirdHeight")){
                resultList = BirdSearchTools.searchBySize(resultList, bundle.getIntegerArrayList("BirdHeight"));
                Log.d("AFTER BY SIZE", resultList.size() + "");
            }
            if (filter.equalsIgnoreCase("BirdBellyColour")){
                resultList = BirdSearchTools.searchByBellyColour(resultList,bundle.getStringArrayList("BirdBellyColour"));
                Log.d("AFTER BY BELLY COLOUR", resultList.size() + "");
            }
            if (filter.equalsIgnoreCase("BirdHeadColour")){
                resultList = BirdSearchTools.searchByHeadColour(resultList, bundle.getStringArrayList("BirdHeadColour"));
                Log.d("AFTER BY HEAD COLOUR", resultList.size() + "");
            }
            if (filter.equalsIgnoreCase("BirdWingColour")){
                resultList = BirdSearchTools.searchByWingColour(resultList, getArguments().getStringArrayList("BirdWingColour"));
                Log.d("AFTER BY WING COLOUR", resultList.size() + "");
            }
            if (filter.equalsIgnoreCase("MammalHeight")){
                resultList = MammalSearchTools.searchBySize(resultList, bundle.getIntegerArrayList("MammalHeight"));
                Log.d("AFTER BY SIZE", resultList.size() + "");
            }if (filter.equalsIgnoreCase("MammalHeadColour")){
                resultList = MammalSearchTools.searchByHeadColour(resultList, bundle.getStringArrayList("MammalHeadColour"));
                Log.d("AFTER BY HEAD COLOUR", resultList.size() + "");
            }if (filter.equalsIgnoreCase("MammalFurColour")){
                resultList = MammalSearchTools.searchByFurColour(resultList, bundle.getStringArrayList("MammalFurColour"));
                Log.d("AFTER BY FUR COLOUR", resultList.size() + "");
            }
            if (filter.equalsIgnoreCase("ReptileAmphibianHeight")){
                resultList = ReptileAmphibianSearchTools.searchBySize(resultList, bundle.getIntegerArrayList("ReptileAmphibianHeight"));
                Log.d("AFTER BY REPTILE SIZE", resultList.size() + "");
            }
            if (filter.equalsIgnoreCase("ReptileAmphibianSkinColour")){
                resultList = ReptileAmphibianSearchTools.searchBySkinColour(resultList, bundle.getStringArrayList("ReptileAmphibianSkinColour"));
                Log.d("AFTER BY REPTILE SIZE", resultList.size() + "");
            }if (filter.equalsIgnoreCase("ReptileAmphibianMarking")){
                resultList = ReptileAmphibianSearchTools.searchByMarking(resultList, bundle.getStringArrayList("ReptileAmphibianMarking"));
                Log.d("AFTER BY REPTILE MARKING", resultList.size() + "");
            }

        }
        for (Animal animal: resultList){
            Log.d("FINAL ANIMAL RESULT", animal.toString());
        }
        return resultList;

    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();
//        Fragment parentFragment = getParentFragment();
//        Log.d("THINGS", parentFragment.toString());
//        Log.d("CHECKING", Boolean.toString(parentFragment instanceof ChooseSpecies));
//        Log.d("WHAT", getActivity().toString());
    }

    class CustomAdapter extends ArrayAdapter<String> {
        Context context;
        List<String> names = new ArrayList<String>();
        List<String> types = new ArrayList<String>();
        List<String> scientificNouns = new ArrayList<String>();
        List<String> images = new ArrayList<String>();
//        int[] imgs;


        public CustomAdapter(Context context, List<String> names, List<String> types, List<String> scientificNouns, List<String> animalImages) {
            super(context, R.layout.animal_lists_item, R.id.animal_name, names);

            this.context = context;
            this.images = animalImages;
            this.names = names;
            this.types = types;
            this.scientificNouns = scientificNouns;
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View animal_item = inflater.inflate(R.layout.animal_lists_item, parent, false);
//            ImageView images = reserve_item.findViewById(R.id.reserve_logo);
            ImageView image = animal_item.findViewById(R.id.animal_image);
            TextView name = animal_item.findViewById(R.id.animal_name);
            TextView type = animal_item.findViewById(R.id.animal_type);
            TextView scientificNoun = animal_item.findViewById(R.id.animal_scientific_name);

            Context context = image.getContext();
            Integer id = context.getResources().getIdentifier(images.get(position), "drawable", context.getPackageName());
            if (!id.toString().equals("null") ){
                image.setImageResource(id);
            }
//

//            images.setImageResource(imgs[position]);
            ;
            name.setText(names.get(position));
            type.setText(types.get(position));
            scientificNoun.setText(scientificNouns.get(position));

            return animal_item;
        }
    }
}