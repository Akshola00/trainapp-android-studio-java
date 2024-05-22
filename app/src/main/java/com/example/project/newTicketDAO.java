package com.example.project;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface newTicketDAO {

    @Insert
    void addticket(newTicket newTicket);

    @Query("select * from newTicket where user == :user")
    List<newTicket> getticket(String user);
}
