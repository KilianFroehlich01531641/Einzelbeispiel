package com.example.einzelarbeit_01531641;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //speichern der verschiedenen Komponenten als variablen
        TextView response = findViewById(R.id.response);
        EditText mtnr = (EditText) findViewById(R.id.editTextNumber);
        Button button_send_Mtnr = findViewById(R.id.button_send_Mtnr);
        Button quersumme = findViewById(R.id.quersumme);

        //button lister erstellen für send
        button_send_Mtnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response.setVisibility(View.VISIBLE);   //macht die anzeige für den Server status visible
                //int mtnrInt = Integer.parseInt(mtnr.getText().toString());   // ließt die eingabe als int aus und speichert sie
                String mtnrString = mtnr.getText().toString();
                UniThread uniconnection = new UniThread(mtnrString);
                uniconnection.start();
                try {
                    uniconnection.join();
                }catch (InterruptedException ie){

                }
                response.setText("Der Server sagt: " + uniconnection.getAnswer());
            }
        });

        quersumme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response.setVisibility(View.VISIBLE);
                response.setText("Die Alternierende Quersummer von " + mtnr.getText().toString() + " ist: " +quersummeCalc(mtnr.getText().toString()));
            }
        });
    }

    public int quersummeCalc(String mtnrString){
        int result= 0;
        for (int i = 0; i < mtnrString.length(); i++) {
            if(i % 2 == 0){
                result += mtnrString.charAt(i);
            }else{
                result -= mtnrString.charAt(i);
            }
        }
        return result;
    }



}