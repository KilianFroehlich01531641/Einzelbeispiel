package com.example.einzelarbeit_01531641;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class UniThread extends Thread{
    String mtnr;
    String answer = "yolo";
    UniThread (String mtnr){
        this.mtnr = mtnr;
    }

    @Override
    public void run() {
        int i = 0;

        try {
            String sentence = this.mtnr;
            String modifiedSentence;
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            i = 1;
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);
            i = 2;
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            i = 3;
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            i = 4;
            outToServer.writeBytes(sentence + '\n');
            i = 5;
            answer = inFromServer.readLine();
            i = 6;
            clientSocket.close();
            i = 7;
        } catch (Exception e){
            answer = "crashed " + i;
        }
    }

    public String getAnswer() {
        return answer;
    }
}
