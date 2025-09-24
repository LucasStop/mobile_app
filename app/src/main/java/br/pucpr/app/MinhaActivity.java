package br.pucpr.app;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MinhaActivity extends AppCompatActivity {

    private Button btnIniciar, btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_minha);

        btnIniciar = findViewById(R.id.btnIniciar);
        btnConsultar = findViewById(R.id.btnConsultar);

        btnConsultar.setEnabled(false);

        EditText edtPrimeiro = findViewById(R.id.edtPrimeiro);
        EditText edtSegundo = findViewById(R.id.edtSegundo);

        btnIniciar.setOnClickListener(v -> {

            double primeiro = Double.parseDouble(edtPrimeiro.getText().toString());
            double segundo = Double.parseDouble(edtSegundo.getText().toString());

            Intent intent = new Intent(this, MeuService.class);
            intent.putExtra("primeiro", primeiro);
            intent.putExtra("segundo", segundo);

            startService(intent);
        });

        btnConsultar.setOnClickListener(v -> {
            Intent intent = new Intent(this, SegundaActivity.class);
            startActivity(intent);
        });
    }

    private BroadcastReceiver meuReceiver;

    @Override
    protected void onStart() {
        super.onStart();

        meuReceiver = new MeuReceiver(btnConsultar);
        registerReceiver(meuReceiver, new IntentFilter("CALCULO_FINALIZADO"), Context.RECEIVER_EXPORTED);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(meuReceiver);
    }
}
