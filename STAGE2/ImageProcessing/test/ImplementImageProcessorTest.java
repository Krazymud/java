import static org.junit.Assert.assertEquals;

import java.awt.*;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import javax.imageio.ImageIO;

@RunWith(Parameterized.class)
public class ImplementImageProcessorTest {
	@Parameters
	public static Collection<Object[]> data(){
		//images need to test
		return Arrays.asList(new Object[][] {
				{"/home/administrator/Desktop/bmptest/goal/1_blue_goal.bmp",
				"/home/administrator/Desktop/bmptest/my/1_blue.bmp"}, 
				{"/home/administrator/Desktop/bmptest/goal/1_red_goal.bmp",
				"/home/administrator/Desktop/bmptest/my/1_red.bmp"}, 
				{"/home/administrator/Desktop/bmptest/goal/1_green_goal.bmp",
				"/home/administrator/Desktop/bmptest/my/1_green.bmp"}, 
				{"/home/administrator/Desktop/bmptest/goal/1_gray_goal.bmp",
				"/home/administrator/Desktop/bmptest/my/1_gray.bmp"}, 
				{"/home/administrator/Desktop/bmptest/goal/2_blue_goal.bmp",
				"/home/administrator/Desktop/bmptest/my/2_blue.bmp"}, 
				{"/home/administrator/Desktop/bmptest/goal/2_red_goal.bmp",
				"/home/administrator/Desktop/bmptest/my/2_red.bmp"}, 
				{"/home/administrator/Desktop/bmptest/goal/2_green_goal.bmp",
				"/home/administrator/Desktop/bmptest/my/2_green.bmp"}, 
				{"/home/administrator/Desktop/bmptest/goal/2_gray_goal.bmp",
				"/home/administrator/Desktop/bmptest/my/2_gray.bmp"}, 
		});
	}
	public FileInputStream file0;
	public FileInputStream file1;
	public Image image0;
	public Image image1;
	public ImplementImageProcessorTest(String f0, String f1) throws Exception{
	    //load images in the parameters
        file0 = new FileInputStream(f0);
        file1 = new FileInputStream(f1);
        image0 = ImageIO.read(file0);
        image1 = ImageIO.read(file1);
	}
	@Test
	public void testWidth(){
        assertEquals(image0.getWidth(null), image1.getWidth(null));
	}

	@Test
	public void testHeight(){
        assertEquals(image0.getHeight(null), image1.getHeight(null));
	}

	@Test
	public void testPixels() throws Exception{
	    //test pixels one by one
	    assertEquals(image0.getWidth(null) * image0.getHeight(null),
                image1.getWidth(null) * image1.getHeight(null));
	    int pixSize = image0.getWidth(null) * image0.getHeight(null);
	    int header = 54;
	    byte[] pix0 = new byte[pixSize];
	    byte[] pix1 = new byte[pixSize];
	    file0.skip(header);
	    file1.skip(header);
	    file0.read(pix0, 0, pixSize);
	    file1.read(pix1, 0, pixSize);
	    for(int i = 0; i < pixSize; i++){
            assertEquals(pix0[i], pix1[i]);
        }

	}
}