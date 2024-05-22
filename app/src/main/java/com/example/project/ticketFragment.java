package com.example.project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ticketFragment extends Fragment {
    ArrayList<tm> tmss = new ArrayList<>(); // Initialize the ArrayList
    rva2 adapter;

    newTicketDatabase newTicketDatabase;
    List<newTicket> ticketArrayList;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
//    tm mtm = new tm();

    FirebaseUser user;
    FirebaseAuth auth;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        RecyclerView recyclerView = view.findViewById(R.id.trv);
        adapter = new rva2(getContext(), tmss); // Initialize the adapter and assign to the class member
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                super.onDestructiveMigration(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        newTicketDatabase = Room.databaseBuilder(getContext(), newTicketDatabase.class, "newTicketDatabase")
                .addCallback(myCallBack)
                .build();

        getallticket();
    }

    public void getallticket() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String cuser = user.getEmail();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                tmss.clear(); // Clear the old data
                ticketArrayList = newTicketDatabase.getnewTicketDAO().getticket(cuser);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (newTicket n : ticketArrayList) {
                            String tmtxt = "Passenger Name: " + n.passangername +
                                    " \n Age: " + n.passangerage + " Gender: " +
                                    n.passangergender + " \n Phone No: " + n.passangerphone +
                                    " \n Train Data: \n Train Name: " + n.trainhead +
                                    " \n About Train: " + n.trainbody;
                            tmss.add(new tm(tmtxt)); // Add new tm object to the list
                        }
                        adapter.notifyDataSetChanged(); // Notify adapter about data changes
                    }
                });
            }
        });
    }
}
