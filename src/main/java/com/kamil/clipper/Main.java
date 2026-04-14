package com.kamil.clipper;

import com.kamil.clipper.core.CommandExecutor;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor();
        try {
            System.out.println("Mencoba memanggil yt-dlp...");
            // Kita panggil perintah yt-dlp --version dalam bentuk List
            executor.execute(List.of("yt-dlp", "--version"));
        } catch (Exception e) {
            System.err.println("Waduh error bre: " + e.getMessage());
        }
    }
}