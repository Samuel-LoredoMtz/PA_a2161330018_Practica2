package Libreria;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Archivotxt {

    public static List<String> leerArchivo(String nombreArchivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        BufferedReader lector = null;
        try {
            lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
            }
        } finally {
            if (lector != null) {
                lector.close();
            }
        }
        return lineas;
    }

    public static void escribirArchivo(String nombreArchivo, List<String> lineas) throws IOException {
        BufferedWriter escritor = null;
        try {
            escritor = new BufferedWriter(new FileWriter(nombreArchivo));
            for (String linea : lineas) {
                escritor.write(linea);
                escritor.newLine();
            }
        } finally {
            if (escritor != null) {
                escritor.close();
            }
        }
    }
}
