/*
 * RoutePredicate_LengthRange.java
 * 
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import pt.karambola.commons.collections.Predicate;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.util.GpxUtils;

public class
RoutePredicate_LengthRange
	implements Predicate<Route>
{
	private Double minLength ;
	private Double maxLength ;

	public
	RoutePredicate_LengthRange( Double minLength, Double maxLength )
	{
		super( ) ;
		this.minLength = minLength ;
		this.maxLength = maxLength ;
	}

	public
	boolean
	evaluate( Route rte )
	{
		if (this.minLength == null  &&  this.maxLength == null)  return true ;
		double rteLength = GpxUtils.lengthOfRoute( rte ) ;
		
		if (this.minLength != null  &&  rteLength < this.minLength)  return false ;
		if (this.maxLength != null  &&  rteLength > this.maxLength)  return false ;

		return true ;
	}
}
