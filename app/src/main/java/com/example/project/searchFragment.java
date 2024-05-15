package com.example.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class searchFragment extends Fragment implements recyclerviewinterface {
    SearchView accfrag_from, accfrag_to;
    ImageButton accfrag_switch;
    Button accfrag_searchbtn;

    ArrayList<search> searchValuesModels = new ArrayList<search>();
//    private recyclerviewinterface recyclerviewinterface;
    ArrayList<String> searchlist;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;



    public searchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onItemClick (int position) {
        Intent intent = new Intent(getActivity(), detailttrain.class);
        showAlertDialouge("Response: " + "just clicked a button");
        Toast.makeText(getContext(), "lorem ipsum " + position, Toast.LENGTH_SHORT).show();
        intent.putExtra("head", searchValuesModels.get(position).getHead());
        intent.putExtra("body", searchValuesModels.get(position).getBody());
        intent.putExtra("price", searchValuesModels.get(position).getPrice());

        startActivity(intent);
    }
    public void showAlertDialouge(String Message){
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setTitle("Oops, Something went Wrong !").setMessage(Message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create();
        alertDialog.show();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        int max=300,min=100;

        final RecyclerView recyclerView = view.findViewById(R.id.myrecyclerview);


        String url = "https://irctc1.p.rapidapi.com/api/v3/trainBetweenStations?fromStationCode=BVI&toStationCode=NDLS&dateOfJourney=2024-05-15";

        super.onViewCreated(view, savedInstanceState);
        accfrag_from = view.findViewById(R.id.accfrag_from);
        accfrag_to = view.findViewById(R.id.accfrag_to);
        accfrag_switch = view.findViewById(R.id.accfrag_switch);
        accfrag_searchbtn = view.findViewById(R.id.accfrag_searchbtn);
        accfrag_searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialouge("Response: " + "Hold on");
                Log.d("searchFragment", "Button clicked");

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray availabletrains = response.getJSONArray("data");
                            for (int j = 0; j < availabletrains.length(); j++) {// Inside the onResponse method of your JsonObjectRequest
                                JSONObject trainObject = availabletrains.getJSONObject(j);
                                String trainName = trainObject.getString("train_name");
                                String fromStationName = trainObject.getString("from_station_name");
                                String toStationName = trainObject.getString("to_station_name");
                                String fromStaTime = trainObject.getString("from_sta");
                                String toStaTime = trainObject.getString("to_sta");
                                String distanceKM = trainObject.getString("distance");

                                search searchItem = new search();
                                searchItem.setHead(trainName);
                                String body = "From: " + fromStationName + " to " + toStationName
                                        + "\n Arrival: " + fromStaTime + " Departure: " + toStaTime
                                        + "\n Distance: " + distanceKM + "KM";
                                searchItem.setBody(body);

                                int intprice = min + (int)(Math.random() * ((max - min) + 1));
                                String price = "$" + intprice + ".00";
                                searchItem.setPrice(price);

                                searchValuesModels.add(searchItem);

                            }
                            searches adapter = new searches(getContext(), searchValuesModels, searchFragment.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            showAlertDialouge("An Error Occurred: " + e.toString());
                            Log.d("searchFragment", String.valueOf(e));
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showAlertDialouge("An Error Occurred: " + error.toString());
                        Log.d("searchFragment", String.valueOf(error));
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("X-RapidAPI-Key", "da1f632e13msh98c3bdbdee4427cp1d6fd5jsnfe1d29bdd539");
                        params.put("X-RapidAPI-Host", "irctc1.p.rapidapi.com");
                        return params;
                    }
                };

                Volley.newRequestQueue(getContext()).add(request);

            }
        });
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
//        setupsearchValues();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.myrecyclerview);
// Ensure recyclerView is not null before proceeding
        if (recyclerView != null) {
            searches adapter = new searches(getContext(), searchValuesModels, this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            Log.e("searchFragment", "RecyclerView is null");
        }
        return view;

    }



//    private void setupsearchValues(){
//        String[] head = getResources().getStringArray(R.array.head);
//        String[] body = getResources().getStringArray(R.array.body);
//        String[] price = getResources().getStringArray(R.array.price);
//
//        for (int i = 0; i<head.length; i++){
//            searchValuesModels.add(new search());
//        }
//    }
}
