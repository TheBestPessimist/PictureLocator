/*
 * TrackComparator_Name.java
 * 
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.commons.util.NullSafeComparator;
import pt.karambola.gpx.beans.Track;

public class
TrackComparator_Name
	implements Comparator<Track>
{
	public
	int
	compare( final Track t1, final Track t2 )
	{
		if (t1 == t2)	return  0 ;
		if (t1 == null)	return -1 ;
		if (t2 == null) return  1 ;

		return NullSafeComparator.compare( t1.getName( ), t2.getName( ) ) ;
	}
}
