/*
 * RouteComparator.java
 * 
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.gpx.beans.Route;


public class
RouteComparator
{
	public static final Comparator<Route> LENGTH				= new RouteComparator_Length( ) ;
	public static final Comparator<Route> NAME					= new RouteComparator_Name( ) ;
	public static final Comparator<Route> NUMBER				= new RouteComparator_Number( ) ;
	public static final Comparator<Route> TIME_OLDER2YOUNGER	= new RouteComparator_Time_Older2Younger( ) ;
	public static final Comparator<Route> TIME_YOUNGER2OLDER	= new RouteComparator_Time_Younger2Older( ) ;
	public static final Comparator<Route> TYPE					= new RouteComparator_Type( ) ;
}
