package com.example.extermination.obtenpropina;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Lista extends Fragment {
    public static ArrayList<String> data =  new ArrayList<>();
    public static  ArrayAdapter<String> adapter;

    public Lista() {
        // Required empty public constructor
        //created changes
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        //data.add("test");
         adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                data);
        ListView listvw =  (ListView) view.findViewById(R.id.listview);
        listvw.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        return view;
    }

    public static void agregarProp(String propina){
        data.add(propina);
        adapter.notifyDataSetChanged();
    }
    public static void limpiarLista(){
        data.clear();
        adapter.notifyDataSetChanged();
    }

}
