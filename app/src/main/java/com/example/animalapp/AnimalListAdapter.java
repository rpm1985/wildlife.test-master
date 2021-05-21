package com.example.animalapp;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AnimalListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Animal> animalsList;

    public AnimalListAdapter(Context context, int layout, ArrayList<Animal> foodsList) {
        this.context = context;
        this.layout = layout;
        this.animalsList = animalsList;
    }

    @Override
    public int getCount() {
        return animalsList.size();
    }

    @Override
    public Object getItem(int position) {
        return animalsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtDescription;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtDescription = (TextView) row.findViewById(R.id.txtDescription);
            holder.imageView = (ImageView) row.findViewById(R.id.imgAnimal);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Animal food = animalsList.get(position);

        holder.txtName.setText(food.getName());
        holder.txtDescription.setText(food.getDescription());

        byte[] foodImage = food.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
