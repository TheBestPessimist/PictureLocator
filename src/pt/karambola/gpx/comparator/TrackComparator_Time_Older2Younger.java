/*
 * TrackComparator_Time_Older2Younger.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.beans.TrackPoint;

public class
TrackComparator_Time_Older2Younger
	implements Comparator<Track>
{
	public
	int
	compare( Track t1, Track t2 )
	{
		if (t1 == t2)	return  0 ;
		if (t1 == null)	return -1 ;
		if (t2 == null) return  1 ;

		// Track age comparison is delegated on their respective start points age comparison.
		TrackPoint start1 = t1.getTrackSegments().get(0).getTrackPoints().get(0) ;
		TrackPoint start2 = t2.getTrackSegments().get(0).getTrackPoints().get(0) ;

		return GenericPointComparator.TIME_OLDER2YOUNGER.compare( start1, start2 ) ;
	}
}
