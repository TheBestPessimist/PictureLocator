/*
 * RouteDecorator_LengthTypeArity.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.geo.Units;
import pt.karambola.gpx.beans.Route;


public class
RouteDecorator_LengthTypeArity
extends RouteDecorator_Length
{
	public
	RouteDecorator_LengthTypeArity( Units units )
	{
		super( units ) ;
	}


	@Override
	public
	String
	getStringDecoration( Route rte )
	{
		String result = super.getStringDecoration( rte ) ;

		if (rte.getType() != null)
			result += ", " + rte.getType() ;

		result += ", #" + rte.getRoutePoints().size() ;

		return result ;
	}
}
