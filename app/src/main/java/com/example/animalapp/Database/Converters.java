package com.example.animalapp.Database;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.room.TypeConverter;

class Converters {
    @TypeConverter
    public StringArrayList storedStringToList(String value) {
        ArrayList<String> langs = new ArrayList<String>(Arrays.asList(value.split(";")));
        return new StringArrayList(langs);
    }

    @TypeConverter
    public String ListToStoredString(StringArrayList cl) {
        String value = "";

        for (String lang :cl.getStringArrayList())
            value += lang + ";";

        return value;
    }
}
