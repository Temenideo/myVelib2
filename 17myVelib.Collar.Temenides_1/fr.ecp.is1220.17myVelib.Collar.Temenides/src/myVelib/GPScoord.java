package myVelib;

public class GPScoord {
	private float latittude;
	private float longitude;
	
	public GPScoord(float lat,float lon) {
		this.latittude=lat;
		this.longitude=lon;
	}
	
	public double getDistance(GPScoord place) {
		return Math.sqrt((this.latittude-place.latittude)*(this.latittude-place.latittude)+(this.longitude-place.longitude)*(this.longitude-place.longitude));
	}
	
	public float getLatittude() {
		return latittude;
	}
	public void setLatittude(float latittude) {
		this.latittude = latittude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return latittude + "," + longitude;
	}
	
	
}
