package com.lorenzo.mytabapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NuovaLista extends AppCompatActivity {

    private ArrayList<EsempioProdotto> mEsempioLista;

    private RecyclerView m_recyclerView;
    private EsempioAdapter m_Adapter;
    private RecyclerView.LayoutManager m_layoutManager;

    private Button btnInserisci;
    private EditText editInserisciProdotto, editInserisciQuantita;

    public DataBaseHelper mDataBaseHelper;
    private Button btnSalva;
    private String nomeListaF;
    private TextView txtNomeLista;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuova_lista_layout);
        mDataBaseHelper = new DataBaseHelper(NuovaLista.this);


        buildRecyclerView();
        setButtons();


        CreateNewTable();


        //Toast.makeText(NuovaLista.this, nomeListaF, Toast.LENGTH_SHORT).show();
    }

    public void CreateNewTable() {
        nomeListaF = getIntent().getExtras().getString("nomeLista");
        txtNomeLista = findViewById(R.id.txt_nome_lista);
        txtNomeLista.setText(nomeListaF);
        mDataBaseHelper.creaNuovaTabella(nomeListaF);

        Toast.makeText(NuovaLista.this, "Perfavore inserisci almeno il campo prodotto", Toast.LENGTH_LONG).show();



    }


    public void inserisciProdotto(int position) {

        String testo1, testo2;
        testo1 = editInserisciProdotto.getText().toString();
        testo2 = editInserisciQuantita.getText().toString();

        mEsempioLista.add(position, new EsempioProdotto(R.drawable.ic_delete, R.drawable.ic_replace, "Prodotto: " + testo1, "Quantità: " + testo2));
        m_Adapter.notifyItemInserted(position);

        mDataBaseHelper.addProdotto(testo1, testo2);
        clearInserisciProdotto();
    }

    public void clearInserisciProdotto() {
        editInserisciProdotto.setText("");
        editInserisciQuantita.setText("");
    }

    public void changeItem(int position, String prodotto, String quantita) {
        mEsempioLista.get(position).setM_textProdotto(prodotto);
        mEsempioLista.get(position).setM_textQuantita(quantita);
        m_Adapter.notifyItemChanged(position);
    }


    public void buildRecyclerView() {

        mEsempioLista = new ArrayList<>();
        m_recyclerView = findViewById(R.id.recyclerView_nuovaLista);
        m_recyclerView.setHasFixedSize(true);
        m_Adapter = new EsempioAdapter(mEsempioLista);
        m_layoutManager = new LinearLayoutManager(this);

        m_recyclerView.setLayoutManager(m_layoutManager);
        m_recyclerView.setAdapter(m_Adapter);


        m_Adapter.setOnItemClickListener(new EsempioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // changeItem(position, "clicked", "cazzo");
            }

            @Override
            public void onDeleteClick(int position) {
                mEsempioLista.remove(position);
                m_Adapter.notifyItemRemoved(position);
            }

            @Override
            public void onModificaClick(int position) {
                openDialog(position);
                m_Adapter.notifyItemChanged(position);
            }
        });
    }

    private void setButtons() {

        btnInserisci = findViewById(R.id.btn_inserisciProdotto);
        editInserisciProdotto = findViewById(R.id.edit_inserisciProdotto);
        editInserisciQuantita = findViewById(R.id.edit_inserisciQuantita);

        btnSalva = findViewById(R.id.btn_salvaLista);

        btnInserisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mEsempioLista.size();
                inserisciProdotto(position);
            }
        });

        btnSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NuovaLista.this, PrimeList.class);
                intent.putExtra("nomePrimeList", nomeListaF);
                startActivity(intent);
            }
        });
    }

    private void openDialog(int position) {

        Dialog dialog = new Dialog(NuovaLista.this);
        dialog.setContentView(R.layout.update_product_layout);

        EditText editProdotto = dialog.findViewById(R.id.edit_modifica_prodotto);
        EditText editQuantita = dialog.findViewById(R.id.edit_modifica_quantita);
        Button btnModifica = dialog.findViewById(R.id.btn_modificaProQua);
        dialog.show();


        //    String txt_prodotto = mEsempioLista.get(position).getM_textProdotto();
        //    String txt_quantita = mEsempioLista.get(position).getM_textQuantita();


        btnModifica.setOnClickListener(new View.OnClickListener() {
            String prodotto, quantita;

            @Override
            public void onClick(View v) {

                if (!editProdotto.getText().toString().equals("")) {
                    prodotto = editProdotto.getText().toString();
                    quantita = editQuantita.getText().toString();
                    changeItem(position, prodotto, quantita);
                } else {

                    Toast.makeText(NuovaLista.this, "Perfavore inserisci almeno il campo prodotto", Toast.LENGTH_LONG).show();

                }

                dialog.dismiss();
            }
        });
    }

}
