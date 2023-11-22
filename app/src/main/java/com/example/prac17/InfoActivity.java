package com.example.prac17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    DataBaseHelper bd;
    TextView name;
    TextView author;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        bd = new DataBaseHelper(this);
        name = findViewById(R.id.i_manga_name);
        author = findViewById(R.id.i_manga_author);
        delete = findViewById(R.id.delete_manga);

        Bundle extras = getIntent().getExtras();
        int currentId = extras.getInt("id");

        Group group = bd.getManga(currentId);
        name.setText(group.getManga_Name());
        author.setText(group.getManga_Author());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd.deleteManga(currentId);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}