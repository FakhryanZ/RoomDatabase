package polinema.ac.id.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import polinema.ac.id.roomdatabase.activities.RoomCreate;
import polinema.ac.id.roomdatabase.activities.RoomRead;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTambah = findViewById(R.id.btnTambah);
        Button btnLihat = findViewById(R.id.btnLihat);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RoomCreate.class);
                startActivity(intent);
            }
        });

        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RoomRead.class);
                startActivity(intent);
            }
        });
    }
}
