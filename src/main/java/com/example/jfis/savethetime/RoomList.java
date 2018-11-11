package com.example.jfis.savethetime;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RoomList extends ArrayAdapter {

    private Activity context ;
    List<Room> rooms ;

    public RoomList(Activity context , List<Room> rooms){
        super(context,R.layout.layout_room_list ,rooms);
        this.context = context ;
        this.rooms = rooms ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();
        View listViewItems =inflater.inflate(R.layout.layout_room_list,null,true);
        TextView roomid = listViewItems.findViewById(R.id.roomIdView);
        TextView department =listViewItems.findViewById(R.id.departmentView);
        TextView roomType =listViewItems.findViewById(R.id.roomTypeDialog);
        Room room = rooms.get(position);

        roomid.setText(room.getRoomId());
        department.setText(room.getDepartment());
        roomType.setText(room.getRoomType());
        return listViewItems;
    }

}
