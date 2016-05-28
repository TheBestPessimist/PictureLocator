/*
 * AbstractCompoundFilter.java
 * 
 * Copyright (c) 2016 Karambola. All rights reserved.
 */

package pt.karambola.gpx.predicate;

public abstract class
AbstractCompoundFilter
{
	protected String	name ;


	abstract protected
	void
	clearCompoundPredicate( ) ;


	/** === GETTERS === **/

	public
	String
	getName( )
	{
		return this.name ;
	}


	/** === SETTERS === **/

	public
	void
	setName( String name )
	{
		this.name = name ;
	}
}
