/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author salva
 */
public class JSONReader {
    
    public static final String JSON_FILE = "libri.json";

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException {

        Libro libri[];
        InputStream input = new FileInputStream(JSON_FILE);
        JsonReader jsonReader = Json.createReader(input);
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        JsonObject innerJsonObject = jsonObject.getJsonObject("Libreria");
        JsonArray jsonArray = innerJsonObject.getJsonArray("Libri");
        libri = new Libro[jsonArray.size()];
        int index = 0;
        for (JsonValue element : jsonArray) {
            Libro libro = new Libro();
            libro.setGenere(element.asJsonObject().getString("genere"));
            libro.setTitolo(element.asJsonObject().getString("titolo"));
            libro.setAutore(element.asJsonObject().getString("autore"));
            libro.setPrezzo((float) element.asJsonObject().getJsonNumber("prezzo").doubleValue());
            libri[index++] = libro;
        }
        for (index = 0; index < libri.length; index++) {
            System.out.println(libri[index]);
        }
    }
}
