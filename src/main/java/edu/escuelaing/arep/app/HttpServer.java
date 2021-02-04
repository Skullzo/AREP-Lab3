package edu.escuelaing.arep.app;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.io.File;
import java.util.ArrayList;

public class HttpServer {
    private String root = "src/main/resources";
    private PrintWriter out = null;
    private DBConnection connection = null;

    public void start() throws IOException {
        int port = getPort();
        connection = new DBConnection();
        while(true) {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                System.err.println("Could not listen on port: 35000.");
                System.exit(1);
            }
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            processRequest(clientSocket);

            out.close();
            clientSocket.close();
            serverSocket.close();
        }
    }

    private void processRequest(Socket clientSocket) throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, file = "";
        while ((inputLine = in.readLine()) != null) {
            //System.out.println("Recibí: " + inputLine);
            if (inputLine.contains("GET")) {
                file = inputLine.split(" ")[1];
                if (file.startsWith("/Apps")) {
                    String appuri = file.substring(5);
                    out.println(invoke(appuri));
                } else {
                    if (file.equals("/")) {
                        file = "/index.html";
                    }
                    getResource(file,clientSocket);
                }
            }
            if (!in.ready()) {
                break;
            }
        }
        in.close();
    }

    private void getResource(String file,Socket clientSocket) throws IOException{
        String outputLine;
        int type = getType(file);
        if (type == 0) {
            outputLine = getFile(file,"html");
            out.println(outputLine);
        }else if(type == 1){
            outputLine = getFile(file,"json");
            out.println(outputLine);
        }else if(type == 2) {
            getImage(file, clientSocket.getOutputStream());
        }
    }

    public String invoke(String type){
        String outputLine = getHeader("html"),file =  MySpark.get(type);
        if(type.equals("/informationDB")){
            file = "";
            ArrayList<String[]> information = connection.getInformation();
            for(String[] temp : information){
                file+=" Nombres y Apellidos: " + temp[0] + "     -     " + " Direccion: " + temp[1];
            }
            return outputLine + file;
        }
        if(file != null){
            return outputLine + file;
        }
        return errorResponse(type);
    }

    public String getHeader(String type){
        return "HTTP/1.1 200 OK\r\n" + "Content-Type: text/"+type+"\r\n" + "\r\n";
    }

    public String getFile(String ruta,String type){
        String outputLine = getHeader(type),path = root + ruta;
        File file = new File(path);
        if(file.exists()){
            String content;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                while ((content = br.readLine()) != null) {
                    outputLine += content;
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            outputLine = errorResponse(file.getName());
        }
        return outputLine;
    }

    private String errorResponse(String file){
        String outputLine = "HTTP/1.1 404 Not Found \r\nContent-Type: text/html \r\n\r\n <!DOCTYPE html> <html>"
                + "<head><title>404</title></head>" + "<body> <h1>404 Not Found " + file
                + "</h1></body></html>";
        return outputLine;
    }

    public void getImage(String type, OutputStream outClient){
        String path = root + type;
        File file = new File(path);
        if (file.exists()) {
            try {
                BufferedImage image = ImageIO.read(file);
                ByteArrayOutputStream ArrBytes = new ByteArrayOutputStream();
                DataOutputStream writeimg = new DataOutputStream(outClient);
                ImageIO.write(image, "PNG", ArrBytes);
                writeimg.writeBytes("HTTP/1.1 200 OK \r\n" + "Content-Type: image/png \r\n" + "\r\n");
                writeimg.write(ArrBytes.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            out.println(errorResponse(file.getName()));
        }
    }

    public int getType(String type){
        if(type.contains("html")){
            return 0;
        }else if(type.contains("js")){
            return 1;
        }else{
            return 2;
        }
    }

    /**
     * Calcula el puerto que se va a utilizar
     * @return int puerto
     */
    public int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000; // returns default port if heroku-port isn't set(i.e. on localhost)
    }
}
