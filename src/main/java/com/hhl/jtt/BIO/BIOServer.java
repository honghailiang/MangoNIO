package com.hhl.jtt.BIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by DELL on 2017/3/29.
 */
public class BIOServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(10000);
            System.out.println("server start...");
            while (true) {
                Socket s = ss.accept();
                System.out.println("accept comming...");
                new LogicThread(s);//开一个线程来处理请求，这里面调用InputStream.read()读取请求信息
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class LogicThread {
        public LogicThread(Socket s) {
            try {
                while(true){
                    InputStream in = s.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    BufferedReader buffer = new BufferedReader(reader);
                    String line = buffer.readLine();//阻塞
                    System.out.println(line);
                    OutputStream outputStream = s.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    bufferedWriter.write("I'm  " + line );
                    bufferedWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
