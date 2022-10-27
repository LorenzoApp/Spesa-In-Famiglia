package com.lorenzo.mytabapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PrimeList extends AppCompatActivity {

    RecyclerView recyclerView;
    DataBaseHelper myDb;

    ArrayList<String> prodotto_id, prodotto_nome, prodotto_quantita;


    private ArrayList<PrimeEsempioProdotto> pEsempioLista;

    private RecyclerView p_recyclerView;
    private PrimeAdapter p_Adapter;
    private RecyclerView.LayoutManager p_layoutManager;
    public String nomePrimeLista;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prime_list_layout);

      nomePrimeLista = getIntent().getExtras().getString("nomePrimeList");


        recyclerView = findViewById(R.id.recyclerView_primeList);

        myDb = new DataBaseHelper(PrimeList.this);
        prodotto_id = new ArrayList<>();
        prodotto_nome = new ArrayList<>();
        prodotto_quantita = new ArrayList<>();

        buildPrimeRecycleView();
        prodottiInArray();

    }

    private void buildPrimeRecycleView() {

        pEsempioLista = new ArrayList<>();
        p_recyclerView = findViewById(R.id.recyclerView_primeList);
        p_recyclerView.setHasFixedSize(true);
        p_Adapter = new PrimeAdapter(pEsempioLista);
        p_layoutManager = new LinearLayoutManager(this);

        p_recyclerView.setLayoutManager(p_layoutManager);
        p_recyclerView.setAdapter(p_Adapter);

        p_Adapter.setOnItemClickListener(new PrimeAdapter.OnItemClickListener() {
            @Override
            public void onBuyClick(int position) {
                // changeItem(position, "clicked", "cazzo");
            }
        });
    }


    public void prodottiInArray() {

       // String nomeListaF = getIntent().getExtras().getString("nomeLista");
        Cursor cursor = myDb.readAllProdotti(nomePrimeLista);

        if (cursor.getCount() == 0) {
            Toast.makeText(PrimeList.this, "La lista è vuota", Toast.LENGTH_LONG).show();
        } else {
            //cursor.moveToFirst();
            while (cursor.moveToNext()) {
                String s2 = cursor.getString(1);
                String s3 = cursor.getString(2);

                int position = pEsempioLista.size();
                inserisciProdotto(position, s2, s3);
            }
        }
    }

    private void inserisciProdotto(int position, String t1, String t2) {
        pEsempioLista.add(position, new PrimeEsempioProdotto(t1, t2));
    }

}