package com.example.moviles.listas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

ArrayList<Estudiante> estudiantes;
Activity activity;



public Adaptador(Activity activity){
    this.activity = activity;
    estudiantes = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return estudiantes.size();
    }

    @Override
    public Object getItem(int i) {
        return estudiantes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
//UN renglon de la lista
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(activity);
       View renglon = inflater.inflate(R.layout.renglon,null,false);
        TextView item_nombre = renglon.findViewById(R.id.item_nombre);
        TextView item_codigo = renglon.findViewById(R.id.item_codigo);

        item_nombre.setText(estudiantes.get(position).getNombre());
        item_codigo.setText(estudiantes.get(position).getCodigo());

        return renglon;
    }

    public void agregarEstudiante (Estudiante e){
    estudiantes.add(e);

    notifyDataSetChanged();
    }
}
