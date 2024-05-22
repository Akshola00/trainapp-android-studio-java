package com.example.project;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {newTicket.class}, version = 1)
public abstract class newTicketDatabase extends RoomDatabase {
    public abstract newTicketDAO getnewTicketDAO();
}
