package com.example.jfis.savethetime;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddRoomActivity extends AppCompatActivity {
    private EditText roomId, roomType, department;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Button addButton;
    private DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("rooms");
        databaseReference1 = firebaseDatabase.getReference("availableRooms");

        roomId = findViewById(R.id.roomIdAddRoomText);
        roomType = findViewById(R.id.roomTypeAddRoomText);
        department = findViewById(R.id.departmentAddRoomText);
        addButton = findViewById(R.id.addRoomAddRoomButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String roomIdString = roomId.getText().toString();
                String roomTypeString = roomType.getText().toString();
                String departmentString = department.getText().toString();


                if (roomIdString.trim().isEmpty() || roomTypeString.trim().isEmpty() || departmentString.trim().isEmpty()) {
                    Toast.makeText(AddRoomActivity.this, "Please add proper details", Toast.LENGTH_SHORT).show();
                } else {
                    Room room = new Room(roomIdString, roomTypeString, departmentString);
                    databaseReference.child(roomIdString).setValue(room);
                    String[] lecturesss = {"lectur1", "lectur2", "lectur3", "lectur4", "lectur5", "lectur6", "lectur7", "lectur8", "lectur9"};
                    for (char c : "MTWHF".toCharArray()) {
                        for (int i = 0; i < lecturesss.length; i++) {

                            databaseReference1.child(c + "").child(lecturesss[i]).child(roomIdString).setValue("available");
                        }
                    }

                    Toast.makeText(AddRoomActivity.this, "data added successfully", Toast.LENGTH_SHORT).show();

                    roomId.setText("");
                    roomType.setText("");
                    department.setText("");
                }

            }
        });

    }
}
