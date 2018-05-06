
import imagereader.IImageProcessor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImplementImageProcessor implements IImageProcessor{

    private static final int SHIFTONE = 0xff000000;
    private static final int SHIFTTWO = 0xff0000;
    private static final int SHIFTTHREE = 0xff00;
    private static final int SHIFTFOUR = 0xff;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int EIGHT = 8;
    private static final int SIXTEEN = 16;
    // to transfer the image to show different color
    public Image helpShow(Image sourceImg, int mode){
        BufferedImage bimage;
        if(mode == THREE){
            bimage = new BufferedImage(sourceImg.getWidth(null), sourceImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
        }
        else{
            bimage = new BufferedImage(sourceImg.getWidth(null), sourceImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(sourceImg, ZERO, ZERO, null);
        bGr.dispose();
        // get the width and height of the image under processing
        int biHeight = bimage.getHeight();
        int biWidth = bimage.getWidth();
        int[] rgb = new int [FOUR];
        for(int i = ZERO; i < biWidth; i++){
            for(int j = ZERO; j < biHeight; j++){
                int pixel = bimage.getRGB(i, j);
                //get pixel and rgb
                rgb[ZERO] = (pixel & SHIFTONE);
                rgb[ONE] = (pixel & SHIFTTWO);
                rgb[TWO] = (pixel & SHIFTTHREE);
                rgb[THREE] = (pixel & SHIFTFOUR);
                if(mode == ZERO){
                    // set to red
                    bimage.setRGB(i, j, ((rgb[ZERO]) | (rgb[ONE]) | ZERO | ZERO));
                }
                else if(mode == ONE){
                    //set to green
                    bimage.setRGB(i, j, ((rgb[ZERO]) | ZERO | (rgb[TWO]) | ZERO));
                }
                else if(mode == TWO){
                    //set to blue
                    bimage.setRGB(i, j, ((rgb[ZERO]) | ZERO | ZERO | (rgb[THREE])));
                }
                else{
                    //set to gray
                    int gray = (int)(0.299 * (rgb[ONE] >> SIXTEEN) + 0.587 * (rgb[TWO] >> EIGHT) + 0.114 * rgb[THREE]);
                    bimage.setRGB(i, j, (rgb[ZERO] | gray << SIXTEEN | gray << EIGHT | gray));
                }
            }
        }
        return bimage;
    }
    public Image showChanelR(Image sourceImg){

        return helpShow(sourceImg, ZERO);
    }
    public Image showChanelG(Image sourceImg){
        return helpShow(sourceImg, ONE);
    }
    public Image showChanelB(Image sourceImg){
        return helpShow(sourceImg, TWO);
    }
    public Image showGray(Image sourceImg){
        return helpShow(sourceImg, THREE);
    }
}