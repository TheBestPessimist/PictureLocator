/*
 * RouteDecorator_Length.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.commons.util.StringDecorator;
import pt.karambola.geo.Units;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.util.GpxUtils;


public class
RouteDecorator_Length
	implements StringDecorator<Route>
{
	private final Units   units ;

	public
	RouteDecorator_Length( Units units )
	{
		super( ) ;
		this.units  = units ;
	}


	@Override
	public
	String
	getStringDecoration( Route rte )
	{
		String[] formatedDistance = Units.formatDistance( GpxUtils.lengthOfRoute( rte ), units ) ;

		return formatedDistance[0] + " " + formatedDistance[1] ;
	}
}
