package com.example.project;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Ticket.class}, version = 1)
public abstract class TicketDatabase extends RoomDatabase {
    public abstract TicketDAO getTicketDAO();

}
