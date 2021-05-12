package com.company;

import com.company.data.Datos;
import com.company.mesh.Mesh;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        String file = readFile(Main.class.getResource("/malla.txt"));
        Datos datos = new Datos(file);
        Mesh mesh = new Mesh(datos);
        mesh.mostrar();
    }

    //funcion de leer el archivo
    public static String readFile(URL url){
        StringBuilder builder = new StringBuilder();

        try {
            Path path = Paths.get(url.toURI());
            Files.lines(path).forEachOrdered(l -> builder.append(l).append("\n"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

}
