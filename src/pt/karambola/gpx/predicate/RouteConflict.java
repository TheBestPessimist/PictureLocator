/*
 * RouteConflict.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import pt.karambola.commons.collections.BiPredicate;
import pt.karambola.gpx.beans.Route;

public class
RouteConflict
{
	public static final BiPredicate<Route,Route> OVERLAPPING	= new RouteBiPredicate_Overlapping( ) ;
}
