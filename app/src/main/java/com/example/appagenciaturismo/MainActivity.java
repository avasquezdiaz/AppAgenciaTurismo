package com.example.appagenciaturismo;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.text.Format;




public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] tDestino = {"Cartagena","Santa Marta","San Andrés","Medellín"};
    String DestinoSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instanciar y referenciar los IDs del archivo xml
        EditText nombre =  findViewById(R.id.etnombre);
        Spinner Destino = findViewById(R.id.spdestino);
        EditText FechaSalida =  findViewById(R.id.etfecha);
        EditText numeroPersonas =  findViewById(R.id.etnropersonas);
        RadioButton rb2 = findViewById(R.id.rb2);
        RadioButton rb4 = findViewById(R.id.rb4);
        RadioButton rb6 = findViewById(R.id.rb6);
        TextView TotalPago = findViewById(R.id.tvTotalPago);
        Button calcular = findViewById(R.id.btncalcular);
        Button limpiar = findViewById(R.id.btnclean);
        Switch TourCiudad= findViewById(R.id.swTourCiudad);
        Switch Discoteca= findViewById(R.id.swDiscoteca);
        TextView cuota = findViewById(R.id.tvTotalPago);

        //crear el adaptador que contendra el arreglo tDestino
        ArrayAdapter AdptpDestino = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, tDestino);

        //asignar el adaptador al spinner
        Destino.setAdapter(AdptpDestino);


        //checkear el tipo de destino seleccionado
        Destino.setOnItemSelectedListener(this);

        //evento en click calcular
        calcular.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (!nombre.getText().toString().isEmpty()) {
                    if (!FechaSalida.getText().toString().isEmpty()) {
                        if (!numeroPersonas.getText().toString().isEmpty()) {
                            if (parseDouble(numeroPersonas.getText().toString()) >= 1 && parseDouble(numeroPersonas.getText().toString()) <= 10) {
                                double xtotalpersona = parseDouble(numeroPersonas.getText().toString());
                                double tour = xtotalpersona * 60000;
                                double disco = xtotalpersona * 80000;
                                double totalPagar = 0;
                                double descuentosintour = 0;
                                DecimalFormat valueFormat = new DecimalFormat("###,###,###,###.#");

                                //chequear el tipo de destino seleccionado
                                double valor = 0;
                                int dias = 0;

                                if (rb2.isChecked()) {
                                    dias = 2;
                                }
                                if (rb4.isChecked()) {
                                    dias = 4;
                                }
                                if (rb6.isChecked()) {
                                    dias = 6;
                                }

                                switch (DestinoSel) {
                                    case "Cartagena":
                                        valor = 200000;
                                        break;
                                    case "Santa Marta":
                                        valor = 180000;
                                        break;
                                    case "San Andrés":
                                        valor = 170000;
                                        break;
                                    case "Medellín":
                                        valor = 190000;
                                }


                                totalPagar = (valor * xtotalpersona * dias );
                                descuentosintour = (valor * xtotalpersona * dias) * 10 / 100;

                                if (xtotalpersona > 5) {
                                    totalPagar = totalPagar - descuentosintour;
                                }


                                if (TourCiudad.isChecked()) {
                                    totalPagar = totalPagar + tour;
                                }
                                if (Discoteca.isChecked()) {
                                    totalPagar = totalPagar + disco;
                                }

                                cuota.setText(valueFormat.format(totalPagar));

                            } else {
                                Toast.makeText(getApplicationContext(), "Numero de personas debe de ser entre 1 y 10", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Numero de personas obligatorio", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Fecha obligatorio", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Nombre obligatorio", Toast.LENGTH_SHORT).show();
                }
            }

        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.setText("");
                FechaSalida.setText("");
                numeroPersonas.setText("");
                TourCiudad.setChecked(false);
                Discoteca.setChecked(false);
                cuota.setText("");
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        DestinoSel = tDestino[position];



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}