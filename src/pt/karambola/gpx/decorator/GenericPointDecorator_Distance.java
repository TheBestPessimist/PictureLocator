/*
 * GenericPointDecorator_Distance.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.commons.util.StringDecorator;
import pt.karambola.geo.Units;
import pt.karambola.gpx.beans.GenericPoint;
import pt.karambola.gpx.beans.Point;
import pt.karambola.gpx.util.GpxUtils;

public class
GenericPointDecorator_Distance
	implements StringDecorator<GenericPoint>
{
	private final Point ref = new Point() ;
	private final Units units ;

	public
	GenericPointDecorator_Distance( final double refLat, final double refLon, final double refEle, Units units )
	{
		this( refLat, refLon, units ) ;
		ref.setElevation( refEle ) ;
	}

	public
	GenericPointDecorator_Distance( final double refLat, final double refLon, Units units )
	{
		super( ) ;
		ref.setLatitude( refLat ) ;
		ref.setLongitude( refLon ) ;
		this.units  = units ;
	}

	
	@Override
	public
	String
	getStringDecoration( GenericPoint p )
	{
		String[] formatedDistance = Units.formatDistance( GpxUtils.distance( ref, p ), units ) ;

		return formatedDistance[0] + " " + formatedDistance[1] ;
	}
}
