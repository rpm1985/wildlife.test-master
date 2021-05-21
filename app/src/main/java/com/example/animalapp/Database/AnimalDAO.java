package com.example.animalapp.Database;



import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AnimalDAO {

    @Query("SELECT * FROM Animal WHERE id = :animalID")
    List<Animal> getAnimalByID(int animalID);

    @Query("SELECT * FROM Animal")
    List<Animal> getAllAnimals();
    
    @Insert
    void insertAll(List<Animal> animal);

    @Query("DELETE FROM Animal")
    void clearAnimal();


}
