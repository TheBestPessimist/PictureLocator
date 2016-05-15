/*
 * RouteDecorator_Type.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.commons.util.StringDecorator;
import pt.karambola.gpx.beans.Route;


public class
	RouteDecorator_Type
implements StringDecorator<Route>
{
	@Override
	public
	String
	getStringDecoration( Route rte )
	{
		return rte.getType() != null  ?  rte.getType()  :  "?" ;
	}
}
