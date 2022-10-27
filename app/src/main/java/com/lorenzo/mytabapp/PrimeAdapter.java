package com.lorenzo.mytabapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PrimeAdapter extends RecyclerView.Adapter<PrimeAdapter.PrimeViewHolder> {

    private ArrayList<PrimeEsempioProdotto> pEsempioProdotto;
    private OnItemClickListener pListener;
    //Context context;
    //ArrayList prodotto_id, prodotto_nome, prodotto_quantita;

    public interface OnItemClickListener {
        void onBuyClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.pListener = listener;
    }

    public static class PrimeViewHolder extends RecyclerView.ViewHolder {

        public ImageView pImageViewC;
        public TextView pTextViewProdotto;
        public TextView pTextViewQuantita;

        public PrimeViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            pImageViewC = itemView.findViewById(R.id.imageView_compra_prime);
            pTextViewProdotto = itemView.findViewById(R.id.textView_prodotto_prime);
            pTextViewQuantita = itemView.findViewById(R.id.textView_quantita_prime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onBuyClick(position);
                        }
                    }
                }
            });
        }
    }

    /*public PrimeAdapter(Context context, ArrayList prodotto_id, ArrayList prodotto_nome, ArrayList prodotto_quantita) {
        this.context = context;
        this.prodotto_id = prodotto_id;
        this.prodotto_nome = prodotto_nome;
        this.prodotto_quantita = prodotto_quantita;
    }*/

    public PrimeAdapter(ArrayList<PrimeEsempioProdotto> primeProdotto) {

        this.pEsempioProdotto = primeProdotto;
    }

    @NonNull
    @Override
    public PrimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prodotto_stampato, parent, false);
        PrimeViewHolder primeViewHolder = new PrimeViewHolder(view, pListener);
        return primeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PrimeViewHolder holder, int position) {

        PrimeEsempioProdotto currentItem = pEsempioProdotto.get(position);
        holder.pImageViewC.setImageResource(currentItem.getImageCompra());
        holder.pTextViewProdotto.setText(currentItem.getP_textProdotto());
        holder.pTextViewQuantita.setText(currentItem.getP_textQuantita());
    }

    @Override
    public int getItemCount() {
        return pEsempioProdotto.size();
    }


}
