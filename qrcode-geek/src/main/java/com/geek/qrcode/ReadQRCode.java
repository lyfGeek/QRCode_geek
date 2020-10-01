package com.geek.qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReadQRCode {

    public static void main(String[] args) throws IOException {
        File file = new File("./qrcode.png");

        BufferedImage bufferedImage = ImageIO.read(file);

        QRCodeDecoder qrCodeDecoder = new QRCodeDecoder();

        byte[] bytes = qrCodeDecoder.decode(new MyQRCodeImage(bufferedImage));
        String result = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("result = " + result);
        // result = https://me.csdn.net/lyfGeek
    }

}
