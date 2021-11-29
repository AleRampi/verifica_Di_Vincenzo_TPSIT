package com.divincenzo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) throws Exception{
  
        Socket socket = new Socket("localhost", 8888);
        BufferedWriter outputBuffer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);
        String scelta = "";
  
            System.out.println(inputBuffer.readLine());

            do{
  
                System.out.println(inputBuffer.readLine());

                outputBuffer.write(scanner.nextLine() + '\n');
                outputBuffer.flush();

                for(int i = 0; i <= 1; i++){
                    System.out.println(inputBuffer.readLine());
                    outputBuffer.write(scanner.nextLine() + '\n');
                    outputBuffer.flush();
                }

                System.out.println("Risultato: " + inputBuffer.readLine());
                System.out.println(inputBuffer.readLine());
                
                scelta = scanner.nextLine();

                while(scelta.charAt(0) != 'y' && scelta.charAt(0) != 'n'){
                    System.out.println("scelta non valida, riprova. (digita: y/n)");
                    scelta = scanner.nextLine();
                }

                outputBuffer.write(scelta + '\n');
                outputBuffer.flush();

            } while(scelta.charAt(0) == 'y');

            socket.close();
            scanner.close();
    }
}