/*
 * GenericPointPredicate_TimeRange.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import java.util.Date;

import pt.karambola.commons.collections.Predicate;
import pt.karambola.gpx.beans.GenericPoint;

public class
GenericPointPredicate_TimeRange
implements Predicate<GenericPoint>
{
	private final Date	timeMin, timeMax ;

	public
	GenericPointPredicate_TimeRange( Date timeMin, Date timeMax )
	{
		super( ) ;
		this.timeMin = timeMin ;
		this.timeMax = timeMax ;
	}

	@Override
	public
	boolean
	evaluate( GenericPoint gpt )
	{
		Date time = gpt.getTime() ;

		if (time == null)  return (this.timeMin == null  &&  this.timeMax == null) ;				// To be able to catch the "untimed" ones.
		if (this.timeMin != null  &&  time.before( this.timeMin ))  return false ;
		if (this.timeMax != null  &&  time.after( this.timeMax ))   return false ;

		return true ;
	}
}
