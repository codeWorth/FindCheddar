import java.util.Arrays;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {
	
	public static void main(String[] args) {
		
		String[] imgNames = new String[]{"im1.jpg", "im2.jpg", "im3.jpg", "im4.jpg", "im5.jpg"};
		
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME ); // This is required only for computers, the phone is already fine
		
		boolean str = true;
		for (String nm : imgNames) {
		
			Mat sourceImg = Imgcodecs.imread("/Users/andrew/Desktop/Find Box/" + nm); // load the image
			Mat img = ImageUtil.resize(sourceImg);
			
			ImageProcess.setSourceImage(img);
			if (str) {
				ImageProcess.start();
				str = false;
			}
				
			while (!ImageProcess.frameProcessed.get()) {
			}
					
			ImageProcess.frameProcessed.set(false);
			Imgproc.drawContours(img, Arrays.asList(ImageProcess.lastProcessed), 0, new Scalar(0, 0, 255), 3); // draw the contour on the orignial image
			//paramters: image to draw on, the list of contours, the index of the contour we want to draw, the color to draw it in, the thickness of the line
			// I make the contour into a list because the drawContours function only accepts lists (very annoying)
			// The color is red (opencv lists colors as BGR)
			
			ImageUtil.imshow(img); // show that image
			
		}
		
		ImageProcess.stop();
	}
	
}

