/*
 * GenericPointComparator_Name.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.commons.util.NullSafeComparator;
import pt.karambola.gpx.beans.GenericPoint;

public class
GenericPointComparator_Name
	implements Comparator<GenericPoint>
{
	@Override
	public int
	compare( GenericPoint p1, GenericPoint p2 )
	{
		if (p1 == p2)	return  0 ;
		if (p1 == null)	return -1 ;
		if (p2 == null) return  1 ;

		return NullSafeComparator.compare( p1.getName( ), p2.getName( ) ) ;
	}
}
