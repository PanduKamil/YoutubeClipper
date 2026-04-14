package com.kamil.clipper.service;

import com.kamil.clipper.core.CommandExecutor;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class ClipperService {

    private final CommandExecutor executor;

        public void cutVideo(String videoUrl, String audioUrl, String start, String duration, String outputName) throws Exception {
    // User Agent harus sama dengan yang biasa dipakai yt-dlp
    String ua = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36";

    List<String> command = List.of(
        "ffmpeg",
        "-hide_banner", "-loglevel", "error", // Biar log gak nyampah
        "-ss", start,
        "-protocol_whitelist", "file,http,https,tcp,tls,crypto",
        "-user_agent", ua, // KUNCINYA DI SINI BRE!
        "-i", videoUrl,
        "-user_agent", ua, // Header buat audio juga
        "-i", audioUrl,
        "-t", duration,
        "-map", "0:v:0",
        "-map", "1:a:0",
        "-c:v", "copy", // Stream copy biar cepet (nggak re-encode)
        "-c:a", "aac",  // Audio di-convert dikit biar sinkron
        "-y",
        outputName
    );

        System.out.println(">>> Processing: " + outputName);
        executor.execute(command);
    }
}
