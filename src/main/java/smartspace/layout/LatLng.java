package smartspace.layout;

public class LatLng {
	private double lan;
	private double lat;
	
	public LatLng() {
		// TODO Auto-generated constructor stub
	}
	public LatLng(double lan, double lat) {
		this.lan=lan;
		this.lat=lat;
	}
	public double getLan() {
		return lan;
	}
	public double getLat() {
		return lat;
	}
	public void setLan(double lan) {
		this.lan = lan;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}

}
