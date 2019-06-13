package com.aveco.gstreamer;

import java.awt.FlowLayout;
import java.net.URI;

import com.aveco.nue.commons.utils.ThreadHelper;
import com.sun.jna.Native;
import org.freedesktop.gstreamer.Bus;
import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.elements.AppSink;
import org.freedesktop.gstreamer.elements.PlayBin;
import org.freedesktop.gstreamer.message.Message;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {

    private PlayBin playBin;
    private AppSink appSink;
    private URI uri;


    public Main() {

        Gst.init();
        uri = URI.create("https://www.freedesktop.org/software/gstreamer-sdk/data/media/sintel_trailer-480p.webm");
        playBin = new PlayBin("Test Gstreamer");
        playBin.setURI(uri);
        playBin.pause();
        playBin.play();


        SwingUtilities.invokeLater(()->{
            JFrame frame = new JFrame("Control");
            frame.setSize(200,100);
            JButton btn = new JButton("Stop");
            btn.addActionListener(event->{
                System.out.println("Stop action");
                playBin.stop();
                ThreadHelper.sleep(2000);
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
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Sleep");

            System.out.println("Exit shotdown");
        }));
        new Main();
    }

}
