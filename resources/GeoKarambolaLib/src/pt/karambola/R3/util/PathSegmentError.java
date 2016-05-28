/*
 * PathSegmentError.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */

package pt.karambola.R3.util;

class
PathSegmentError
{
	final int	firstPointIdx ;
	final int	lastPointIdx ;
	double		maxError ;
	int			maxErrorPointIdx ;

	public
	PathSegmentError( final int firstPointIdx, final int lastPointIdx )
	{
		super( ) ;

		this.firstPointIdx	= firstPointIdx ;
		this.lastPointIdx	= lastPointIdx ;
		maxError            = 0.0 ;
		maxErrorPointIdx	= -1 ;
	}


    /**
     * Returns a String representation of this segment.
     */
    @Override
    public
    String
    toString( )
    {
		return new StringBuffer()
				   .append( firstPointIdx )
				   .append( "|" )
				   .append( lastPointIdx )
		           .toString()
		           ;
    }
}
