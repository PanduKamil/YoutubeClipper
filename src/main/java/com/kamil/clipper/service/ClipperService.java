package com.kamil.clipper.service;

import com.kamil.clipper.core.CommandExecutor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ClipperService {

    private final CommandExecutor executor;

    // 1. Deklarasiin dulu variabelnya di sini!
    String ua = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36";

    // 2. Baru deh dipake di bawah
    public void cutVideo(String videoUrl, String audioUrl, String start, String duration, String outputName) throws Exception {
        List<String> command = new ArrayList<>();
    command.add("ffmpeg");
    command.add("-hide_banner");
    command.add("-loglevel"); command.add("error");
    
    // Pakai argumen terpisah biar ProcessBuilder nggak bingung sama spasi
    command.add("-user_agent");
    command.add("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
    
    command.add("-protocol_whitelist");
    command.add("file,http,https,tcp,tls,crypto");
    
    command.add("-ss"); command.add(start);
    command.add("-i"); command.add(videoUrl);
    
    command.add("-ss"); command.add(start);
    command.add("-i"); command.add(audioUrl);
    
    command.add("-t"); command.add(duration);
    command.add("-map"); command.add("0:v:0");
    command.add("-map"); command.add("1:a:0");
    
    // Solusi patah-patah tadi:
    command.add("-c:v"); command.add("libx264");
    command.add("-preset"); command.add("ultrafast");
    command.add("-crf"); command.add("23");
    command.add("-c:a"); command.add("aac");
    command.add("-avoid_negative_ts"); command.add("make_zero");
    
    command.add("-y");
    command.add(outputName);

    System.out.println(">>> Processing: " + outputName);
    executor.execute(command);
    }
    
    
    
}
