package com.company.mesh;

import com.company.data.Datos;
import com.company.data.Elemento;
import org.apache.commons.math3.linear.*;

import java.util.List;

public class Mesh {

    private Datos datos;
    private RealMatrix realMatrix1;
    private RealMatrix realMatrix2;

    //Constructor
    public Mesh(Datos datos){
        this.datos = datos;
    }

    public void mostrar(){

        DecompositionSolver solver = new LUDecomposition(realMatrix1).getSolver();
        RealVector vector = realMatrix2.scalarMultiply(-1).getColumnVector(0);
        RealVector resp = solver.solve(vector);

        System.out.println("\nRespuesta:");
        result("t", resp);
    }


    private void result(String name, RealVector v){
        int length = v.getDimension();

        System.out.print(name + " = [");
        for (int i = 0; i < length; i++){
            System.out.printf("%10.3f", v.getEntry(i));
        }
        System.out.println("\t]");
    }

}
