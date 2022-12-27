import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerGame {
    public static void start() {
        int port = 8081;
        boolean gameStart = false;
        String city = "";
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    if (gameStart && !city.isEmpty()) {
                        out.println(city);
                        String parsedCity = city.toLowerCase();
                        char endCity = (parsedCity.endsWith("ь")) ? parsedCity.charAt(parsedCity.length() - 2) : parsedCity.charAt(parsedCity.length() - 1);
                        String responseCity = in.readLine();
                        if (!responseCity.isEmpty() && endCity == responseCity.toLowerCase().charAt(0)) {
                            city = responseCity;
                            out.println("OK");
                        } else {
                            out.println("NOT OK");
                        }
                        out.println("OK");
                    } else {
                        out.println("???");
                        gameStart = true;
                        city = in.readLine();
                        out.println("OK");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
