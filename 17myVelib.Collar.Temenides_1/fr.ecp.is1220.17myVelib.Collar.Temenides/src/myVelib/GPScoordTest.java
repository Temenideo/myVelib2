package myVelib;

import static org.junit.Assert.*;

import org.junit.Test;

public class GPScoordTest {

	@Test
	public void testGetDistance() {
		GPScoord pointA=new GPScoord(1,1);
		GPScoord pointB=new GPScoord(2,2);
		assertEquals(Math.sqrt(2), pointA.getDistance(pointB),0.0001 );
	}
	@Test
	public void testGetDistance2() {
		GPScoord pointA=new GPScoord(1,1);
		GPScoord pointB=new GPScoord(1,1);
		assertEquals(0, pointA.getDistance(pointB),0.0001 );
	}
}
