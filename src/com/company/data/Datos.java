package com.company.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Datos {

    //variables
    private int x;
    private int y;
    private double value;
    private double T;
    private double q;
    private List<Elemento> elementos;

    public Datos(String file){
        try {
            setAttributesFromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Getter & Setter

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getT() {
        return T;
    }

    public void setT(double t) {
        T = t;
    }

    public double getQ() {
        return q;
    }

    public void setQ(double q) {
        this.q = q;
    }

    public List<Elemento> getElementos() {
        return elementos;
    }

    public void setElementos(List<Elemento> elementos) {
        this.elementos = elementos;
    }

    private void setAttributesFromFile(String file) throws IOException {
        StringReader stringReader = new StringReader(file);
        BufferedReader reader = new BufferedReader(stringReader);

        Map<Integer, Nodo> nodesMap = new HashMap<>();
        elementos = new ArrayList<>();

        String linea;

        //Lee el archivo
        while ((linea = reader.readLine()) != null){
            String tokens[] = linea.split("\\s+");

            switch (tokens[0]){
                case "-":
                    break;
                //Nodos
                case "n":
                    Nodo nodo = new Nodo();

                    int id = Integer.parseInt(tokens[1]);
                    nodo.setId(id);
                    nodo.setTipo(Nodo.Type.NORMAL);

                    double x = Double.parseDouble(tokens[2]);
                    nodo.setX(x);

                    nodesMap.put(id, nodo);
                    break;
                //Tipo
                case "t":
                    Nodo.Type type = Nodo.Type.fromTipo(Integer.parseInt(tokens[2]));
                    nodesMap.get(Integer.parseInt(tokens[1])).setTipo(type);
                    break;
                //Elementos
                case "e":
                    Elemento e = new Elemento();
                    int nodoId;

                    nodoId = Integer.parseInt(tokens[1]);
                    e.setNodo1(nodesMap.get(nodoId));

                    nodoId = Integer.parseInt(tokens[2]);
                    e.setNodo2(nodesMap.get(nodoId));

                    elementos.add(e);
                    break;

                case "p":
                    int eId1 = Integer.parseInt(tokens[1]);
                    int eId2 = Integer.parseInt(tokens[2]);
                    double q = Double.parseDouble(tokens[3]);
                    double K = Double.parseDouble(tokens[4]);

                    for (int i = eId1; i <= eId2; i++){
                        Elemento element = elementos.get(i);
                        element.setQ(q);
                        element.setK(K);
                    }

                    break;

                case "a":
                    value = Double.parseDouble(tokens[1]);
                    break;

                case "T":
                    T = Double.parseDouble(tokens[1]);
                    break;

                case "q":
                    q = Double.parseDouble(tokens[1]);
                    break;
            }
        }

        y = nodesMap.size();
        x = elementos.size();
    }
}
