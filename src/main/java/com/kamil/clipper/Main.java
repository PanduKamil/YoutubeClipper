package com.kamil.clipper;

import com.kamil.clipper.core.CommandExecutor;
import com.kamil.clipper.service.YoutubeService;
import com.kamil.clipper.service.ClipperService;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor();
        YoutubeService ytService = new YoutubeService(executor);
        ClipperService clipper = new ClipperService(executor);

        String url = "https://youtu.be/It_2_5Jf78U?si=wPlryygvt2TdcVla";

        try {
            // 1. Ambil link stream SEKALI saja
            List<String> streams = ytService.getVideoAndAudioUrls(url);
            String vUrl = streams.get(0);
            String aUrl = streams.get(1);

            // 2. Daftar potongan (Start, Durasi, Nama File)
            String[][] clips = {
                {"00:00:10", "00:00:10", "potongan_awal.mp4"},
                {"00:01:00", "00:00:15", "potongan_tengah.mp4"},
                {"00:02:30", "00:00:10", "potongan_akhir.mp4"}
            };

            // 3. Looping potong-potong!
            for (String[] clip : clips) {
                System.out.println("Gunting video bagian: " + clip[2]);
                clipper.cutVideo(vUrl, aUrl, clip[0], clip[1], clip[2]);
            }

            System.out.println("Berhasil semua, bre! Cek folder projectmu.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}