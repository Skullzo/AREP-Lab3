package edu.escuelaing.arep.app;
import java.io.IOException;
public class App {
    public static void main( String[] args ) {
        HttpServer server = new HttpServer();
        MySpark.post("/hola","Buenos dias.");
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}