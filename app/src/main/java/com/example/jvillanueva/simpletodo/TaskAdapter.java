package com.example.jvillanueva.simpletodo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kahizer on 1/23/2016.
 */
public class TaskAdapter extends ArrayAdapter<TaskViewModel> {
    public TaskAdapter(Context context, ArrayList<TaskViewModel> tasks){
        super(context, 0 , tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TaskViewModel task = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);

        switch (task.status){
            case "TO-DO":
                switch (task.priority){
                    case "HIGH":
                        tvPriority.setTextColor(Color.RED);
                        tvTitle.setTextColor(Color.BLACK);

                        break;

                    case "MEDIUM":
                        tvPriority.setTextColor(Color.YELLOW);
                        tvTitle.setTextColor(Color.BLACK);
                        break;

                    case "LOW":
                        tvPriority.setTextColor(Color.BLUE);
                        tvTitle.setTextColor(Color.BLACK);
                        break;
                }
                break;

            case "DONE":
                tvPriority.setTextColor(Color.GRAY);
                tvTitle.setTextColor(Color.GRAY);
                break;
        }

        tvTitle.setText(task.tittle);
        tvPriority.setText(task.priority);

        return convertView;
    }
}