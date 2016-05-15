/*
 * GenericPointPredicate_TypeAny.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import java.util.List;

import pt.karambola.commons.collections.Predicate;
import pt.karambola.gpx.beans.GenericPoint;

public class
GenericPointPredicate_TypeAny
implements Predicate<GenericPoint>
{
	private final List<String> accepted ;

	public
	GenericPointPredicate_TypeAny( List<String> accepted )
	{
		super( ) ;
		this.accepted = accepted ;
	}

	@Override
	public
	boolean
	evaluate( GenericPoint gpt )
	{
		if (this.accepted == null)  return false ;
		String type = gpt.getType() ;
		if (this.accepted.isEmpty())  return type == null ;		// To be able to catch the "untyped" ones.

		return (type == null) ? false : accepted.contains( type ) ;
	}
}
