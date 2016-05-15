/*
 * GenericPointDecorator_DistanceType.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.commons.util.StringDecorator;
import pt.karambola.geo.Units;
import pt.karambola.gpx.beans.GenericPoint;

public class
GenericPointDecorator_DistanceType
	extends		GenericPointDecorator_Distance
	implements	StringDecorator<GenericPoint>
{
	public
	GenericPointDecorator_DistanceType( final double refLat, final double refLon, final double refEle, Units units )
	{
		super( refLat, refLon, refEle, units ) ;
	}

	public
	GenericPointDecorator_DistanceType( final double refLat, final double refLon, Units units )
	{
		super( refLat, refLon, units ) ;
	}


	@Override
	public
	String
	getStringDecoration( GenericPoint p )
	{
		String result = super.getStringDecoration( p ) ;

		if (p.getType() != null)
			result += ", " + p.getType() ;

		return result ;
	}
}
