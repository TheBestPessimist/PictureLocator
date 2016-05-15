/*
 * RouteDecorator_LengthType.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.geo.Units;
import pt.karambola.gpx.beans.Route;


public class
RouteDecorator_LengthType
	extends RouteDecorator_Length
{
	public
	RouteDecorator_LengthType( Units units )
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

		return result ;
	}
}
