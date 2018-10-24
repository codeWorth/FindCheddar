import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

/**
 * This is a convience class the groups a contour with some of its properties.
 * It has an area that is set on initializing. There is also a method to find the convex hull
 * of the contour, and its area.
 * 
 * @author andrew
 *
 */
public class MatContour implements Comparable<MatContour> {

	public MatOfPoint mat;
	public double area;
	public MatOfPoint hull;
	public double hullArea;
	
	public MatContour(MatOfPoint mat) {
		this.mat = mat;
		this.area = Imgproc.contourArea(this.mat);
		this.hull = new MatOfPoint();
	}

	@Override
	public int compareTo(MatContour o) { // this method helps sort contours so that the highest area one comes first
		return (int) Math.signum(o.area - this.area); // this line compares my area to another dude's area and returns a number depending
	}
	
	public void findHull() {
		
		// The code below is for finding the convex hull and its area
		
		MatOfInt hullIs = new MatOfInt(); // The convex hull is stored as a list of indecies into the original contour
		Imgproc.convexHull(mat, hullIs); // Generates the convex hull. Look up convex hull online (it's the outline of a contour)
		Point[] contPoints = mat.toArray(); // points from the contour
		int[] contIs = hullIs.toArray(); // indecies from the hull
		Point[] points = new Point[hullIs.rows()]; // the empty list we will be putting points into
		
		for (int i = 0; i < points.length; i++) {
			points[i] = contPoints[contIs[i]]; // go thru each index and add the corresponding point to the array
		}
		
		this.hull.fromArray(points); // construct the full convex hull from the found points	
		hullArea = Imgproc.contourArea(hull); // find the area of that hull
	}
	
}
