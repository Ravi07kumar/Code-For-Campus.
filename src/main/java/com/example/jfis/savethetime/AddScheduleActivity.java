package com.example.jfis.savethetime;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddScheduleActivity extends AppCompatActivity {

    private Spinner lecture1, lecture2, lecture3, lecture4, lecture5, lecture6, lecture7, lecture8, lecture9;
    private Button submit;
    private EditText className;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference, roomDatabaseReference;
    private ArrayList<String> roomidLecture1, roomidLecture2, roomidLecture3, roomidLecture4, roomidLecture5, roomidLecture6, roomidLecture7, roomidLecture8, roomidLecture9;
    private ArrayAdapter a1, a2, a3, a4, a5, a6, a7, a8, a9;
    private String dayString;
    private String[] lecturesss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

         lecturesss = new String[]{"lectur1", "lectur2", "lectur3", "lectur4", "lectur5", "lectur6", "lectur7", "lectur8", "lectur9"};



        roomidLecture1 = new ArrayList<>();
        roomidLecture2 = new ArrayList<>();
        roomidLecture3 = new ArrayList<>();
        roomidLecture4 = new ArrayList<>();
        roomidLecture5 = new ArrayList<>();
        roomidLecture6 = new ArrayList<>();
        roomidLecture7 = new ArrayList<>();
        roomidLecture8 = new ArrayList<>();
        roomidLecture9 = new ArrayList<>();

        roomidLecture1.add("none");
        roomidLecture2.add("none");
        roomidLecture3.add("none");
        roomidLecture4.add("none");
        roomidLecture5.add("none");
        roomidLecture6.add("none");
        roomidLecture7.add("none");
        roomidLecture8.add("none");
        roomidLecture9.add("none");


        className = findViewById(R.id.classNameAddScheduleText);

        lecture1 = findViewById(R.id.lecture1RoomAddScheduleText);
        lecture2 = findViewById(R.id.lecture2RoomAddScheduleText);
        lecture3 = findViewById(R.id.lecture3RoomAddScheduleText);
        lecture4 = findViewById(R.id.lecture4RoomAddScheduleText);
        lecture5 = findViewById(R.id.lecture5RoomAddScheduleText);
        lecture6 = findViewById(R.id.lecture6RoomAddScheduleText);
        lecture7 = findViewById(R.id.lecture7RoomAddScheduleText);
        lecture8 = findViewById(R.id.lecture8RoomAddScheduleText);
        lecture9 = findViewById(R.id.lecture9RoomAddScheduleText);

        a1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roomidLecture1);
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lecture1.setAdapter(a1);

        a2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roomidLecture2);
        a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lecture2.setAdapter(a2);
        a3 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roomidLecture3);
        a3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lecture3.setAdapter(a3);
        a4 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roomidLecture4);
        a4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lecture4.setAdapter(a4);
        a5 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roomidLecture5);
        a5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lecture5.setAdapter(a5);
        a6 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roomidLecture6);
        a6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lecture6.setAdapter(a6);
        a7 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roomidLecture7);
        a7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lecture7.setAdapter(a7);
        a8 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roomidLecture8);
        a8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lecture8.setAdapter(a8);
        a9 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roomidLecture9);
        a9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lecture9.setAdapter(a9);

        submit = findViewById(R.id.submitAddScheduleButton);
        radioGroup = findViewById(R.id.dayAddScheduleRadioGroup);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("schedule");

        roomDatabaseReference = FirebaseDatabase.getInstance().getReference("availableRooms");


        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedId);
        String data = radioButton.getText().toString();


        if (data.equals("Th")) {
            data = "H";
        }
        setData(data);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                radioButton = findViewById(i);
                String day = radioButton.getText().toString();
                String dayString = "";
                if (day.equals("M")) {
                    dayString = "Monday";
                } else if (day.equals("T")) {
                    dayString = "Tuesday";
                } else if (day.equals("W")) {
                    dayString = "Wednesday";
                } else if (day.equals("Th")) {
                    dayString = "Thursday";
                } else if (day.equals("F")) {
                    dayString = "Friday";
                }


                Toast.makeText(AddScheduleActivity.this, dayString + "  selected", Toast.LENGTH_SHORT).show();


                lecture1.setSelection(0);
                lecture2.setSelection(0);
                lecture3.setSelection(0);
                lecture4.setSelection(0);
                lecture5.setSelection(0);
                lecture6.setSelection(0);
                lecture7.setSelection(0);
                lecture8.setSelection(0);
                lecture9.setSelection(0);

                if (day.equals("Th")) {
                    day = "H";
                }
                setData(day);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String classNameString = className.getText().toString();

                final String lecture1String = lecture1.getSelectedItem().toString();
                final String lecture2String = lecture2.getSelectedItem().toString();
                final String lecture3String = lecture3.getSelectedItem().toString();
                final String lecture4String = lecture4.getSelectedItem().toString();
                final String lecture5String = lecture5.getSelectedItem().toString();
                final String lecture6String = lecture6.getSelectedItem().toString();
                final String lecture7String = lecture7.getSelectedItem().toString();
                final String lecture8String = lecture8.getSelectedItem().toString();
                final String lecture9String = lecture9.getSelectedItem().toString();

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                final String day = radioButton.getText().toString();
                dayString = "";
                if (day.equals("M")) {
                    dayString = "Monday";
                } else if (day.equals("T")) {
                    dayString = "Tuesday";
                } else if (day.equals("W")) {
                    dayString = "Wednesday";
                } else if (day.equals("Th")) {
                    dayString = "Thursday";
                } else if (day.equals("F")) {
                    dayString = "Friday";
                }

                if (classNameString.trim().isEmpty()) {
                    Toast.makeText(AddScheduleActivity.this, "Please enter class name first ", Toast.LENGTH_SHORT).show();

                } else {

                    databaseReference.child(classNameString).child(day).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()) {
                                Schedule schedule = new Schedule(lecture1String, lecture2String, lecture3String, lecture4String, lecture5String, lecture6String, lecture7String, lecture8String, lecture9String);
                                databaseReference.child(classNameString).child(day).setValue(schedule);
                                roomDatabaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                            if (postSnapshot.getKey().equals(day)) {
                                                for (DataSnapshot postSnapshot1 : postSnapshot.getChildren()) {
                                                    for (int i = 0; i < lecturesss.length; i++) {

                                                        if (postSnapshot1.getKey().equals(lecturesss[i])) {
                                                            for (DataSnapshot postSnapshot2 : postSnapshot1.getChildren()) {
                                                                String pgk=postSnapshot2.getKey().toString();
                                                                if (pgk.equals(lecture1String)) {
                                                                    roomDatabaseReference.child(day).child(lecturesss[i]).child(lecture1String).setValue("not available");
                                                                }
                                                                if (pgk.equals(lecture2String)) {
                                                                    roomDatabaseReference.child(day).child(lecturesss[i]).child(lecture2String).setValue("not available");
                                                                }
                                                                if (pgk.equals(lecture3String)) {
                                                                    roomDatabaseReference.child(day).child(lecturesss[i]).child(lecture3String).setValue("not available");
                                                                }
                                                                if (pgk.equals(lecture4String)) {
                                                                    roomDatabaseReference.child(day).child(lecturesss[i]).child(lecture4String).setValue("not available");
                                                                }
                                                                if (pgk.equals(lecture5String)) {
                                                                    roomDatabaseReference.child(day).child(lecturesss[i]).child(lecture5String).setValue("not available");
                                                                }
                                                                if (pgk.equals(lecture6String)) {
                                                                    roomDatabaseReference.child(day).child(lecturesss[i]).child(lecture6String).setValue("not available");
                                                                }
                                                                if (pgk.equals(lecture7String)) {
                                                                    roomDatabaseReference.child(day).child(lecturesss[i]).child(lecture7String).setValue("not available");
                                                                }
                                                                if (pgk.equals(lecture8String)) {
                                                                    roomDatabaseReference.child(day).child(lecturesss[i]).child(lecture8String).setValue("not available");
                                                                }
                                                                if (pgk.equals(lecture1String)) {
                                                                    roomDatabaseReference.child(day).child(lecturesss[i]).child(lecture9String).setValue("not available");
                                                                }

                                                            }
                                                        }                                                    }
                                                }
                                            }

                                        }
                                        Toast.makeText(AddScheduleActivity.this, " data added successfully for " + dayString, Toast.LENGTH_SHORT).show();
                                        lecture1.setSelection(0);
                                        lecture2.setSelection(0);
                                        lecture3.setSelection(0);
                                        lecture4.setSelection(0);
                                        lecture5.setSelection(0);
                                        lecture6.setSelection(0);
                                        lecture7.setSelection(0);
                                        lecture8.setSelection(0);
                                        lecture9.setSelection(0);
                                        setData(day);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            } else {
                                Toast.makeText(AddScheduleActivity.this, "Schedule of class " + classNameString + " on " + dayString + " already presents ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }


            }


        });

    }


    public void setData(final String data) {
        roomDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                roomidLecture1.clear();
                roomidLecture1.add("none");
                roomidLecture2.clear();
                roomidLecture2.add("none");
                roomidLecture3.clear();
                roomidLecture3.add("none");
                roomidLecture4.clear();
                roomidLecture4.add("none");
                roomidLecture5.clear();
                roomidLecture5.add("none");
                roomidLecture6.clear();
                roomidLecture6.add("none");
                roomidLecture7.clear();
                roomidLecture7.add("none");
                roomidLecture8.clear();
                roomidLecture8.add("none");
                roomidLecture9.clear();
                roomidLecture9.add("none");


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    if (postSnapshot.getKey().equals(data)) {
                        for (DataSnapshot postSnapshot1 : postSnapshot.getChildren()) {

                            for (int i = 0; i < lecturesss.length; i++) {

                                if (postSnapshot1.getKey().equals(lecturesss[i])) {
                                    for (DataSnapshot postSnapshot3 : postSnapshot1.getChildren()) {


                                        if (postSnapshot3.getValue().toString().equalsIgnoreCase("available")) {
                                            String pgt=postSnapshot3.getKey().toString();
                                            if (!roomidLecture1.contains(pgt)) {
                                                roomidLecture1.add(pgt);

                                            }
                                            if (!roomidLecture2.contains(pgt)) {
                                                roomidLecture2.add(pgt);

                                            }
                                            if (!roomidLecture3.contains(pgt)) {
                                                roomidLecture3.add(pgt);

                                            }if (!roomidLecture4.contains(pgt)) {
                                                roomidLecture4.add(pgt);

                                            }if (!roomidLecture5.contains(pgt)) {
                                                roomidLecture5.add(pgt);

                                            }if (!roomidLecture6.contains(pgt)) {
                                                roomidLecture6.add(pgt);

                                            }if (!roomidLecture7.contains(pgt)) {
                                                roomidLecture7.add(pgt);

                                            }if (!roomidLecture8.contains(pgt)) {
                                                roomidLecture8.add(pgt);

                                            }if (!roomidLecture9.contains(pgt)) {
                                                roomidLecture9.add(pgt);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                }
                a1.notifyDataSetChanged();
                a2.notifyDataSetChanged();
                a3.notifyDataSetChanged();
                a4.notifyDataSetChanged();
                a5.notifyDataSetChanged();
                a6.notifyDataSetChanged();
                a7.notifyDataSetChanged();
                a8.notifyDataSetChanged();
                a9.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
