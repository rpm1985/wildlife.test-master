package com.example.animalapp;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 */


public class NatureReserveFragment extends Fragment {
    List<DataItem> listData;


    ListView list;
    String titles[] = {"Cathays Cemetery", "Roath Park", "Bute Park", "Forest Farm Country Park", "Lisvane and Llanishen reservoirs", "Cardiff Bay Wetlands and Hamadryad Park"};
    String informations[] = {"Fairoak Rd, Cardiff CF24 4PY", "Lake Rd W, Cardiff CF23 5PA", "North Rd, Cardiff CF10 3ER", "Forest Farm Rd, Cardiff CF14 7JH", "Cardiff CF14 0RH", "Cardiff CF10 5UY"};
    int images[] = {R.drawable.cemetery, R.drawable.park, R.drawable.park, R.drawable.park, R.drawable.resevoir, R.drawable.reserve};


    public NatureReserveFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_nature_reserves, container,false);
        getActivity().setTitle(R.string.title_walks);
        list = view.findViewById(R.id.reserves_list);
//        ListView lvData = (ListView) view.findViewById(R.id.list);
//
//        DataItem reserve1 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve2 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve3 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve4 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve5 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve6 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve7 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve8 = new DataItem("1","reserve 1","Park","Enter description here");
//
//        ArrayList<DataItem> data = new ArrayList<>();
//        data.add(reserve1);
//        data.add(reserve2);
//        data.add(reserve3);
//        data.add(reserve4);
//        data.add(reserve5);
//        data.add(reserve6);
//        data.add(reserve7);
//        data.add(reserve8);
//
//        CustomAdapter adapter = new CustomAdapter(getContext(),R.layout.fragment_nature_reserves,data);
//        lvData.setAdapter(adapter);
//        super.getView(position, convertView, parent)
        CustomAdapter adapter = new CustomAdapter(getContext(), titles, images, informations);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//          on click for four items of nature reserves
                Bundle bundle = new Bundle();
                if (position == 0) {
                    Log.i("WALKS CLICK", "First one");
                    bundle.putDouble("Latitude", 51.50072866);
                    bundle.putDouble("Longitude", -3.18105698);
                    replaceFragment(setBundleToMap(bundle));
                }else
                if (position == 1) {
                    Log.i("WALKS CLICK", "Second one");
                    bundle.putDouble("Latitude", 51.50492273);
                    bundle.putDouble("Longitude", -3.17530632);
                    replaceFragment(setBundleToMap(bundle));
                }else
                if (position == 2) {
                    Log.i("WALKS CLICK", "Second one");
                    bundle.putDouble("Latitude", 51.48849155);
                    bundle.putDouble("Longitude", -3.18938255);
                    replaceFragment(setBundleToMap(bundle));
                }else if (position == 3) {
                    Log.i("WALKS CLICK", "Second one");
                    bundle.putDouble("Latitude", 51.516780);
                    bundle.putDouble("Longitude",-3.243370);
                    replaceFragment(setBundleToMap(bundle));
                }else if (position == 4) {
                    Log.i("WALKS CLICK", "Second one");
                    bundle.putDouble("Latitude", 51.52559354);
                    bundle.putDouble("Longitude", -3.17539215);
                    replaceFragment(setBundleToMap(bundle));
                }else if (position == 5) {
                    Log.i("WALKS CLICK", "Second one");
                    bundle.putDouble("Latitude", 51.46067865);
                    bundle.putDouble("Longitude", -3.1755209);
                    replaceFragment(setBundleToMap(bundle));
                }
            }
        });

        return view;



    }
    public MapFragment setBundleToMap(Bundle bundle){
        MapFragment map = new MapFragment();
        map.setArguments(bundle);
        return map;
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    class CustomAdapter extends ArrayAdapter<String> {
        Context context;
        String myTitles[];
        String myDescriptions[];
        int[] imgs;


        public CustomAdapter(Context context, String[] titles, int[] imgs, String[] descriptions) {
            super(context, R.layout.nature_reserve_item, R.id.reserve_title, titles);

            this.context = context;
            this.imgs = imgs;
            this.myTitles = titles;
            this.myDescriptions = descriptions;
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View reserve_item = inflater.inflate(R.layout.nature_reserve_item, parent, false);
            ImageView images = reserve_item.findViewById(R.id.reserve_logo);
            TextView title = reserve_item.findViewById(R.id.reserve_title);
            TextView description = reserve_item.findViewById(R.id.reserve_information);

            images.setImageResource(imgs[position]);
            title.setText(myTitles[position]);
            description.setText(myDescriptions[position]);

            return reserve_item;
        }
    }








}
