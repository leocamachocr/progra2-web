package com.leoc.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileUtil {


    public static String readFromFile(String file)
            throws IOException {
        URL fileToRead = Thread.currentThread().getContextClassLoader().getResource(file);
        try (RandomAccessFile reader = new RandomAccessFile(fileToRead.getFile(), "r");
             FileChannel channel = reader.getChannel();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            ByteBuffer buff = ByteBuffer.allocate(bufferSize);
            while (channel.read(buff) > 0) {
                out.write(buff.array(), 0, buff.position());
                buff.clear();
            }
            return new String(out.toByteArray(), StandardCharsets.UTF_8);
        }
    }
    public static void readResource(String file, OutputStream os)
            throws IOException {
        URL fileToRead = Thread.currentThread().getContextClassLoader().getResource("resources/"+file);
        try (RandomAccessFile reader = new RandomAccessFile(fileToRead.getFile(), "r");
             FileChannel channel = reader.getChannel();
             ) {
            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            ByteBuffer buff = ByteBuffer.allocate(bufferSize);
            while (channel.read(buff) > 0) {
                os.write(buff.array(), 0, buff.position());
                buff.clear();
            }
        }
    }

}
