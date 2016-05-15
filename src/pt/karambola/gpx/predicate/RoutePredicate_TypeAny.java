/*
 * RoutePredicate_TypeAny.java
 * 
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import java.util.List;

import pt.karambola.commons.collections.Predicate;
import pt.karambola.gpx.beans.Route;

public class
RoutePredicate_TypeAny
	implements Predicate<Route>
{
	private final List<String> accepted ;

	public
	RoutePredicate_TypeAny( List<String> accepted )
	{
		super( ) ;
		this.accepted = accepted ;
	}

	public
	boolean
	evaluate( Route rte )
	{
		if (this.accepted == null)  return false ;
		String type = rte.getType() ;
		if (this.accepted.isEmpty())  return type == null ;		// To be able to catch the "untyped" ones.

		return (type == null) ? false : accepted.contains( type ) ;
	}
}
