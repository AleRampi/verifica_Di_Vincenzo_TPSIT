package com.divincenzo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server{

	public static void main(String args[]) throws Exception	{

		ServerSocket serverSocket = new ServerSocket(8888);
		Socket socket = serverSocket.accept();

		System.out.println("Connessione con il client effettuata");

        BufferedWriter outputBuffer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);

        String val1 = "";
        String val2 = "";
        String operatore = "";
        String scelta = ""; //se si vuole eseguire una nuova operazione
        int risultato = 0;

        outputBuffer.write("Connessione effettuata" + '\n');

            do{
                outputBuffer.write("Inserisci il primo numero" + '\n');
                outputBuffer.flush();

                val1 = inputBuffer.readLine();

                outputBuffer.write("Inserisci il secondo numero" + '\n');
                outputBuffer.flush();
                val2 = inputBuffer.readLine();

                outputBuffer.write("Scegli l'operatore da eseguire" + '\n');
                outputBuffer.flush();
                operatore = inputBuffer.readLine();

                switch(operatore){
                    case "+":
                        risultato = Integer.parseInt(val1) + Integer.parseInt(val2);
                    break;

                    case "-":
                        risultato = Integer.parseInt(val1) - Integer.parseInt(val2);
                    break;

                    case "*":
                        risultato = Integer.parseInt(val1) * Integer.parseInt(val2);
                    break;

                    case "/":
                        risultato = Integer.parseInt(val1) / Integer.parseInt(val2);
                    break;
                }

                outputBuffer.write(String.valueOf(risultato) + '\n');
                outputBuffer.write("Vuoi eseguire una nuova operazione? (y/n)" + '\n');
                outputBuffer.flush();

                scelta = inputBuffer.readLine();

            } while(scelta.charAt(0) == 'y');

            serverSocket.close();
            socket.close();
            inputBuffer.close();
            outputBuffer.close();
            scanner.close();
	}
}