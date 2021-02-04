package edu.escuelaing.arep.app;
import java.io.IOException;
public class App {
    public static void main( String[] args ) {
        HttpServer server = new HttpServer();
        MySpark.post("/acercade","La Registraduria Nacional del Estado Civil de Colombia es una institucion descentralizada del Estado de ese pais encargada del registro civil nacional, asi como de la convocatoria y organizacion electoral bajo el mandato y supervision del Consejo Nacional Electoral. El actual registrador nacional es Alexander Vega. Los origenes de la Registraduria Nacional como entidad llamada a responder por la elaboracion de la cedula de ciudadania, puede remontarse a 1934, cuando en la Policia Nacional se designo a la Seccion Electoral para encargarse de las funciones relacionadas con la cedulacion. Mas tarde, hacia 1935, en atencion a que el volumen de trabajo se hacia cada vez mayor, se dispuso que en el Ministerio de Gobierno funcionara la Oficina Nacional de Identificacion Electoral, con tarjetas dactiloscopicas decadactilares, negativos fotograficos de los ciudadanos, archivos alfabetico, numerico y dactiloscopico. Por decreto de 1935, se asignaron a la Contraloria General de la Republica las estadisticas electorales de cada municipio del pais. Se separo la oficina de Identificacion de la Policia Nacional y se fijaron las siguientes bases para la organizacion que demandaba el gran volumen de trabajo:");
        MySpark.post("/acercade","La Registraduria Naci");
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}