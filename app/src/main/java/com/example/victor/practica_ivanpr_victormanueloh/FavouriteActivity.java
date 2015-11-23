package com.example.victor.practica_ivanpr_victormanueloh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Victor e Ivan on 16/11/2015.
 */
public class FavouriteActivity extends AppCompatActivity{

    private FormaDatosList [] misDatos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.chooseOp);
    }

    /**
     * Acción de continuar para el panel de elección de tematica (Música, Películas...)
     * Este metodo gestiona la visitibidad de los paneles y genera el Spinner que deberá mostrarse
     * en la pantalla posterior en base a la opcion seleccionada en el conjunto de radioButton que
     * se encuentra en la vista con id chooseOp
     * @param v: Boton que usa este metodo
     */
    public void continua(View v){
        int idSeleccionado=((RadioGroup)findViewById(R.id.rGroup)).getCheckedRadioButtonId();
        int spinnerCarga;
        switch(idSeleccionado){
            default://SE PONE PARA QUE EN CASO (IMPOSIBLE TEÓRICAMENTE) DE QUE NO SEA NINGUNO, CARGE MUSICA
            case R.id.musicRB:
                spinnerCarga=R.array.catSpinnerMusica;
                break;
            case R.id.peliculasRB:
                spinnerCarga=R.array.catSpinnerPelicula;
                break;
            case R.id.seriesRB:
                spinnerCarga=R.array.catSpinnerSerie;
                break;
            case R.id.gamesRB:
                spinnerCarga=R.array.catSpinnerJuego;
                break;
        }
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, spinnerCarga, android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner cmbOpciones = (Spinner)findViewById(R.id.spinnerCategories);
        cmbOpciones.setAdapter(adaptador);
        cmbOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                        muestraMiembrosCategoria(parent.getItemAtPosition(position).toString());
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        //NO HACEMOS NADA
                    }
                });
        findViewById(R.id.chooseOp).setVisibility(View.GONE);
        findViewById(R.id.spinner).setVisibility(View.VISIBLE);

    }

    /**
     * Metodo que gestiona el acceso a los miembros de una categoria elegida en el spinner y se imprime
     * en el listview, esto significa crear y generar el listview.
     * @param categoria: String del item seleccionado
     */
    public void muestraMiembrosCategoria(String categoria){
//AQUI CREACION DEL LISTVIEW, FALTAN LOS DEMAS CASOS... INVESTIGAR SI HAY ALGUNA POSIBILIDAD ALTERNATIVA QUE NO SEA UN SWITCH CASE ABSOLUTAMENTE INFINITO...
        switch (categoria){
            case "Rock":
                misDatos=new FormaDatosList[]{/*AQUI LOS VALORES*/};
                break;
            case "Rap":
                misDatos=new FormaDatosList[]{/*AQUI LOS VALORES*/};
                break;
            case "Pop":
                misDatos=new FormaDatosList[]{/*AQUI LOS VALORES*/};
                break;
            case "Electronica":
                misDatos=new FormaDatosList[]{/*AQUI LOS VALORES*/};
                break;
        }
        ListView lista = (ListView) findViewById(R.id.listItems);
        AdapterListView adapter= new AdapterListView(this);
        lista.setAdapter(adapter);
        findViewById(R.id.spinner).setVisibility(View.GONE);
        findViewById(R.id.layList).setVisibility(View.VISIBLE);
    }

    /**
     * Método que gestiona los botones BACK que usaremos para cancelar la agregación de un favorito,
     * por lo que se llamará a onResponder(View v), que gestionará la devolución de información al
     * MainActivity
     * @param v es el botón que lo pulse
     */
    public void back(View v){
        onResponder(v);
    }

    /**
     * Metodo que recoge lo que se devuelve a la actividad principal (Menú inicial) y cierra esta
     * actividad.
     * @param v es el botón que lo pulse, si es agregar mandará la información para ser agregada,
     *          sino, al ser back, devolverá un vacio
     */
    public void onResponder(View v) {
        int resultado;
        Intent datos = new Intent();
        if(getResources().getResourceEntryName(v.getId()).startsWith("back")){
            resultado=RESULT_CANCELED;
        }else{
            resultado=RESULT_OK;
            //datos.putExtra("",);
        }
        setResult(resultado, datos);
        finish();
    }

    class AdapterListView extends ArrayAdapter<FormaDatosList> {

        public AdapterListView(Activity context){
            super(context,R.layout.layout_listview, misDatos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.layout_listview, null);
            TextView lblTitle = (TextView)item.findViewById(R.id.title);
            lblTitle.setText(misDatos[position].getTitle());
            TextView lblDescription = (TextView)item.findViewById(R.id.description);
            lblDescription.setText(misDatos[position].getDescription());
            ImageView lblImage = (ImageView)item.findViewById(R.id.imagen);
            lblImage.setImageResource(misDatos[position].getIcon());
            return(item);
        }
    }
}
