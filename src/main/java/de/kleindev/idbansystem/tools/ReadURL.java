package de.kleindev.idbansystem.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ReadURL {
    public static List<String> listLines(String URL){
        URL url = null;
        List<String> lines = new ArrayList<String>();
        try {
            url = new URL(URL);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                lines.add(inputLine);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static boolean contains(String URL, String Content){
        URL url = null;
        List<String> lines = new ArrayList<String>();
        try {
            url = new URL(URL);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                lines.add(inputLine);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.contains(Content);
    }

    public static boolean equals(String URL, String Content){
        URL url = null;
        List<String> lines = new ArrayList<String>();
        try {
            url = new URL(URL);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                lines.add(inputLine);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.equals(Content);
    }


    public static boolean equalsIgnoreCase(String URL, String Content){
        URL url = null;
        List<String> lines = new ArrayList<String>();
        try {
            url = new URL(URL);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                lines.add(inputLine);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.toString().equalsIgnoreCase(Content);
    }

    public static boolean getBoolean(String URL){
        URL url = null;
        boolean check = false;
        List<String> lines = new ArrayList<String>();
        try {
            url = new URL(URL);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                check = Boolean.getBoolean(inputLine);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return check;
    }
}
