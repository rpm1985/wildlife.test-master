package com.example.animalapp;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.animalapp.Database.Animal;
import com.example.animalapp.Database.AnimalDatabase;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.opencsv.CSVReader;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class MainActivity extends AppCompatActivity {

    //TextView item;
    TextView textView;
    Button button;
    AnimalDatabase db;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalHelper.onAttach(newBase, "en"));
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
        db = AnimalDatabase.getDatabase(this);

        addData();
        //MAIN BOTTOM NAV
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //LOGIN FRAGMENT
        fragmentTransaction.add(R.id.fragment_container, new LoginFragment<Fragment>(LoginFragment.class));
        fragmentTransaction.commit();

        final HomeFragment homeFragment = new HomeFragment();
        final AnimalFragment animalFragment = new AnimalFragment();
        final MapFragment mapFragment = new MapFragment();
        final NatureReserveFragment natureReserveFragment = new NatureReserveFragment();
        final LinksFragment linksFragment = new LinksFragment();


        //MAIN BOTTOM NAV IF STATEMENT FOR LOADING FRAGMENTS VIA BUTTONS PRESSED
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (id == R.id.navHome) {
                    fragmentTransaction.replace(R.id.fragment_container, homeFragment);
                    fragmentTransaction.commit();
                    return true;
                } else if (id == R.id.navList) {
                    fragmentTransaction.replace(R.id.fragment_container, animalFragment);
                    fragmentTransaction.commit();
                    return true;
                } else if (id == R.id.navMap) {
                    fragmentTransaction.replace(R.id.fragment_container, mapFragment);
                    fragmentTransaction.commit();
                    return true;
                } else if (id == R.id.navWalk) {
                    fragmentTransaction.replace(R.id.fragment_container, natureReserveFragment);
                    fragmentTransaction.commit();
                    return true;
                } else if (id == R.id.navLinks) {
                    fragmentTransaction.replace(R.id.fragment_container, linksFragment);
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
        });

        navigationView.setSelectedItemId(R.id.navHome);

// else if (item.getItemId() == R.id.btn_notification) { //NOTIFICATION
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.DATE, 5);
//                calendar.set(Calendar.MONTH, 4);
//                calendar.set(Calendar.HOUR_OF_DAY, 11);
//                calendar.set(Calendar.MINUTE, 07);
//                calendar.set(Calendar.SECOND, 30);
//
//                //NOTIFICATION INTENT
//                Intent intent = new Intent(getApplicationContext(), NotificationReciever.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//            }
        //return true;

    }

    //Top menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    //LANGUAGE DEFAULT ENGLISH AND SECONDARY LANGUAGE WELSH
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Configuration configuration;
        configuration = new Configuration(getResources().getConfiguration());
        if (item.getItemId() == R.id.language_en) {
            configuration.locale = Locale.ENGLISH;
        } else if (item.getItemId() == R.id.language_cy) {
            configuration.locale = new Locale("cy");
        }

        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        recreate();
        return true;
    }

    private void updateView(String lang) {
        Context context = LocalHelper.setLocale(this, lang);
        Resources resources = context.getResources();


    }

    public void browser1(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cardiffconservation.org.uk/"));
        startActivity(browserIntent);
    }

    public void browser2(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://ww2.rspb.org.uk/groups/cardiff/"));
        startActivity(browserIntent);
    }

    public void browser3(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.rspb.org.uk/"));
        startActivity(browserIntent);
    }

    public void browser4(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.welshwildlife.org/my-wild-cardiff//"));
        startActivity(browserIntent);
    }

    public void browser5(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cardiff.ac.uk/software-academy/"));
        startActivity(browserIntent);
    }


    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
    public AnimalDatabase getDb() {
        return db;
    }
    private void addData() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.animalDAO().clearAnimal();

                List<Animal> list = null;
                try {
                    list = readAnimalListFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                db.animalDAO().insertAll(list);

//                final List<Animal> allAnimals = db.animalDAO().getAllAnimals();

//                Log.d("List All Animals: ", "AllAnimals: " + allAnimals);
            }
        });
    }
    private List<Animal> readAnimalListFile() throws IOException {

        List<Animal> animalList = new ArrayList<>();

        InputStream is = getResources().openRawResource(R.raw.animal_list);
        InputStreamReader csvStreamReader = new InputStreamReader(is);

        CSVReader reader = new CSVReader(csvStreamReader);
        reader.skip(1);

        String[] record = null;
        int num = 0;


        while ((record = reader.readNext()) != null) {
//            Animal animal = new Animal(record[0], record[1], record[2],
//                    record[3], record[4], record[5], record[6],
//                    record[7], record[8], record[9], record[10], record[11]);
            Animal animal = new Animal();
            animal.setType(record[0]);
            animal.setName(record[1]);
            animal.setScientificName(record[2]);
//            animal.setMinBodyLengthCm(record[3]);
//            animal.setMaxBodyLengthCm(record[4]);
//            animal.setMinWingspanCm(record[5]);
//            animal.setMaxWingspanCm(record[6]);
            animal.setMinBodyLengthCm(Integer.parseInt(record[3]));
            animal.setMaxBodyLengthCm(Integer.parseInt(record[4]));

            try{
                if (record[5] != null && record[6] != null) {
                    animal.setMinWingspanCm(Integer.parseInt(record[5]));
                    animal.setMaxWingspanCm(Integer.parseInt(record[6]));
                } else {
                    animal.setMinWingspanCm(num);
                    animal.setMaxWingspanCm(num);
                }
            }catch (NumberFormatException e) {
                Log.getStackTraceString(e);
            }

//                animal.setMinWingspanCm(Integer.parseInt(record[5]));
//                animal.setMaxWingspanCm(Integer.parseInt(record[6]));
            animal.setDescription(record[7]);
            animal.setHabitat(record[8]);
            animal.setBestTime(record[9]);
            animal.setBestWalk(record[10]);
            animal.setFoodSource(record[11]);
            animal.setAnimalImage(record[12]);
            animal.setHeadColour(record[14]);
            animal.setWingColour(record[15]);
            animal.setBellyColour(record[16]);
            animal.setFurColour(record[17]);
            animal.setSkinColour(record[18]);
            animal.setMarkings(record[19]);

            animalList.add(animal);
//                Log.d("Read file: ", "Animal List: " + animal);

        }
        return animalList;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
