import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.OutputStream;

import imagereader.IImageIO;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageOutputStream;

public class ImplementImageIO implements IImageIO{
    public int shift(byte[] f, int a, int b, int c, int d){
        return (((int)f[a] & 0xff) << 24 |
                ((int)f[b]& 0xff) << 16 |
                ((int)f[c]& 0xff) << 8 |
                (int)f[d] & 0xff);
    }
    public class fileHeader{
        public int bfType = 0;
        public int bfSize;
        public int bfReserved1 = 0;
        public int bfReserved2 = 0;
        public int bfOffBits;
        public fileHeader(FileInputStream file){
            int fileHeaderSize = 14;
            byte[] fHeader = new byte[fileHeaderSize];
            try {
                file.read(fHeader, 0, fileHeaderSize);
                bfSize = shift(fHeader,5,4,3,2);
                bfOffBits = shift(fHeader,13,12,11,10);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public class infoHeader{
        public int biSize = 40;
        public int biWidth;
        public int biHeight;
        public int biBitCount = 24;
        public int biSizeImage;
        public int biClrUsed;
        public int biClrImportant;
        public int bmpInUse[];
        public infoHeader(FileInputStream file){
            byte[] fHeader = new byte[biSize];
            try{
                file.read(fHeader, 0, biSize);
                biWidth = shift(fHeader,7,6,5,4);
                biHeight = shift(fHeader,11,10,9,8);
                biSizeImage = shift(fHeader,23,22,21,20);
                biClrUsed = shift(fHeader,35,34,33,32);
                biClrImportant = shift(fHeader,39,38,37,36);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        public Image toImage(FileInputStream file){
            byte[] bmp = new byte[biSizeImage];
            try{
                file.read(bmp, 0, biSizeImage);
                bmpInUse = new int[biWidth * biHeight];
                int blank = (biWidth * biBitCount + 31) / 32 * 4 - biWidth * 3;
                int temp = 0;
                for(int i = biHeight - 1; i >= 0; i--){
                    for(int j = 0; j < biWidth; j++){
                        bmpInUse[temp] = ((255 & 0xff) << 24 |
                                ((int)bmp[i * (3 * biWidth + blank) + j * 3 + 2] & 0xff) << 16 |
                                ((int)bmp[i * (3 * biWidth + blank) + j * 3 + 1] & 0xff) << 8 |
                                (int)bmp[i * (3 * biWidth + blank) + j * 3] & 0xff);
                        temp++;
                    }
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            Image img = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(biWidth, biHeight,
                    bmpInUse, 0, biWidth));
            return img;
        }
    }

    public Image myRead(String path){
        Image img = null;
        FileInputStream input = null;
        try{
            input = new FileInputStream(path);
            fileHeader fHeader = new fileHeader(input);
            infoHeader iHeader = new infoHeader(input);
            img = iHeader.toImage(input);
        } catch(Exception e){
            e.printStackTrace();
        }
        return img;
    }
    public Image myWrite(Image img, String path){
        try{
            if(!path.contains("bmp")){
                path += ".bmp";
            }
            File output = new File(path);
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();

            ImageIO.write(bimage, "bmp", output);

        }catch(Exception e){
            e.printStackTrace();
        }
        return img;
    }
}