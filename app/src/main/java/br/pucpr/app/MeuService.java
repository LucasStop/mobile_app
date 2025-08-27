package br.pucpr.app;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

public class MeuService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        double primeiro = intent.getDoubleExtra("primeiro", 0.0);
        double segundo = intent.getDoubleExtra("segundo", 0.0);

//        new Thread(() -> {
//            try {
        // Delay de 2 segundos
//                Thread.sleep(2000);
        double resultado = 0;
        for (int i = 0; i < segundo; i++) {
            resultado += primeiro;
        }

        gravarResultadoNoBanco((int) resultado);

        Intent broadcastIntent = new Intent("CALCULO_FINALIZADO");
        sendBroadcast(broadcastIntent);

        Log.d("AppVazio", "Resultado: " + resultado);
        stopSelf();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }).start();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void gravarResultadoNoBanco(int resultado) {
        BancoHelper helper = new BancoHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("resultado", resultado);
        db.insert("resultados", null, values);
        db.close();
    }
}