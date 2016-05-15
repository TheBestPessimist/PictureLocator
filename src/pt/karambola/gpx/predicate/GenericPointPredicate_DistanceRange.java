/*
 * GenericPointPredicate_DistanceRange.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import pt.karambola.commons.collections.Predicate;
import pt.karambola.geo.Geo;
import pt.karambola.gpx.beans.GenericPoint;

public class
GenericPointPredicate_DistanceRange
implements Predicate<GenericPoint>
{
	private final double	referenceLatitude ;
	private final double	referenceLongitude ;
	private final Double	distanceMin ;
	private final Double	distanceMax ;

	public
	GenericPointPredicate_DistanceRange( double referenceLatitude, double referenceLongitude, Double distanceMin, Double distanceMax )
	{
		super( ) ;
		this.referenceLatitude  = referenceLatitude ;
		this.referenceLongitude = referenceLongitude ;
		this.distanceMin 		= distanceMin ;
		this.distanceMax 		= distanceMax ;
	}

	@Override
	public
	boolean
	evaluate( GenericPoint gpt )
	{
		if (this.distanceMin != null  ||  this.distanceMax != null)
		{
			final double distance = Geo.distance( gpt.getLatitude( ), gpt.getLongitude( ), this.referenceLatitude, this.referenceLongitude ) ;

			if (this.distanceMin != null  &&  distance < this.distanceMin)  return false ;
			if (this.distanceMax != null  &&  distance > this.distanceMax)  return false ;
		}

		return true ;
	}
}
