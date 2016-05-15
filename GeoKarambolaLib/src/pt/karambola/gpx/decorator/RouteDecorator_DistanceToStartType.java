/*
 * RouteDecorator_DistanceToStartType.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.geo.Units;
import pt.karambola.commons.util.StringDecorator;
import pt.karambola.gpx.beans.Route;

public class
RouteDecorator_DistanceToStartType
	extends		RouteDecorator_DistanceToStart
	implements	StringDecorator<Route>
{
	public
	RouteDecorator_DistanceToStartType( final double refLat, final double refLon, final double refEle, final Units units )
	{
		super( refLat, refLon, refEle, units ) ;
	}

	public
	RouteDecorator_DistanceToStartType( final double refLat, final double refLon, final Units units )
	{
		super( refLat, refLon, units ) ;
	}


	@Override
	public
	String
	getStringDecoration( Route r )
	{
		String result = super.getStringDecoration( r ) ;

		if (r.getType() != null)
			result += ", " + r.getType() ;

		return result ;
	}
}
