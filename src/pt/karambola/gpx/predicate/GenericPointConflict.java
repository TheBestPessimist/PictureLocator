/*
 * GenericPointConflict.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import pt.karambola.commons.collections.BiPredicate;
import pt.karambola.gpx.beans.GenericPoint;

public class
GenericPointConflict
{
	public static final BiPredicate<GenericPoint,GenericPoint> OVERLAPPING	= new GenericPointBiPredicate_Overlapping( 10.0 ) ;
	public static final BiPredicate<GenericPoint,GenericPoint> SIMILAR		= new GenericPointBiPredicate_Similar( 10.0 ) ;
}
