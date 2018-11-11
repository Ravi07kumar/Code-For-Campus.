package com.example.jfis.savethetime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class UserActivity extends AppCompatActivity {

    private ImageButton scheduleClasses , freeRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        scheduleClasses=findViewById(R.id.viewClassesUserButton);
        freeRooms=findViewById(R.id.viewAvailableRoomUserButton);

        scheduleClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,AvailableRoomsActivity.class);
                startActivity(intent);

            }
        });

        freeRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,ViewScheduleActivity.class);
                startActivity(intent);

            }
        });

    }
}
