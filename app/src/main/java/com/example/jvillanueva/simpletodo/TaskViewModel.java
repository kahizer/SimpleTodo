package com.example.jvillanueva.simpletodo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by kahizer on 1/18/2016.
 */
public class TaskViewModel implements Parcelable{
    public String tittle;
    public String description;
    public String priority;
    public String status;
    public Date date;

    public TaskViewModel(String tittle, String description, String priority, String status, Date date){
        this.tittle = tittle;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.date = date;
    }

    // Parcelling part
    public TaskViewModel(Parcel in){
        String[] data = new String[5];

        in.readStringArray(data);
        this.tittle = data[0];
        this.description = data[1];
        this.priority = data[2];
        this.status = data[3];
        this.date = new Date(data[4]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.tittle, this.description, this.priority, this.status, this.date.toString()
        } );
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public TaskViewModel createFromParcel(Parcel in) {
            return new TaskViewModel(in);
        }

        public TaskViewModel[] newArray(int size) {
            return new TaskViewModel[size];
        }
    };
}
