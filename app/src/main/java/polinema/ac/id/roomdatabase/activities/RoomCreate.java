package polinema.ac.id.roomdatabase.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import polinema.ac.id.roomdatabase.R;
import polinema.ac.id.roomdatabase.data.AppDatabase;
import polinema.ac.id.roomdatabase.data.entity.Barang;

public class RoomCreate extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "barangdb").build();

        final EditText edtNamaBarang = findViewById(R.id.edtNamaBarang);
        final EditText edtMerkBarang = findViewById(R.id.edtMerkBarang);
        final EditText edtHargaBarang = findViewById(R.id.edtHargaBarang);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        final Barang barang = (Barang)
                getIntent().getSerializableExtra("data");

        if (barang != null) {
            edtNamaBarang.setText(barang.getNamaBarang());
            edtMerkBarang.setText(barang.getMerkBarang());
            edtHargaBarang.setText(barang.getHargaBarang());
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    barang.setNamaBarang(edtNamaBarang.getText().toString());
                    barang.setMerkBarang(edtMerkBarang.getText().toString());
                    barang.setHargaBarang(edtHargaBarang.getText().toString());
                    updateData(barang);
                }
            });
        } else {
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Barang barang = new Barang();
                    barang.setNamaBarang(edtNamaBarang.getText().toString());
                    barang.setMerkBarang(edtMerkBarang.getText().toString());
                    barang.setHargaBarang(edtHargaBarang.getText().toString());
                    insertData(barang);
                }
            });
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void updateData(final Barang barang){
        new AsyncTask<Void, Void, Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.barangDao().updateBarang(barang);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(RoomCreate.this, "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void insertData(final Barang barang) {
        new AsyncTask<Void, Void, Long>() {

            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.barangDao().insertBarang(barang);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(RoomCreate.this, "status row " + status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, RoomCreate.class);
    }

}
