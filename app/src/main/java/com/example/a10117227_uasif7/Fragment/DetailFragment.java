package com.example.a10117227_uasif7.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewFlipper;

import com.example.a10117227_uasif7.R;

public class DetailFragment extends AppCompatActivity {
    //    Tanggal Pengerjaan : 9 Agustus 2020
    //    Nim : 10117227
    //    Nama : Mohamad Riyan Hidayat
    //    Kelas : IF - 7

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_detail_fragment);

        if(getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            final EditText nama   = findViewById(R.id.nama_wisata);
            EditText alamat  = findViewById(R.id.alamat_wisata);
            EditText website   = findViewById(R.id.website_wisata);
            Button btn_lokasi = findViewById(R.id.lokasi_wisata);
            ViewFlipper v_flipper = findViewById(R.id.v_flipper);

            final String nama_wisata = bundle.getString("nama");
            nama.setText(nama_wisata);
            alamat.setText(bundle.getString("alamat"));
            website.setText(bundle.getString("website"));

            ///Ketika button lokasi di klik
            btn_lokasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ii = new Intent(DetailFragment.this, MapFragment.class);
                    ii.putExtra("nama_wisata", nama_wisata);
                    startActivity(ii);
                }
            });
        }
    }
}
