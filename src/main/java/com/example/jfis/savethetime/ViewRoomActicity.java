package com.example.jfis.savethetime;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class ViewRoomActicity extends AppCompatActivity {

    private ListView roomListView;
    private List rooms;
    private DatabaseReference databaseReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_room_acticity);

        rooms = new ArrayList<>();

        roomListView = findViewById(R.id.roomListViewRoomListView);

        databaseReference= FirebaseDatabase.getInstance().getReference("rooms");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rooms.clear();
                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    Room room = postSnapShot.getValue(Room.class);
                    rooms.add(room);
                }

                RoomList roomAdapter = new RoomList(ViewRoomActicity.this,rooms);
                roomListView.setAdapter(roomAdapter);

                LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(ViewRoomActicity.this, R.anim.list_layout_controller);
                roomListView.setLayoutAnimation(controller);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
