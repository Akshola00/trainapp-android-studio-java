package com.example.project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ticketFragment extends Fragment {
    TicketDatabase ticketDatabase;
    TextView alltickets;
    List<Ticket> ticketArrayList;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ticketFragment() {
        // Required empty public constructor
    }

    public static ticketFragment newInstance(String param1, String param2) {
        ticketFragment fragment = new ticketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };
        ticketDatabase = Room.databaseBuilder(getContext(), TicketDatabase.class, "TicketDB").addCallback(myCallBack).build();

        getTicketInBakground();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alltickets = view.findViewById(R.id.alltickets);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false);

    }
    public void getTicketInBakground(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ticketArrayList = ticketDatabase.getTicketDAO().getTicket();

                //
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        StringBuilder sb = new StringBuilder();
                        for (Ticket t : ticketArrayList){
                            sb.append(t.getPassanger_name() + " boarding: " +  t.train_body);
                            sb.append("\n");
                            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        }
                        String finalData = sb.toString();
                        alltickets.setText(finalData);
                        Toast.makeText(getContext(), "final data is " + finalData, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}