/*
 * RoutePredicate_TimeRange.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import java.util.Date;

import pt.karambola.commons.collections.Predicate;
import pt.karambola.gpx.beans.Route;

public class RoutePredicate_TimeRange
implements Predicate<Route>
{
	private final Date	timeMin, timeMax ;

	public RoutePredicate_TimeRange(Date timeMin, Date timeMax)
	{
		super( ) ;
		this.timeMin = timeMin ;
		this.timeMax = timeMax ;
	}

	@Override
	public
	boolean
	evaluate( Route rte	)
	{
		Date time = rte.getRoutePoints().get(0).getTime() ;

		if (time == null)  return (this.timeMin == null  &&  this.timeMax == null) ;				// To be able to catch the "untimed" ones.
		if (this.timeMin != null  &&  time.before( this.timeMin ))  return false ;
		if (this.timeMax != null  &&  time.after( this.timeMax ))   return false ;

		return true ;
	}
}
