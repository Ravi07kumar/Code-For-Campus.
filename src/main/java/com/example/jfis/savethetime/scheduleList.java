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

public class scheduleList extends ArrayAdapter {

    private Activity context ;
    List<Schedule> schedules ;

    public scheduleList(Activity context , List<Schedule> schedules){
        super(context,R.layout.layout_schedule_list ,schedules);
        this.context = context ;
        this.schedules = schedules ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();
        View listViewItems =inflater.inflate(R.layout.layout_schedule_list,null,true);
        TextView lecture1 = listViewItems.findViewById(R.id.lecture1RoomLayoutScheduleText);
        TextView lecture2 = listViewItems.findViewById(R.id.lecture2RoomLayoutScheduleText);
        TextView lecture3 = listViewItems.findViewById(R.id.lecture3RoomLayoutScheduleText);
        TextView lecture4 = listViewItems.findViewById(R.id.lecture4RoomLayoutScheduleText);
        TextView lecture5 = listViewItems.findViewById(R.id.lecture5RoomLayoutScheduleText);
        TextView lecture6 = listViewItems.findViewById(R.id.lecture6RoomLayoutScheduleText);
        TextView lecture7 = listViewItems.findViewById(R.id.lecture7RoomLayoutScheduleText);
        TextView lecture8 = listViewItems.findViewById(R.id.lecture8RoomLayoutScheduleText);
        TextView lecture9 = listViewItems.findViewById(R.id.lecture9RoomLayoutScheduleText);

        Schedule schedule = schedules.get(position);

        lecture1.setText(schedule.getLectur1());
        lecture2.setText(schedule.getLectur2());
        lecture3.setText(schedule.getLectur3());
        lecture4.setText(schedule.getLectur4());
        lecture5.setText(schedule.getLectur5());
        lecture6.setText(schedule.getLectur6());
        lecture7.setText(schedule.getLectur7());
        lecture8.setText(schedule.getLectur8());
        lecture9.setText(schedule.getLectur9());

        return listViewItems;
    }
}
