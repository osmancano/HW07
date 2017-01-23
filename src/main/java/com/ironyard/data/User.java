package com.ironyard.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by osmanidris on 1/18/17.
 */
public class User {
    private String username;
    private String password;
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean isAuthenticated() throws FileNotFoundException {
        boolean status = false;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("files/userCredentials.txt").getFile());
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String [] details = line.split(":");
            String storedUsername = details[0];
            String storedPassword = details[1];
            if(storedUsername.equals(username)&& storedPassword.equals(password)){
                status = true;
                break;
            }
        }
        return status;
    }

    public void sighUp() throws IOException {
        File file = new File("userCredentials.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(username+":"+password+"\n");
        out.close();
    }
}
