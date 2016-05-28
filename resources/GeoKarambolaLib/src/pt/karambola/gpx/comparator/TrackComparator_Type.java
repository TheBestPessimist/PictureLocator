/*
 * TrackComparator_Type.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.commons.util.NullSafeComparator;
import pt.karambola.gpx.beans.Track;

public class
TrackComparator_Type
	implements Comparator<Track>
{
	@Override
	public int
	compare( Track t1, Track t2 )
	{
		if (t1 == t2)	return  0 ;
		if (t1 == null)	return -1 ;
		if (t2 == null) return  1 ;

		return NullSafeComparator.compare( t1.getType( ), t2.getType( ) ) ;
	}
}
