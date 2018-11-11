package com.example.jfis.savethetime;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewScheduleActivity extends AppCompatActivity {

    private ListView classesListView, scheduleListView;
    private List classes, schedules;
    private DatabaseReference databaseReference;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
private String dayString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        databaseReference = FirebaseDatabase.getInstance().getReference("schedule");

        classes = new ArrayList<>();
        classesListView = findViewById(R.id.classesViewScheduleListView);

        schedules = new ArrayList<>();
        scheduleListView = findViewById(R.id.viewParticularClassViewScheduleListView);



        classesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        radioGroup = findViewById(R.id.dayViewScheduleRadioGroup);
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        radioButton = findViewById(selectedId);
                        dayString = radioButton.getText().toString();


                        schedules.clear();
                        String className = (String) classes.get(i);
                        Toast.makeText(ViewScheduleActivity.this, className + "", Toast.LENGTH_SHORT).show();

                        for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                            if (postSnapShot.getKey().equalsIgnoreCase(className)) {
                                for (DataSnapshot postSnapShot1 : postSnapShot.getChildren()) {
                                    if (postSnapShot1.getKey().equalsIgnoreCase(dayString)) {
                                        Toast.makeText(ViewScheduleActivity.this, postSnapShot1.child("lectur1").getValue().toString() + "", Toast.LENGTH_SHORT).show();

                                        Schedule schedule = new Schedule(postSnapShot1.child("lectur1").getValue().toString(), postSnapShot1.child("lectur2").getValue().toString(), postSnapShot1.child("lectur3").getValue().toString(),
                                                postSnapShot1.child("lectur4").getValue().toString(), postSnapShot1.child("lectur5").getValue().toString(), postSnapShot1.child("lectur6").getValue().toString(),
                                                postSnapShot1.child("lectur7").getValue().toString(), postSnapShot1.child("lectur7").getValue().toString(), postSnapShot1.child("lectur9").getValue().toString());
                                        schedules.add(schedule);
                                    }
                                }
                            }

                        }
                        scheduleList arrayAdapter1 = new scheduleList(ViewScheduleActivity.this, schedules);
                        scheduleListView.setAdapter(arrayAdapter1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                return true;
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                classes.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String className = postSnapshot.getKey();
                    classes.add(className);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewScheduleActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, classes);

                classesListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
