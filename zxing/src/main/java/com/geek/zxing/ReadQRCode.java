package com.geek.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadQRCode {

    public static void main(String[] args) {
        MultiFormatReader multiFormatReader = new MultiFormatReader();

        File file = new File("./qr.png");
        try {
            BufferedImage image = ImageIO.read(file);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

            // 二维码参数。
            Map hints = new HashMap<>();
            // n. 暗示；提示；示意；征兆；迹象；少许；少量
            // v. 暗示；透露；示意
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.MARGIN, 2);

            Result result = multiFormatReader.decode(binaryBitmap, hints);
            System.out.println(result);
            // https://me.csdn.net/lyfGeek
            BarcodeFormat barcodeFormat = result.getBarcodeFormat();
            System.out.println("barcodeFormat = " + barcodeFormat);
            // barcodeFormat = QR_CODE
            byte[] rawBytes = result.getRawBytes();
            System.out.println("rawBytes = " + rawBytes);
            // [B@6bc168e5
            String string = new String(rawBytes);
            // A��GG3����R�76F���WB�ǖdvVV���������
            System.out.println("string = " + string);
            Map<ResultMetadataType, Object> resultMetadata = result.getResultMetadata();
            System.out.println("resultMetadata = " + resultMetadata);
            // resultMetadata = {BYTE_SEGMENTS=[[B@7b3300e5], ERROR_CORRECTION_LEVEL=M}
            ResultPoint[] resultPoints = result.getResultPoints();
            System.out.println("resultPoints = " + resultPoints);
            // [Lcom.google.zxing.ResultPoint;@2e5c649
            String text = result.getText();
            System.out.println("text = " + text);
            // https://me.csdn.net/lyfGeek
            long timestamp = result.getTimestamp();
            System.out.println("timestamp = " + timestamp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
