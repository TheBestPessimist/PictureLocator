/*
 * GenericPointComparator_Time_Younger2Older.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.gpx.beans.GenericPoint;
import pt.karambola.commons.util.NullSafeComparator;

public class
GenericPointComparator_Time_Younger2Older
	implements Comparator<GenericPoint>
{
	@Override
	public int
	compare( GenericPoint p1, GenericPoint p2 )
	{
		if (p1 == p2)	return  0 ;
		if (p1 == null)	return  1 ;
		if (p2 == null) return -1 ;

		return NullSafeComparator.compare( p2.getTime( ), p1.getTime( ) ) ;
	}
}
