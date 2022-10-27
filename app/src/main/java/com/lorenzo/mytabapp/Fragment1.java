package com.lorenzo.mytabapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    private CardView nuovaLista, primeList, galleriaListe;
    private String nomeLista;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_fragment_1, container, false);

        nuovaLista = view.findViewById(R.id.card1_nuovaLista);
        galleriaListe = view.findViewById(R.id.card3_galleriaListe);
        primeList = view.findViewById(R.id.card2_primoPiano);


        nuovaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiamaDialog();
            }
        });

        primeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiamaPrimeList();
            }
        });

        /*galleriaListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fragment1.this.getActivity(), Galleria.class);
                startActivity(intent);
            }
        });

        primeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fragment1.this.getActivity(), NuovaLista.class);
                startActivity(intent);
            }
        });*/

        return view;
    }

    private void chiamaDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.nome_lista_layout);

        EditText editNomeLista = dialog.findViewById(R.id.edit_nome_lista);
        Button btnCreaLista = dialog.findViewById(R.id.btn_crea_lista);
        dialog.show();


        btnCreaLista.setOnClickListener(new View.OnClickListener() {
            String dnomeLista;

            @Override
            public void onClick(View v) {

                if (!editNomeLista.getText().toString().equals("")) {
                    dnomeLista = editNomeLista.getText().toString();
                    // Toast.makeText(getActivity(), dnomeLista, Toast.LENGTH_LONG).show();
                    nomeLista = dnomeLista;
                    chiamaNuovaLista();
                    dialog.dismiss();

                } else {
                    Toast.makeText(getActivity(), "Perfavore inserisci il nome della lista", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void chiamaNuovaLista() {
        Intent intent = new Intent(Fragment1.this.getActivity(), NuovaLista.class);
        intent.putExtra("nomeLista", nomeLista); // per passare una variabile da class to class
        startActivity(intent);
    }

    private void chiamaPrimeList() {
        Intent intent = new Intent(Fragment1.this.getActivity(), PrimeList.class);
        startActivity(intent);
    }

}