package com.example.jfis.savethetime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminActivity extends AppCompatActivity {
    private ImageButton addSchedules , viewSchedules , availableRooms ,addRoom,viewRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
            addRoom=findViewById(R.id.addRoomAdminButton);
        addSchedules=findViewById(R.id.addScheduleAdminButton);
        viewSchedules=findViewById(R.id.viewScheduleAdminButton);
        availableRooms=findViewById(R.id.availableRoomsAdminButton);
viewRoom=findViewById(R.id.viewRoomAdminButton);


viewRoom.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(AdminActivity.this,ViewRoomActicity.class);
        startActivity(intent);
    }
});
        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this,AddRoomActivity.class);
                startActivity(intent);
            }
        });


        addSchedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this,AddScheduleActivity.class);
                startActivity(intent);
            }
        });



        viewSchedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this,ViewScheduleActivity.class);
                startActivity(intent);

            }
        });


        availableRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this,AvailableRoomsActivity.class);
                startActivity(intent);

            }
        });
    }
}
