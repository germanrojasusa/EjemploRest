package com.example.ejemplorest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private CharactersList charactersList;
    private List<Character> characters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);


    }
    public void obtenerDatos(View view){
        MiTarea tarea = new MiTarea();
        tarea.execute();
    }
    //AsyncTask
    class MiTarea extends AsyncTask<Integer, Integer, Integer> {
        //Lo que pasa antes de ejecutar el hilo (inicializaciones
        protected void onPreExecute() {
            //Inicializo mi listado de personajes
            charactersList = new CharactersList(MainActivity.this);
            textView.setText("Iniciando...");
        }

        //Lo que pasa en la ejecución del hilo (Este es el método que ejecuta en el execute()
        protected Integer doInBackground(Integer... n) {
            characters = charactersList.getAll();

            return characters.size();
        }

        //Lo que se debe ejecutar cuando termina la ejecución del hilo
        protected void onPostExecute(Integer res) {
            for (Character character:characters){
                textView.append("Nombre: " + character.getName() + "\n");
            }
        }
    }
}