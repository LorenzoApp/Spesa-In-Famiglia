package com.lorenzo.mytabapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EsempioAdapter extends RecyclerView.Adapter<EsempioAdapter.EsempioViewHolder> {

    private ArrayList<EsempioProdotto> mEsempioProdotto;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
        void onModificaClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public static class EsempioViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageViewE, mImageViewM;
        public TextView mTextViewProdotto;
        public TextView mTextViewQuantita;

        public EsempioViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            mImageViewM = itemView.findViewById(R.id.imageView_modifica);
            mImageViewE = itemView.findViewById(R.id.imageView_elimina);
            mTextViewProdotto = itemView.findViewById(R.id.textView_prodotto);
            mTextViewQuantita = itemView.findViewById(R.id.textView_quantita);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            mImageViewE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

            mImageViewM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onModificaClick(position);
                        }
                    }
                }
            });

        }
    }

    public EsempioAdapter(ArrayList<EsempioProdotto> esempioProdotto) {

        this.mEsempioProdotto = esempioProdotto;
    }

    @Override
    public EsempioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.esempio_prodotto, parent, false);
        EsempioViewHolder esempioViewHolder = new EsempioViewHolder(view, mListener);
        return esempioViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EsempioViewHolder holder, int position) {

        EsempioProdotto currentItem = mEsempioProdotto.get(position);
        holder.mImageViewE.setImageResource(currentItem.getImageElimina());
        holder.mImageViewM.setImageResource(currentItem.getImageModifica());
        holder.mTextViewProdotto.setText(currentItem.getM_textProdotto());
        holder.mTextViewQuantita.setText(currentItem.getM_textQuantita());
    }

    @Override
    public int getItemCount() {
        return mEsempioProdotto.size();
    }
}
