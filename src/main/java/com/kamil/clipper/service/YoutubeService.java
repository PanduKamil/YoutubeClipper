package com.kamil.clipper.service;

import com.kamil.clipper.core.CommandExecutor;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class YoutubeService {
    
    private final CommandExecutor executor;

    public List<String> getVideoAndAudioUrls(String youtubeUrl) throws Exception {
    String userHome = System.getProperty("user.home");
    String ytDlpPath = userHome + "/.local/bin/yt-dlp";

        List<String> command = List.of(
            ytDlpPath, 
            "--no-check-certificate",
            "-g",
            // Tambahkan format agar pasti dapat MP4 & M4A agar FFmpeg tidak kerja keras re-encoding
            "-f", "bestvideo[ext=mp4]+bestaudio[ext=m4a]/best[ext=mp4]/best",
            youtubeUrl
        );
        
        return executor.execute(command);
    }
}
