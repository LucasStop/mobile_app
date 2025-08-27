package br.pucpr.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MeuReceiver extends BroadcastReceiver {
    private final Button btnConsultar;

    public MeuReceiver(Button btnConsultar) {
        this.btnConsultar = btnConsultar;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AppVazio", "Intent recebida: " + intent.getAction());
        Toast.makeText(context, "Cálculo finalizado!", Toast.LENGTH_SHORT).show();
        btnConsultar.setEnabled(true);
    }
}