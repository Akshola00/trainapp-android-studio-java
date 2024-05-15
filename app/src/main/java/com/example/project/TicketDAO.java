package com.example.project;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface TicketDAO {


    @Insert
    public void  addTicket(Ticket ticket);


    @Query("Select * from ticket")
    public List<Ticket> getTicket();



    @Query("SELECT * FROM ticket WHERE user = :user")
    public Ticket getTicket(int user);
}
