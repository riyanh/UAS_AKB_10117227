package com.example.a10117227_uasif7.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.a10117227_uasif7.R;
import com.example.a10117227_uasif7.model.DaftarModel;
import com.example.a10117227_uasif7.model.db.DbOpenHelper;
import com.example.a10117227_uasif7.utility.DaftarAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class DestiFragment extends Fragment {
    //    Tanggal Pengerjaan : 7 Agustus 2020
    //    Nim : 10117227
    //    Nama : Mohamad Riyan Hidayat
    //    Kelas : IF - 7

    public DestiFragment() {
        // Required empty public constructor
    }

    Button btn_tambah;
    DbOpenHelper myDb;

    ListView mListView;
    DaftarAdapter daftarAdapter = null;
    ArrayList<DaftarModel> mList;

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_desti, container, false);
        mListView = root.findViewById(R.id.listView);

//        try {
//            JSONObject obj = new JSONObject(LoadJsonFromAssets());
//            JSONArray jarray = obj.getJSONArray("wisata");
//            HashMap<String, String> list;
//            ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
//            for(int i = 0; i < jarray.length(); i++){
//                JSONObject o = jarray.getJSONObject(i);
//                String wisata_id = o.getString("id");
//                String wisata_nama = o.getString("nama");
//                String wisata_gambar = o.getString("gambar_url");
//                String wisata_alamat = o.getString("alamat");
//                list = new HashMap<>();
//                list.put("id", wisata_id);
//                list.put("nama", wisata_nama);
//                list.put("gambar_url", wisata_gambar);
//                list.put("alamat", wisata_alamat);
//                arrayList.add(list);
//            }
//            daftarAdapter = new DaftarAdapter(getActivity(), R.layout.card_item_daftar, arrayList);
//            mListView.setAdapter(daftarAdapter);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        mList = new ArrayList<>();
        daftarAdapter = new DaftarAdapter(getActivity(), R.layout.card_item, mList);
        mListView.setAdapter(daftarAdapter);
        myDb = new DbOpenHelper(getActivity());
        updateRecordList();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = myDb.getAllData();
                ArrayList<Integer> arrNim = new ArrayList<Integer>();
                while (cursor.moveToNext()){ arrNim.add(cursor.getInt(0)); }
                Log.d("====>", "onClick: "+position);
                showDialogUpdate(getActivity(), arrNim.get(position).toString());
            }
        });
        return root;
    }

    public String LoadJsonFromAssets(){
        String json = null;
        try {
            InputStream in = this.getActivity().getAssets().open("wisata.json");
            int size = in.available();
            byte[] bbufer = new byte[size];
            in.read(bbufer);
            in.close();
            json = new String(bbufer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }


    private void showDialogUpdate(Activity activity, final String val_nim){
        String valNim = "";
        String valNama = "";
        String valKelas = "";
        String valTelp = "";
        String valEmail = "";
        String valSosmed = "";

        Cursor data      = myDb.getData(val_nim);
        while (data.moveToNext()) {
            valNim = data.getString(0);
            valNama = data.getString(1);
            valKelas = data.getString(2);
            valTelp = data.getString(3);
        }

        Intent intent = new Intent(activity, DetailFragment.class);
        intent.putExtra("id", valNim);
        intent.putExtra("nama", valNama);
        intent.putExtra("alamat", valKelas);
        intent.putExtra("website", valTelp);
        startActivity(intent);

    }

    public void updateRecordList(){
        try {
            String valNama = "";
            JSONObject obj = new JSONObject(LoadJsonFromAssets());
            JSONArray jarray = obj.getJSONArray("wisata");
            mList.clear();
            ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
            for(int i = 0; i < jarray.length(); i++){
                JSONObject o = jarray.getJSONObject(i);
                String wisata_id = o.getString("id");
                String wisata_nama = o.getString("nama");
                String wisata_website = o.getString("website");
                String wisata_alamat = o.getString("alamat");
                Cursor data = myDb.getData(wisata_id);
                while (data.moveToNext()) { valNama = data.getString(1); }
                if (wisata_nama != valNama) {
                    myDb.insertData(wisata_nama, wisata_website, wisata_alamat);
                }
                mList.add(new DaftarModel(wisata_nama, wisata_website, wisata_alamat));
            }
            Log.d("TAG", "onCreateView: "+arrayList);
            daftarAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
