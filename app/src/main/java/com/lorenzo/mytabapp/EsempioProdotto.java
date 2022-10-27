package com.lorenzo.mytabapp;

public class EsempioProdotto {

    private int imageElimina;
    private int imageModifica;
    private String m_textProdotto, m_textQuantita;

    public EsempioProdotto(int imageElimina, int imageModifica, String m_textProdotto, String m_textQuantita) {
        this.imageElimina = imageElimina;
        this.imageModifica = imageModifica;
        this.m_textProdotto = m_textProdotto;
        this.m_textQuantita = m_textQuantita;
    }

    public EsempioProdotto( String m_textProdotto, String m_textQuantita) {
        this.m_textProdotto = m_textProdotto;
        this.m_textQuantita = m_textQuantita;
    }


    public String getM_textProdotto() {
        return m_textProdotto;
    }

    public void setM_textProdotto(String m_textProdotto) {
        this.m_textProdotto = m_textProdotto;
    }

    public String getM_textQuantita() {
        return m_textQuantita;
    }

    public void setM_textQuantita(String m_textQuantita) {
        this.m_textQuantita = m_textQuantita;
    }

    public int getImageElimina() {
        return imageElimina;
    }

    public void setImageElimina(int imageElimina) {
        this.imageElimina = imageElimina;
    }

    public int getImageModifica() {
        return imageModifica;
    }

    public void setImageModifica(int imageModifica) {
        this.imageModifica = imageModifica;
    }


}