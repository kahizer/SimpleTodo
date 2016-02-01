package com.example.jvillanueva.simpletodo;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by kahizer on 1/18/2016.
 */
@Table(name = "TaskViewModels")
public class TaskViewModel implements Parcelable{
    @Column(name = "Tittle")
    public String tittle;

    @Column(name = "Description")
    public String description;

    @Column(name = "Priority")
    public String priority;

    @Column(name = "Status")
    public String status;

    @Column(name = "DueDate")
    public Date dueDate;

    @Column(name = "CreatedDate")
    public Date createdDate;

    public TaskViewModel(){
        super();
    }

    public TaskViewModel(String tittle, String description, String priority, String status, Date dueDate, Date createdDate){
        super();
        this.tittle = tittle;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
    }

    // Parcelling part
    public TaskViewModel(Parcel in){
        String[] data = new String[6];

        in.readStringArray(data);
        this.tittle = data[0];
        this.description = data[1];
        this.priority = data[2];
        this.status = data[3];
        this.dueDate = new Date(data[4]);
        this.createdDate = new Date(data[5]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.tittle, this.description, this.priority, this.status, this.dueDate.toString(), this.createdDate.toString()
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
