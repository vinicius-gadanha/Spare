package model;

public class pacoteassinatura {
    private int id;
    private String tipo;
    private double preco;

    public pacoteassinatura() {
        this.id = 0;
        this.tipo = "";
        this.preco = 0;
    }

    public pacoteassinatura(int id, String tipo, double preco) {
        this.id = id;
        this.tipo = tipo;
        this.preco = preco;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "PacoteAssinatura [id=" + id + ", tipo=" + tipo + ", preco=" + preco + "]";
    }
}