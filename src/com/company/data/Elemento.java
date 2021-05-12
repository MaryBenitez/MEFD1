package com.company.data;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class Elemento {

    //variables
    private Nodo nodo1;
    private Nodo nodo2;

    private double K;
    private double q;

    private RealMatrix realMatrix1;
    private RealMatrix realMatrix2;

    public Elemento() {
    }

    public Nodo getNodo1() {
        return nodo1;
    }

    public void setNodo1(Nodo nodo1) {
        this.nodo1 = nodo1;
    }

    public Nodo getNodo2() {
        return nodo2;
    }

    public void setNodo2(Nodo nodo2) {
        this.nodo2 = nodo2;
    }

    public double getK() {
        return K;
    }

    public void setK(double k) {
        K = k;
    }

    public double getQ() {
        return q;
    }

    public void setQ(double q) {
        this.q = q;
    }

    public RealMatrix getRealMatrix1() {
        return realMatrix1;
    }

    public void setRealMatrix1(RealMatrix realMatrix1) {
        this.realMatrix1 = realMatrix1;
    }

    public RealMatrix getRealMatrix2() {
        return realMatrix2;
    }

    public void setRealMatrix2(RealMatrix realMatrix2) {
        this.realMatrix2 = realMatrix2;
    }
    public double getLog(){
        double n1 = nodo1.getX();
        double n2 = nodo2.getX();

        return Math.abs(n2 - n1);
    }

    //-----Funciones-----//

    public void calculateMatrix1(double value){
        double log = getLog();

        double c = (K * q) / log;

        RealMatrix newDataMatriz1 = MatrixUtils.createRealMatrix(
                new double[][]{ {c, -c}, {-c, c} }
        );

        RealMatrix newDataMatriz2 = MatrixUtils.createRealMatrix(
                new double[][]{ {0, 0}, {0, 0} }
        );

        //Condiciones del tipo
        if (nodo1.getTipo() == Nodo.Type.CONVECTION){
            newDataMatriz2.setEntry(0, 0, 1);
            double v = value * q;
            newDataMatriz2 = newDataMatriz2.scalarMultiply(v);
        } else if (nodo2.getTipo() == Nodo.Type.CONVECTION){
            newDataMatriz2.setEntry(1, 1, 1);
            double aS = value * q;
            newDataMatriz2 = newDataMatriz2.scalarMultiply(aS);
        }

        realMatrix1 = newDataMatriz1.add(newDataMatriz2);
    }

    public void calculateMatrix12(Datos dato){
        Nodo.Type n1 = nodo1.getTipo();
        Nodo.Type n2 = nodo2.getTipo();

        //Si los nodos son normales
        if ( (n1 == Nodo.Type.NORMAL) && (n2 == Nodo.Type.NORMAL) ){
            realMatrix2 = MatrixUtils.createRealMatrix(new double[][]{ {0}, {0} });
            return;
        }

        //parte de la transferencia de calor de la matriz
        RealMatrix convectionMatriz = MatrixUtils.createRealMatrix(
                new double[][]{ {0}, {0} }
        );

        //Stream de la matriz
        RealMatrix streamMatriz = MatrixUtils.createRealMatrix(
                new double[][]{ {0}, {0} }
        );

        if (n1 == Nodo.Type.CONVECTION){
            convectionMatriz.setEntry(0, 0, 1);
        } else if (n1 == Nodo.Type.STREAM){
            streamMatriz.setEntry(0, 0, 1);
        }

        if (n2 == Nodo.Type.CONVECTION){
            convectionMatriz.setEntry(1, 0, 1);
        } else if (n2 == Nodo.Type.STREAM){
            streamMatriz.setEntry(1, 0, 1);
        }

        double T = dato.getT();
        double a = dato.getValue();
        double datoQ = dato.getQ();

        double convFactor = q * T * a;
        double streamFactor = datoQ * q;

        convectionMatriz = convectionMatriz.scalarMultiply(-convFactor);
        streamMatriz = streamMatriz.scalarMultiply(streamFactor);

        realMatrix2 = convectionMatriz.add(streamMatriz);
    }

}
