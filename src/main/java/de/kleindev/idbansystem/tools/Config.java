package de.kleindev.idbansystem.tools;

import de.kleindev.idbansystem.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Config {
    static File cfile;
    static FileConfiguration config;

    public static void create(String Folder, String FileName) {
        File df = new File(Folder);
        cfile = new File(df, File.separator + FileName);
        if (!df.exists()) df.mkdir();
        if (!cfile.exists()) {
            try {
                cfile.createNewFile();
            } catch(Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + cfile.getName() + "!");
            }
        }
        config = YamlConfiguration.loadConfiguration(cfile);
    }

    public static FileConfiguration load(String FileName) {
        File df = new File(Main.plugin.getDataFolder().getPath());
        cfile = new File(df, File.separator + FileName);
        config = YamlConfiguration.loadConfiguration(cfile);
        return config;
    }
    public static void save(String Folder, String FileName) {
        File df = new File(Folder);
        cfile = new File(df, File.separator + FileName);
        try {
            config.save(cfile);
        } catch(Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + cfile.getName() + "!");
        }
    }
    public static boolean exist(String Folder, String FileName){
        File df = new File(Folder);
        cfile = new File(df, File.separator + FileName);
        return (cfile.exists());
    }

    public static void addList(String Folder, String FileName, String List, String Object){
        File df = new File(Folder);
        cfile = new File(df, File.separator + FileName);
        config = YamlConfiguration.loadConfiguration(cfile);
        List<String> Liste = config.getStringList(List);
        if(Liste == null) Liste = new ArrayList<String>();
        Liste.add(Object);
        config.set(List, Object);
    }

    public static void removeList(String Folder, String FileName, String List, String Object){
        File df = new File(Folder);
        cfile = new File(df, File.separator + FileName);
        config = YamlConfiguration.loadConfiguration(cfile);
        List<String> Liste = config.getStringList(List);
        if(Liste == null) Liste = new ArrayList<String>();
        Liste.remove(Object);
        config.set(List, Object);
    }

    public static void copy(String File){
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        try {
            String pluginPath = Main.plugin.getDataFolder() + java.io.File.separator + File;
            bis = new BufferedInputStream(Main.plugin.getResource(File));
            fos = new FileOutputStream(pluginPath);

            int count = -1;
            int read;
            while ((read = bis.read()) != -1) {
                fos.write((byte) read);
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
            }
        }

        if (bis != null) {
            try {
                bis.close();
            } catch (IOException e) {
            }
        }
    }
}
