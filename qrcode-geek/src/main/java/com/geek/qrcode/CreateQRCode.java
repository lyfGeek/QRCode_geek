package com.geek.qrcode;

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CreateQRCode {

    public static void main(String[] args) throws IOException {

        Qrcode x = new Qrcode();
        x.setQrcodeErrorCorrect('M');// 纠错等级。
        x.setQrcodeEncodeMode('B');// N（数字）。A（a-Z）。B（其他）。
        x.setQrcodeVersion(7);// 版本号。

        String qrData = "https://me.csdn.net/lyfGeek";
//        int width = 300;
        int width = 67 + 12 * (7 - 1);
//        int height = 300;
        int height = 67 + 12 * (7 - 1);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setBackground(Color.white);
        graphics.setColor(Color.black);
        graphics.clearRect(0, 0, width, height);

        int pixoff = 2;// 偏移量。

//        byte[] d = qrData.getBytes();
        byte[] d = qrData.getBytes(StandardCharsets.UTF_8);
        if (d.length > 0 && d.length < 120) {
            boolean[][] s = x.calQrcode(d);

            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    if (s[j][i]) {
                        graphics.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                    }
                }
            }
        }
        graphics.dispose();
        bufferedImage.flush();

        ImageIO.write(bufferedImage, "png", new File("./qrcode.png"));
    }

}
