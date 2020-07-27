package com.geek.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class createQRCode {

    public static void main(String[] args) {
        int width = 300;
        int height = 300;
        String format = "png";
        String content = "https://me.csdn.net/lyfGeek";

        // 二维码参数。
        Map hints = new HashMap<>();
        // n. 暗示；提示；示意；征兆；迹象；少许；少量
        // v. 暗示；透露；示意
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);

        // 生成。
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            Path path = new File("./qr.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, path);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
