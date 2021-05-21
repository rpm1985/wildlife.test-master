package com.example.animalapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinksFragment extends Fragment {


    public LinksFragment() {
        /* Required empty public constructor */
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.title_external_links);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_links, container, false);



        ImageView imageView1 = view.findViewById(R.id.Image1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cardiffconservation.org.uk/"));
                startActivity(browserIntent);
            }
        });

        ImageView imageView2 = view.findViewById(R.id.Image2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ww2.rspb.org.uk/groups/cardiff/"));
                startActivity(browserIntent);
            }
        });

        ImageView imageView3 = view.findViewById(R.id.Image3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.rspb.org.uk/"));
                startActivity(browserIntent);
            }
        });

        ImageView imageView4 = view.findViewById(R.id.Image4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.welshwildlife.org/my-wild-cardiff/"));
                startActivity(browserIntent);
            }
        });

        ImageView imageView5 = view.findViewById(R.id.Image5);
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cardiff.ac.uk/software-academy/"));
                startActivity(browserIntent);
            }
        });

        return view;

    }

//    private void updateView(String lang) {
//        Context context = LocalHelper.setLocale(this, lang);
//        Resources resources = context.getResources();
//    }
}
