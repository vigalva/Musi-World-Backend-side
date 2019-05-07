package smartspace.layout;

public class LatLng {
	private double lng;
	private double lat;
	
	public LatLng() {
		// TODO Auto-generated constructor stub
	}
	public LatLng(double lat, double lng) {
		this.lng=lng;
		this.lat=lat;
	}
	public double getLng() {
		return lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLan(double lng) {
		this.lng = lng;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}

}
