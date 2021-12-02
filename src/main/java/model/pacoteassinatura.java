package model;

public class pacoteassinatura {
    private int id;
    private String tipo;
    private double pre�o;

    public pacoteassinatura() {
        this.id = 0;
        this.tipo = "";
        this.pre�o = 0;
    }

    public pacoteassinatura(int id, String tipo, double pre�o) {
        this.id = id;
        this.tipo = tipo;
        this.pre�o = pre�o;
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

    public double getPre�o() {
        return pre�o;
    }

    public void setPre�o(double pre�o) {
        this.pre�o = pre�o;
    }

    @Override
    public String toString() {
        return "PacoteAssinatura [id=" + id + ", tipo=" + tipo + ", pre�o=" + pre�o + "]";
    }
}