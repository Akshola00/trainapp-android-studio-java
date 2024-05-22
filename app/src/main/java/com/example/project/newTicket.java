package com.example.project;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "newTicket")
public class newTicket {

    @ColumnInfo(name = "user")
    public String user;

    @ColumnInfo(name = "passangername")
    public String passangername;

    @ColumnInfo(name = "passangerage")
    public String passangerage;

    @ColumnInfo(name = "passangergender")
    public String passangergender;

    @ColumnInfo(name = "passangerphone")
    public String passangerphone;

    @ColumnInfo(name = "trainhead")
    public String trainhead;

    @ColumnInfo(name = "trainbody")
    public String trainbody;


    @ColumnInfo(name = "ticket_id")
    @PrimaryKey(autoGenerate = true)
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassangername() {
        return passangername;
    }

    public void setPassangername(String passangername) {
        this.passangername = passangername;
    }

    public String getPassangerage() {
        return passangerage;
    }

    public void setPassangerage(String passangerage) {
        this.passangerage = passangerage;
    }

    public String getPassangergender() {
        return passangergender;
    }

    public void setPassangergender(String passangergender) {
        this.passangergender = passangergender;
    }

    public String getPassangerphone() {
        return passangerphone;
    }

    public void setPassangerphone(String passangerphone) {
        this.passangerphone = passangerphone;
    }

    public String getTrainhead() {
        return trainhead;
    }

    public void setTrainhead(String trainhead) {
        this.trainhead = trainhead;
    }

    public String getTrainbody() {
        return trainbody;
    }

    public void setTrainbody(String trainbody) {
        this.trainbody = trainbody;
    }

    public newTicket(String user, String passangername, String passangerage, String passangergender, String passangerphone, String trainhead, String trainbody) {
        this.id = 0;
        this.user = user;
        this.passangername = passangername;
        this.passangerage = passangerage;
        this.passangergender = passangergender;
        this.passangerphone = passangerphone;
        this.trainhead = trainhead;
        this.trainbody = trainbody;
    }
}
