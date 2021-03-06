import imagereader.IImageIO;
import imagereader.IImageProcessor;
import imagereader.Runner;

// run the imagereader
public final class ImageReaderRunner {
    private ImageReaderRunner(){}
    public static void main(String[] args) {
        IImageIO imageioer = new ImplementImageIO();
        IImageProcessor processor = new ImplementImageProcessor();
        Runner.run(imageioer, processor);
    }
}