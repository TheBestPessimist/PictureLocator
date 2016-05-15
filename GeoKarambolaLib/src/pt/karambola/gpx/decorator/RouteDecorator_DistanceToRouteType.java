/*
 * RouteDecorator_DistanceToRouteType.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.geo.Units;
import pt.karambola.commons.util.StringDecorator;
import pt.karambola.gpx.beans.Route;

public class
RouteDecorator_DistanceToRouteType
	extends		RouteDecorator_DistanceToRoute
	implements	StringDecorator<Route>
{
	public
	RouteDecorator_DistanceToRouteType( final double refLat, final double refLon, final double refEle, final Units units )
	{
		super( refLat, refLon, refEle, units ) ;
	}


	public
	RouteDecorator_DistanceToRouteType( final double refLat, final double refLon, final Units units )
	{
		super( refLat, refLon, units ) ;
	}


	@Override
	public
	String
	getStringDecoration( final Route rte )
	{
		String result = super.getStringDecoration( rte ) ;

		if (rte.getType() != null)
			result += ", " + rte.getType() ;

		return result ;
	}
}
