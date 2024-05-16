package com.example.project;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Ticket.class}, version = 2)
public abstract class TicketDatabase extends RoomDatabase {
    private static TicketDatabase instance;

    public abstract TicketDAO getTicketDAO();

    // Define the migration from version 1 to version 2
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Define your migration logic here if needed
            // This is called when migrating from version 1 to version 2
        }
    };

    // Apply the destructive migration strategy when building the database
    public static TicketDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TicketDatabase.class, "ticket-database")
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
