package com.example.animalapp;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import androidx.room.Room;
import androidx.test.runner.AndroidJUnit4;

import static androidx.room.Room.inMemoryDatabaseBuilder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DatabaseHelperTest {

    @RunWith(AndroidJUnit4.class)
    public class SimpleEntityReadWriteTest {
        private DatabaseHelper databaseHelper;
        private DatabaseHelperTest db;

        @Before
        public void createDb() {
            Context context = ApplicationProvider.getApplicationContext();
            db = inMemoryDatabaseBuilder(context, DatabaseHelper.class).build();
            DatabaseHelper = ((DatabaseHelper) db).getDatabaseName();
        }

        @After
        public void closeDb() throws IOException {
            databaseHelper.close();
        }

        @Test
        public void writeUserAndReadInList() throws Exception {
            User user = TestUtil.createUser(3);
            user.setName("george");
            DatabaseHelperTest.insert(user);
            List<User> byName = DatabaseHelperTest.findUsersByName("george");
            assertThat(byName.get(0), equalTo(user));
        }
    }
}