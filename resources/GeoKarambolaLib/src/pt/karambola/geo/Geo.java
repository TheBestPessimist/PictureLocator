/*
 * Geo.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */

package pt.karambola.geo;

import pt.karambola.R3.R3;

public class
Geo
{
	public final static double EARTHMEANRADIUS_MTR	= 6371000.0 ;

	/**
	 * Calculate earth surface distance between two geographic points.
	 * 
	 * @param lat1deg	latitude of geographic point 1 (degrees)
	 * @param lon1deg 	longitude of geographic point 1 (degrees)
	 * @param lat2deg 	latitude of geographic point 2 (degrees)
	 * @param lon2deg 	longitude of geographic point 2 (degrees)
	 * @return 			distance between the points (meters)
	 * 
	 * @author 			Afonso Santos
	 */
	public static
	double
	distance( final double lat1deg		// latitude of geographic point 1 (degrees)
			, final double lon1deg		// longitude of geographic point 1 (degrees)
			, final double lat2deg		// latitude of geographic point 2 (degrees)
			, final double lon2deg		// longitude of geographic point 2 (degrees)
			)
	{
		if (lat1deg == lat2deg  &&  lon1deg == lon2deg) 
			return 0.0 ;

		final double lat1rad = Math.toRadians(lat1deg) ;
		final double lat2rad = Math.toRadians(lat2deg) ;
		final double cos12 	 = Math.sin(lat1rad) * Math.sin(lat2rad)  +  Math.cos(lat1rad) * Math.cos(lat2rad) * Math.cos(Math.toRadians(lon1deg - lon2deg)) ;
		final double dist    = EARTHMEANRADIUS_MTR * Math.acos(cos12) ;			// Distance in meters.

		return dist ;
	}


	/**
	 * Calculate geodesic distance between two geographic points.
	 *
	 * @param lat1deg 	latitude of geographic point 1 (degrees)
	 * @param lon1deg 	longitude of geographic point 1 (degrees)
	 * @param ele1mtr 	elevation of geographic point 1 (meters)
	 * @param lat2deg 	latitude of geographic point 2 (degrees)
	 * @param lon2deg 	longitude of geographic point 2 (degrees)
	 * @param ele2mtr 	elevation of geographic point 2 (meters)
	 * @return 			distance between the points (meters)
	 *
	 * @author 			Afonso Santos
	 */
	public static
	double
	distance( final double lat1deg		// latitude of geographic point 1 (degrees)
			, final double lon1deg		// longitude of geographic point 1 (degrees)
			, final double ele1mtr		// elevation of geographic point 1 (meters)
			, final double lat2deg		// latitude of geographic point 2 (degrees)
			, final double lon2deg		// longitude of geographic point 2 (degrees)
			, final double ele2mtr		// elevation of geographic point 2 (meters)
			)
	{
		return Math.hypot( distance( lat1deg, lon1deg, lat2deg, lon2deg ), ele1mtr - ele2mtr ) ;
	}


	/**
	 * Calculate unit length 3D vector (from center of earth).
	 * 
	 * @param latDeg	latitude (degrees)
	 * @param lonDeg	longitude (degrees)
	 * @return 			unit length 3D vector (from center of earth)
	 * 
	 * @author 			Afonso Santos
	 */
	public static R3
	versor( final double latDeg, final double lonDeg )
	{
        // Convert degrees coordinates into trigonometric friendly radians.
        final double latRad = Math.toRadians( latDeg ) ;
        final double lonRad = Math.toRadians( lonDeg ) ;

        final double cosLat = Math.cos( latRad ) ;

        return new R3( cosLat * Math.sin( lonRad )		// x
            		 , cosLat * Math.cos( lonRad )		// y
            		 , Math.sin( latRad )				// z
            		 ) ;
	}


	/**
	 * Calculate 3D vector (from center of earth).
	 * 
	 * @param latDeg	latitude (degrees)
	 * @param lonDeg	longitude (degrees)
	 * @param eleMtr	elevation (meters)
	 * @return 			3D cartesian vector (from center of earth).
	 * 
	 * @author 			Afonso Santos
	 */
	public static
	R3
	cartesian( final double latDeg, final double lonDeg, final double eleMtr )
	{
		return R3.scalar( EARTHMEANRADIUS_MTR + eleMtr, versor( latDeg, lonDeg ) ) ;
	}
}
