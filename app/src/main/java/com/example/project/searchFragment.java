package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class searchFragment extends Fragment {
    ArrayList<serach> searchs = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public searchFragment() {
        // Required empty public constructor
    }

    public static searchFragment newInstance(String param1, String param2) {
        searchFragment fragment = new searchFragment();
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
        setupsearch();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.myrecyclerview);
        searches adapter = new searches(getContext(), searchs);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void setupsearch(){
        String[] from = getResources().getStringArray(R.array.from);
        String[] to = getResources().getStringArray(R.array.to);
        String[] timefrom = getResources().getStringArray(R.array.timefrom);
        String[] timeto = getResources().getStringArray(R.array.timeto);

        for (int i = 0; i<from.length; i++){
            searchs.add(new serach(from[i], to[i], timefrom[i], timeto[i]));
        }
    }
}
