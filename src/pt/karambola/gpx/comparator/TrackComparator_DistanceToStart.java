/*
 * TrackComparator_DistanceToStart.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.gpx.beans.Point;
import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.util.GpxUtils;

public class
TrackComparator_DistanceToStart
	implements Comparator<Track>
{
	private final Point ref = new Point() ;

	public
	TrackComparator_DistanceToStart( final double refLat, final double refLon )
	{
		super( ) ;
		ref.setLatitude( refLat ) ;
		ref.setLongitude( refLon ) ;
	}

	public
	TrackComparator_DistanceToStart( final double refLat, final double refLon, final double refEle )
	{
		this( refLat, refLon ) ;
		ref.setElevation( refEle ) ;
	}


	@Override
	public int
	compare( final Track t1, final Track t2 )
	{
		if (t1 == t2)	return  0 ;
		if (t1 == null)	return  1 ;
		if (t2 == null) return -1 ;

		final double v1 = GpxUtils.distance( ref, t1.getTrackSegments().get(0).getTrackPoints( ).get( 0 ) ) ;
		final double v2 = GpxUtils.distance( ref, t2.getTrackSegments().get(0).getTrackPoints( ).get( 0 ) ) ;

		return Double.compare( v1, v2 ) ;
	}
}
