/*
 * GenericPointBiPredicate_Overlapping.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import pt.karambola.commons.collections.BiPredicate;
import pt.karambola.geo.Geo;
import pt.karambola.gpx.beans.GenericPoint;

public class
GenericPointBiPredicate_Overlapping
implements BiPredicate<GenericPoint,GenericPoint>
{
	private final double proximityToleranceMtrs ;

	public 
	GenericPointBiPredicate_Overlapping( double proximityToleranceMtrs )
	{
		super( ) ;
		this.proximityToleranceMtrs = proximityToleranceMtrs ;
	}

	@Override
	public boolean
	test( GenericPoint gpt1, GenericPoint gpt2 )
	{
		return gpt1 != gpt2
			&& Geo.distance( gpt1.getLatitude( ), gpt1.getLongitude( ), gpt2.getLatitude( ), gpt2.getLongitude( ) ) < this.proximityToleranceMtrs
			;
	}
}
