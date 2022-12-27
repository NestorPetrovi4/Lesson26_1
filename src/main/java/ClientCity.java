import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientCity {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8081;
        Scanner scanner = new Scanner(System.in);
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String resp = in.readLine();
            if (resp.startsWith("???")) {
                System.out.println("Начало игры! Введите первый город");
                out.println(scanner.nextLine());
                System.out.println(in.readLine());
            } else {
                System.out.println("Текущий город " + resp + "\n введите город начинающийся на последнюю букву текущего, если город заканчивается на мягкий знак, то букву которая установлена перед ним");
                out.println(scanner.nextLine());
                System.out.println(in.readLine());
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Не удалось выполнить подключение к хосту");
            e.printStackTrace();
        }
    }
}