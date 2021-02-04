package edu.escuelaing.arep.app;
import java.io.IOException;
public class App {
    public static void main( String[] args ) {
        HttpServer server = new HttpServer();
        MySpark.post("/acercade","La Registraduría Nacional del Estado Civil de Colombia es una institución descentralizada del Estado de ese país encargada del registro civil nacional, así como de la convocatoria y organización electoral bajo el mandato y supervisión del Consejo Nacional Electoral. El actual registrador nacional es Alexander Vega. Los orígenes de la Registraduría Nacional como entidad llamada a responder por la elaboración de la cédula de ciudadanía, puede remontarse al año 1934, cuando en la Policía Nacional se designó a la Sección Electoral para encargarse de las funciones relacionadas con la cedulación. Más tarde, hacia 1935, en atención a que el volumen de trabajo se hacía cada vez mayor, se dispuso que en el Ministerio de Gobierno funcionara la Oficina Nacional de Identificación Electoral, con tarjetas dactiloscópicas decadactilares, negativos fotográficos de los ciudadanos, archivos alfabético, numérico y dactiloscópico.");
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}