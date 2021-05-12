package com.company.data;

public class Nodo {

    //enumeraciones para mayor legibilidad
    public enum Type{
        NORMAL, STREAM, CONVECTION;
        public static Type fromTipo(int id){
            switch (id){
                case 0:
                    return NORMAL;
                case 1:
                    return STREAM;
                case 2:
                    return CONVECTION; //Transferencia de calor
            }
            return NORMAL;
        }
    }

    private int id;
    private double x;
    private Type tipo;

    //constructor
    public Nodo() {}

    //Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type type) {
        this.tipo = tipo;
    }
}
