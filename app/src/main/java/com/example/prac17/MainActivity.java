package com.example.prac17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    RecyclerView listManga;
    Button goAddManga;
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listManga = findViewById(R.id.recycler_view);
        goAddManga = findViewById(R.id.go_to_add);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            dataBaseHelper.addManga(new Group(0, "0", "0"));
            prefs.edit().putBoolean("firstrun", false).commit();
        }
        listManga.setLayoutManager(new LinearLayoutManager(this));
        listManga.setHasFixedSize(true);
        GroupsAdapter adapter = new GroupsAdapter(this, dataBaseHelper.getMangaList());
        listManga.setAdapter(adapter);
        Log.d("manga", dataBaseHelper.getMangaList().toString());
        goAddManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}