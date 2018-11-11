package com.example.jfis.savethetime;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AvailableRoomsActivity extends AppCompatActivity {

    private RadioGroup dayRadioGroup, lectureRadioGroup;
    private RadioButton dayRadioButton, lectureRadioButton;
    private ListView roomIdListView;
    private DatabaseReference databaseReference;
    private List roomIdList;
    private Button checkButton;
    private String daySelectedString, lectureSelectedString;
    private ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_rooms);

        databaseReference = FirebaseDatabase.getInstance().getReference("availableRooms");

        dayRadioGroup = findViewById(R.id.dayAvailableRoomRadioGroup);
        lectureRadioGroup = findViewById(R.id.lectureAvailableRoomRadioGroup);
        roomIdListView = findViewById(R.id.roomIdAvailableRoomListView);
        checkButton = findViewById(R.id.checkRoomAvailableRoomButton);


        roomIdList = new ArrayList<>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, roomIdList);
        roomIdListView.setAdapter(aa);
        roomIdListView.setTextFilterEnabled(true);


        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomIdList.clear();
                int daySelected = dayRadioGroup.getCheckedRadioButtonId();
                int lectureSelected = lectureRadioGroup.getCheckedRadioButtonId();

                dayRadioButton = findViewById(daySelected);
                lectureRadioButton = findViewById(lectureSelected);

                daySelectedString = dayRadioButton.getText().toString();
                if (daySelectedString.equalsIgnoreCase("Th")) {
                    daySelectedString = "H";
                }
                lectureSelectedString = lectureRadioButton.getText().toString();

                if (lectureSelectedString.equals("1")) {
                    lectureSelectedString = "lectur1";
                }
                if (lectureSelectedString.equals("2")) {
                    lectureSelectedString = "lectur2";
                }
                if (lectureSelectedString.equals("3")) {
                    lectureSelectedString = "lectur3";
                }
                if (lectureSelectedString.equals("4")) {
                    lectureSelectedString = "lectur4";
                }
                if (lectureSelectedString.equals("5")) {
                    lectureSelectedString = "lectur5";
                }
                if (lectureSelectedString.equals("6")) {
                    lectureSelectedString = "lectur6";
                }
                if (lectureSelectedString.equals("7")) {
                    lectureSelectedString = "lectur7";
                }
                if (lectureSelectedString.equals("8")) {
                    lectureSelectedString = "lectur8";
                }
                if (lectureSelectedString.equals("9")) {
                    lectureSelectedString = "lectur9";
                }

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            if (postSnapshot.getKey().equals(daySelectedString)) {
                                for (DataSnapshot postSnapshot1 : postSnapshot.getChildren()) {
                                    if (postSnapshot1.getKey().equals(lectureSelectedString)) {
                                        for (DataSnapshot postSnapshot2 : postSnapshot1.getChildren()) {
                                            if (postSnapshot2.getValue().equals("available")) {
                                                roomIdList.add(postSnapshot2.getKey().toString());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        aa.notifyDataSetChanged();
                        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(AvailableRoomsActivity.this, R.anim.list_layout_controller);
                        roomIdListView.setLayoutAnimation(controller);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }


}

