package model;

public class pacoteassinatura {
    private int id;
    private String tipo;
    private double preço;

    public pacoteassinatura() {
        this.id = 0;
        this.tipo = "";
        this.preço = 0;
    }

    public pacoteassinatura(int id, String tipo, double preço) {
        this.id = id;
        this.tipo = tipo;
        this.preço = preço;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreço() {
        return preço;
    }

    public void setPreço(double preço) {
        this.preço = preço;
    }

    @Override
    public String toString() {
        return "PacoteAssinatura [id=" + id + ", tipo=" + tipo + ", preço=" + preço + "]";
    }
}