/*
 * RouteDecorator.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.commons.util.StringDecorator;
import pt.karambola.gpx.beans.Route;


public class
RouteDecorator
{
	public static final StringDecorator<Route> TYPE		= new RouteDecorator_Type( ) ;
}
