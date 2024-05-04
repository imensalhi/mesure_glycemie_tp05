package com.example.salhi_imen_mesure_glycemie.view;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salhi_imen_mesure_glycemie.R;
import com.example.salhi_imen_mesure_glycemie.controller.Controller;

public class MainActivity extends AppCompatActivity {
    private final String RESPONSE_KEY = "result";
    private final int REQUEST_CODE =1;//code de ConsultActivity
    private EditText etValeur;
    private TextView tvAge; //tvReponse;
    private SeekBar sbAge;
    private RadioButton rbIsFasting, rbIsNotFasting;
    private Button btnConsulter;
    private Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean

                    fromUser) {

                Log.i("Information", "onProgressChanged "+progress);
                tvAge.setText("Votre âge : "+ progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age ;
                float valeur ;

                Log.i("Information", "button cliqué");
                boolean verifAge = false, verifValeur = false;
                if(sbAge.getProgress()!=0)
                    verifAge = true;
                else
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre age !", Toast.LENGTH_SHORT).show();

                if(!etValeur.getText().toString().isEmpty())
                    verifValeur = true;
                else
                   Toast.makeText(MainActivity.this, "Veuillez saisir votre valeur mesurée !", Toast.LENGTH_LONG).show();
                if(verifAge && verifValeur)
                {
                    age = sbAge.getProgress();
                    valeur=Float.valueOf(etValeur.getText().toString());
                    // Fleche "UserAction" View --> Controller
                    controller.createPatient(age,valeur,rbIsFasting.isChecked());
                    // Fleche "Notify" Controller --> View
                    //tvReponse.setText(controller.getResult());
                    Intent intent = new Intent(MainActivity.this,ConsultActivity.class);
                    intent.putExtra (RESPONSE_KEY,controller.getResult());
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE)
        {
            if (resultCode == RESULT_CANCELED)
                Toast.makeText(MainActivity.this,"ERROR: RESULT_CANCELED", Toast.LENGTH_LONG).show();
        }

    }

    private void init()
    {
        controller = Controller.getInstance();
        sbAge = findViewById(R.id.sbAge);
        tvAge = findViewById(R.id.tvAge);
        etValeur = findViewById(R.id.etValeur);
        rbIsFasting = findViewById(R.id.rbtOui);
        rbIsNotFasting = findViewById(R.id.rbtNon);
        btnConsulter = findViewById(R.id.btnConsulter);
        //tvReponse = findViewById(R.id.tvReponse);

    }
}