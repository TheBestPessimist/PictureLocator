/*
 * RoutePredicate_DistanceToStart.java
 * 
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import java.util.List;

import pt.karambola.commons.collections.Predicate;
import pt.karambola.geo.Geo;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.beans.RoutePoint;

public class
RoutePredicate_DistanceToStart
	implements Predicate<Route>
{
	private final double	refLat ;
	private final double	refLon ;
	private final double	refEle ;
	private final Double	distanceMin ;
	private final Double	distanceMax ;

	public
	RoutePredicate_DistanceToStart( double refLat, double refLon, double refEle, Double distanceMin, Double distanceMax )
	{
		super( ) ;
		this.refLat  		= refLat ;
		this.refLon 		= refLon ;
		this.refEle 		= refEle ;
		this.distanceMin 	= distanceMin ;
		this.distanceMax 	= distanceMax ;
	}

	public
	RoutePredicate_DistanceToStart( double refLat, double refLon, Double distanceMin, Double distanceMax )
	{
		this( refLat, refLon, 0.0, distanceMin, distanceMax ) ;
	}

	public
	boolean
	evaluate( Route rte )
	{
		if (this.distanceMin != null  ||  this.distanceMax != null)
		{
			List<RoutePoint> rtePts = rte.getRoutePoints() ;
			if (rtePts.size() < 1)  return false ;
			RoutePoint rteStart = rtePts.get(0) ;

			final double distance = rteStart.getElevation() != null
					              ? Geo.distance( rteStart.getLatitude( ), rteStart.getLongitude( ), rteStart.getElevation( ), this.refLat, this.refLon, this.refEle )
					              : Geo.distance( rteStart.getLatitude( ), rteStart.getLongitude( ), this.refLat, this.refLon )
					              ;
			
			if (this.distanceMin != null  &&  distance < this.distanceMin)  return false ;
			if (this.distanceMax != null  &&  distance > this.distanceMax)  return false ;
		}

		return true ;
	}
}
