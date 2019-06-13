package com.aveco.gstreamer;

import java.awt.FlowLayout;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.elements.PlayBin;


public class Main {

    private PlayBin playBin;


    public Main() {

        Gst.init();
        playBin = new PlayBin("Test Gstreamer");
        playBin.setURI(URI.create("https://www.freedesktop.org/software/gstreamer-sdk/data/media/sintel_trailer-480p.webm"));
        playBin.pause();
        playBin.play();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Control");
            frame.setSize(200, 100);
            JButton btn = new JButton("Stop");
            btn.addActionListener(event -> {
                System.out.println("Stop action");
                playBin.stop();
                playBin.dispose();

                System.out.println("System exit");
                System.exit(0);
            });

            frame.setLayout(new FlowLayout());
            frame.add(btn);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }



    public static void main(String[] args) {
        new Main();
    }

}
