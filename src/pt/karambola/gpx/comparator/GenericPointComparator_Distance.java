/*
 * GenericPointComparator_Distance.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.gpx.beans.GenericPoint;
import pt.karambola.gpx.beans.Point;
import pt.karambola.gpx.util.GpxUtils;

public class
GenericPointComparator_Distance
	implements Comparator<GenericPoint>
{
	private final Point ref = new Point() ;

	public
	GenericPointComparator_Distance( final double refLat, final double refLon )
	{
		super( ) ;
		ref.setLatitude( refLat ) ;
		ref.setLongitude( refLon ) ;
	}

	public
	GenericPointComparator_Distance( final double refLat, final double refLon, final double refEle )
	{
		this( refLat, refLon ) ;
		ref.setElevation( refEle ) ;
	}


	/**
	 * Compare the geodesic distance (to a reference point) of two geographic points.
	 *
	 * @param p1 	The first Point point to compare
	 * @param p2 	The second Point point to compare
	 * @return 		a negative value if the first point distance (to the reference point)
	 *              is less than the second point distance (to the reference point), zero if the same and a positive value if greater.
	 */
	@Override
	public int
	compare( GenericPoint p1, GenericPoint p2 )
	{
		if (p1 == p2)	return  0 ;
		if (p1 == null)	return  1 ;
		if (p2 == null) return -1 ;

		final double v1 = GpxUtils.distance( ref, p1 ) ;
		final double v2 = GpxUtils.distance( ref, p2 ) ;

		return Double.compare( v1, v2 ) ;
	}
}
