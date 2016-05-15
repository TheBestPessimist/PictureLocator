/*
 * RoutePredicate_DistanceToPath.java
 * 
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import pt.karambola.R3.R3;
import pt.karambola.commons.collections.Predicate;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.util.GpxUtils;
import pt.karambola.geo.Geo;

public class
RoutePredicate_DistanceToPath
	implements Predicate<Route>
{
	private final R3 ref ;
	private final Double	distanceMin ;
	private final Double	distanceMax ;

	public
	RoutePredicate_DistanceToPath( double refLat, double refLon, double refEle, Double distanceMin, Double distanceMax )
	{
		super( ) ;
		ref  = Geo.cartesian( refLat, refLon, refEle ) ;
		this.distanceMin 	= distanceMin ;
		this.distanceMax 	= distanceMax ;
	}

	public
	RoutePredicate_DistanceToPath( double refLat, double refLon, Double distanceMin, Double distanceMax )
	{
		this( refLat, refLon, 0.0, distanceMin, distanceMax ) ;
	}

	public
	boolean
	evaluate( Route rte )
	{
		if (this.distanceMin != null  ||  this.distanceMax != null)
		{
			final double distance = GpxUtils.distanceToRoute( ref, rte )[0] ;
			
			if (this.distanceMin != null  &&  distance < this.distanceMin)  return false ;
			if (this.distanceMax != null  &&  distance > this.distanceMax)  return false ;
		}

		return true ;
	}
}
