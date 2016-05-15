/*
 * TrackComparator.java
 * 
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.gpx.beans.Track;


public class
TrackComparator
{
	public static final Comparator<Track> LENGTH				= new TrackComparator_Length( ) ;
	public static final Comparator<Track> NAME					= new TrackComparator_Name( ) ;
	public static final Comparator<Track> TIME_OLDER2YOUNGER	= new TrackComparator_Time_Older2Younger( ) ;
	public static final Comparator<Track> TIME_YOUNGER2OLDER	= new TrackComparator_Time_Younger2Older( ) ;
	public static final Comparator<Track> TYPE					= new TrackComparator_Type( ) ;
}
