
import imagereader.IImageProcessor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImplementImageProcessor implements IImageProcessor{
    public int biHeight;
    public int biWidth;
    public Image helpShow(Image sourceImg, int mode){
        BufferedImage bimage;
        if(mode == 3){
            bimage = new BufferedImage(sourceImg.getWidth(null), sourceImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
        }
        else{
            bimage = new BufferedImage(sourceImg.getWidth(null), sourceImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(sourceImg, 0, 0, null);
        bGr.dispose();
        biHeight = bimage.getHeight();
        biWidth = bimage.getWidth();
        int[] rgb = new int [4];
        for(int i = 0; i < biWidth; i++){
            for(int j = 0; j < biHeight; j++){
                int pixel = bimage.getRGB(i, j);
                rgb[0] = (pixel & 0xff000000);
                rgb[1] = (pixel & 0xff0000);
                rgb[2] = (pixel & 0xff00);
                rgb[3] = (pixel & 0xff);
                if(mode == 0){
                    bimage.setRGB(i, j, ((rgb[0]) | (rgb[1]) | 0 | 0));
                }
                else if(mode == 1){
                    bimage.setRGB(i, j, ((rgb[0]) | 0 | (rgb[2]) | 0));
                }
                else if(mode == 2){
                    bimage.setRGB(i, j, ((rgb[0]) | 0 | 0 | (rgb[3])));
                }
                else{

                    int gray = (int)(0.299 * (rgb[1] >> 16) + 0.587 * (rgb[2] >> 8) + 0.114 * rgb[3]);
                    bimage.setRGB(i, j, (rgb[0] | gray << 16 | gray << 8 | gray));
                }
            }
        }
        return bimage;
    }
    public Image showChanelR(Image sourceImg){

        return helpShow(sourceImg, 0);
    }
    public Image showChanelG(Image sourceImg){
        return helpShow(sourceImg, 1);
    }
    public Image showChanelB(Image sourceImg){
        return helpShow(sourceImg, 2);
    }
    public Image showGray(Image sourceImg){
        return helpShow(sourceImg, 3);
    }
}