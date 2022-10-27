package com.lorenzo.mytabapp;

public class PrimeEsempioProdotto {

    private int imageCompra;
    private String p_textProdotto;
    private String p_textQuantita;

    public PrimeEsempioProdotto(int imageCompra, String p_textProdotto, String p_textQuantita) {
        this.imageCompra = imageCompra;
        this.p_textProdotto = p_textProdotto;
        this.p_textQuantita = p_textQuantita;
    }

    public PrimeEsempioProdotto(String p_textProdotto, String p_textQuantita) {
        this.p_textProdotto = p_textProdotto;
        this.p_textQuantita = p_textQuantita;
    }

    public int getImageCompra() {
        return imageCompra;
    }

    public void setImageCompra(int imageCompra) {
        this.imageCompra = imageCompra;
    }

    public String getP_textProdotto() {
        return p_textProdotto;
    }

    public void setP_textProdotto(String p_textProdotto) {
        this.p_textProdotto = p_textProdotto;
    }

    public String getP_textQuantita() {
        return p_textQuantita;
    }

    public void setP_textQuantita(String p_textQuantita) {
        this.p_textQuantita = p_textQuantita;
    }
}
