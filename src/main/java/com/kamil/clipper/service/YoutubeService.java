package com.kamil.clipper.service;

import com.kamil.clipper.core.CommandExecutor;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class YoutubeService {
    
    private final CommandExecutor executor;

    public List<String> getVideoAndAudioUrls(String youtubeUrl) throws Exception {
    String ytDlpPath = System.getProperty("user.home") + "/.local/bin/yt-dlp";
    return executor.execute(List.of(
        ytDlpPath, 
        "-g", 
        "-f", "bestvideo[ext=mp4]+bestaudio[ext=m4a]", 
        youtubeUrl
    ));
}
}
