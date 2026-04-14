package com.kamil.clipper.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    
    public List<String> execute(List<String> command) throws Exception {
        List<String> output = new ArrayList<>();
        
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true); // Gabungkan error stream ke input stream biar gampang dibaca
        
        Process process = pb.start();
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.add(line);
                // Kita print juga ke console biar kita bisa liat prosesnya (Logging sederhana)
                System.out.println("[TERMINAL]: " + line);
            }
        }
        
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Command gagal dengan exit code: " + exitCode);
        }
        
        return output;
    }
}