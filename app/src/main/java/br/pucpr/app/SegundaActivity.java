package br.pucpr.app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SegundaActivity extends AppCompatActivity {

    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        TextView txtResultado = findViewById(R.id.txtResultado);

        Cursor cursor = getContentResolver().query(
                MeuProvider.CONTENT_URI,
                new String[]{"resultado"},
                null, null,
                "_id DESC LIMIT 1");

        if (cursor != null && cursor.moveToFirst()) {
            int resultado = cursor.getInt(0);
            txtResultado.setText("Resultado: " + resultado);
            cursor.close();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(SegundaActivity.this, MinhaActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}
