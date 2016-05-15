/*
 * AbstractTypeDistanceFilter.java
 * 
 * Copyright (c) 2016 Karambola. All rights reserved.
 */

package pt.karambola.gpx.predicate;

import java.util.ArrayList;
import java.util.List;

public abstract class
AbstractTypeDistanceFilter
	extends AbstractCompoundFilter
{
	protected boolean 				isTypeFilterEnabled		= false ;
	protected boolean 				isDistanceFilterEnabled	= false ;
	protected double 				distanceRefLat ;
	protected double 				distanceRefLon ;
	protected double 				distanceRefEle ;
	protected Double				distanceMin				= null ;
	protected Double				distanceMax				= null ;
	protected final List<String>	acceptedTypes			= new ArrayList<>( ) ;


	public
	void
	enableTypeFilter( final List<String> acceptedTypes )
	{
		setTypeFilterEnabled( true ) ;
		setAcceptedTypes( acceptedTypes ) ;
	}


	public
	void
	disableTypeFilter( )
	{
		setTypeFilterEnabled( false ) ;
	}


	public
	void
	enableDistanceFilter( double refLat, double refLon, double refEle, Double distanceMin, Double distanceMax )
	{
		setDistanceFilterEnabled( true ) ;

		setDistanceRefLat( refLat ) ;
		setDistanceRefLon( refLon ) ;
		setDistanceRefEle( refEle ) ;
		setDistanceMin( distanceMin ) ;
		setDistanceMax( distanceMax ) ;
	}


	public
	void
	enableDistanceFilter( double refLat, double refLon, Double distanceMin, Double distanceMax )
	{
		enableDistanceFilter( refLat, refLon, 0.0, distanceMin, distanceMax ) ;
	}


	public
	void
	disableDistanceFilter( )
	{
		setDistanceFilterEnabled( false ) ;
	}


	/** === SETTERS === **/

	public
	void
	clearAcceptedTypes( )
	{
		acceptedTypes.clear( ) ;
		clearCompoundPredicate( ) ;
	}


	public
	void
	setTypeFilterEnabled( final boolean isTypeFilterEnabled )
	{
		this.isTypeFilterEnabled = isTypeFilterEnabled ;
		clearCompoundPredicate( ) ;
	}


	public
	void
	setDistanceFilterEnabled( final boolean isDistanceFilterEnabled )
	{
		this.isDistanceFilterEnabled = isDistanceFilterEnabled ;
		clearCompoundPredicate( ) ;
	}


	public
	void
	setDistanceRefLat( final double distanceRefLat )
	{
		this.distanceRefLat = distanceRefLat ;
		clearCompoundPredicate( ) ;
	}


	public
	void
	setDistanceRefLon( final double distanceRefLon )
	{
		this.distanceRefLon = distanceRefLon ;
		clearCompoundPredicate( ) ;
	}


	public
	void
	setDistanceRefEle( final double distanceRefEle )
	{
		this.distanceRefEle = distanceRefEle ;
		clearCompoundPredicate( ) ;
	}


	public
	void
	setDistanceMin( final Double distanceMin )
	{
		this.distanceMin = distanceMin ;
		clearCompoundPredicate( ) ;
	}


	public
	void
	setDistanceMax( final Double distanceMax )
	{
		this.distanceMax = distanceMax ;
		clearCompoundPredicate( ) ;
	}


	public
	void
	setAcceptedTypes( final List<String> acceptedTypes )
	{
		clearAcceptedTypes( ) ;
		this.acceptedTypes.addAll( acceptedTypes ) ;
		clearCompoundPredicate( ) ;
	}


	/** === GETTERS === **/

	public
	double
	getDistanceRefLat( )
	{
		return distanceRefLat ;
	}


	public
	double
	getDistanceRefLon( )
	{
		return distanceRefLon ;
	}


	public
	double
	getDistanceRefEle( )
	{
		return distanceRefEle ;
	}

	public
	boolean
	isTypeFilterEnabled( )
	{
		return isTypeFilterEnabled ;
	}


	public
	boolean
	isDistanceFilterEnabled( )
	{
		return isDistanceFilterEnabled ;
	}


	public
	boolean
	isEnabled( )
	{
		return isDistanceFilterEnabled || isTypeFilterEnabled ;
	}


	public
	List<String>
	getAcceptedTypes( )
	{
		return acceptedTypes ;
	}


	public
	Double
	getDistanceMin( )
	{
		return distanceMin ;
	}


	public
	Double
	getDistanceMax( )
	{
		return distanceMax ;
	}
}
