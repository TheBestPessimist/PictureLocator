/*
 * RouteComparator_Type.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.commons.util.NullSafeComparator;
import pt.karambola.gpx.beans.Route;

public class
RouteComparator_Type
	implements Comparator<Route>
{
	@Override
	public int
	compare( Route r1, Route r2 )
	{
		if (r1 == r2)	return  0 ;
		if (r1 == null)	return -1 ;
		if (r2 == null) return  1 ;

		return NullSafeComparator.compare( r1.getType( ), r2.getType( ) ) ;
	}
}
