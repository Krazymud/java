import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.*;
import java.awt.*;

import imagereader.IImageIO;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageOutputStream;

public class ImplementImageIO implements IImageIO{

    // shifting - get the pixels message 
    public int shift(byte[] f, int a, int b, int c, int d){
        int temp0 = ((int)f[a] & 0xff) << 24 | ((int)f[b]& 0xff) << 16;
        int temp1 = ((int)f[c]& 0xff) << 8 | (int)f[d] & 0xff;
        return temp0 | temp1;
    }
    public class FileHeader{
        // get fileHeader message
        public FileHeader(FileInputStream file) throws IOException {
            int fileHeaderSize = 14;
            byte[] fHeader = new byte[fileHeaderSize];
                file.read(fHeader, 0, fileHeaderSize);
        }
    }
    public final class InfoHeader{
        // get infoHeader message
        private int biSize = 40;
        private int biWidth;
        private int biHeight;
        private int biBitCount = 24;
        private int biSizeImage;
        private int bmpInUse[];
        private InfoHeader(FileInputStream file) throws IOException{
            byte[] fHeader = new byte[biSize];
                file.read(fHeader, 0, biSize);
                biWidth = shift(fHeader,7,6,5,4);
                biHeight = shift(fHeader,11,10,9,8);
                biSizeImage = shift(fHeader,23,22,21,20);
            // read the width and height and size of the image
        }

        // from binary stream to image
        public Image toImage(FileInputStream file) throws IOException{
            byte[] bmp = new byte[biSizeImage];
            Image img = null;
                // read pixels into bmmpuse
            file.read(bmp, 0, biSizeImage);
            bmpInUse = new int[biWidth * biHeight];
            int blank = (biWidth * biBitCount + 31) / 32 * 4 - biWidth * 3;
            int temp = 0;
            for(int i = biHeight - 1; i >= 0; i--){
                for(int j = 0; j < biWidth; j++){
                    // set pixel
                    int temp0 = (255 & 0xff) << 24 |
                            ((int)bmp[i * (3 * biWidth + blank) + j * 3 + 2] & 0xff) << 16;
                    int temp1 = ((int)bmp[i * (3 * biWidth + blank) + j * 3 + 1] & 0xff) << 8 |
                            (int)bmp[i * (3 * biWidth + blank) + j * 3] & 0xff;
                    bmpInUse[temp] = temp0 | temp1;
                    temp++;
                }
            }
                //use tookit to transfer to image
            img = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(biWidth, biHeight,
                    bmpInUse, 0, biWidth));
            return img;
        }
    }

    //read image
    public Image myRead(String path) {
        Image img = null;
        FileInputStream input = null;
        try{
            input = new FileInputStream(path);
            new FileHeader(input);
            InfoHeader iHeader = new InfoHeader(input);
            img = iHeader.toImage(input);
        }catch(Exception e){
            return null;
        }
        return img;
    }

    //save image
    public Image myWrite(Image img, String path) {
        try{
            File output = new File(path);
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();
            ImageIO.write(bimage, "bmp", output);
        }catch(Exception e){
            return null;
        }
        return img;
    }
}
