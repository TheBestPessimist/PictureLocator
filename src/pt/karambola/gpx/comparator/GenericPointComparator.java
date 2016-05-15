/*
 * GenericPointComparator.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.gpx.beans.GenericPoint;


public class
GenericPointComparator
{
	public static final Comparator<GenericPoint> NAME				= new GenericPointComparator_Name( ) ;
	public static final Comparator<GenericPoint> TIME_YOUNGER2OLDER	= new GenericPointComparator_Time_Younger2Older( ) ;
	public static final Comparator<GenericPoint> TIME_OLDER2YOUNGER	= new GenericPointComparator_Time_Older2Younger( ) ;
	public static final Comparator<GenericPoint> TYPE				= new GenericPointComparator_Type( ) ;
}
