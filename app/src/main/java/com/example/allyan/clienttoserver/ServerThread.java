package com.example.allyan.clienttoserver;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Allyan on 05/05/2017.
 */

class ServerThread extends Thread {

    private String name;
    private int serverPort = 6666;
    private String address = "192.168.56.1";
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;

    public ServerThread(String s) {
        this.name = s;
    }

    @Override
    public void run() {

        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
             // создаем сокет используя IP-адрес и порт сервера.
            socket = new Socket(ipAddress, serverPort);

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.

            in = new DataInputStream(sin);

            out = new DataOutputStream(sout);

            // Создаем поток для чтения с клавиатуры.
            String line = null;

            out.writeUTF(name); // отсылаем введенную строку текста серверу.
            out.flush(); // заставляем поток закончить передачу данных.
            line = in.readUTF(); // ждем пока сервер отошлет строку текста.
            System.out.println("It sent me this : " + line);
//            Toast.makeText()
        } catch (Exception x) {
            x.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
