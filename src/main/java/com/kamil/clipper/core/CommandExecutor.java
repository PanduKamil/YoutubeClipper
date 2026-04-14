package com.kamil.clipper.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    
  public List<String> execute(List<String> command) throws Exception {
    List<String> output = new ArrayList<>();
    ProcessBuilder pb = new ProcessBuilder(command);
    pb.redirectErrorStream(true);
    
    Process process = pb.start();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
        String line;
        while ((line = reader.readLine()) != null) {
            // CUMA ambil baris yang depannya "https://" (biar gak kena warning/log nyampah)
            if (line.startsWith("https://")) {
                output.add(line);
            }
            System.out.println("[TERMINAL]: " + line);
        }
    }
    
    int exitCode = process.waitFor();
    if (exitCode != 0) throw new RuntimeException("Gagal! Kode: " + exitCode);
    
    return output;
    }
}