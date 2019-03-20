package smartspace.data;

import smartspace.data.Location;

public class LocationTests {
	public void testLocatoins() {
		//GIVEN initialized Location
		Location location = new Location();
		double max = 50;
		for(double x=-max;x<max;x+=0.001)
			for(double y=-max;y<max;y+=0.001) {
				location.setX(x);
				location.setY(y);
				if (location.getX()!=x) {
					throw new RuntimeException(
						"Error while testing setX() & getX(). "
						+ "Expected: " + x
						+ ". However, actual: " + location.getX());
				}
				if (location.getY()!=y) {
					throw new RuntimeException(
						"Error while testing setY() & getY(). "
						+ "Expected: " + y
						+ ". However, actual: " + location.getY());
				}
			}
	}
	public void test() {
		testLocatoins();
	}
}
