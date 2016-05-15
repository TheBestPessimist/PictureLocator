/*
 * PathSimplifierResult.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 *
 */

package pt.karambola.R3.util;

public class
PathSimplifierResult
{
	public final int	requestedMaxPoints ;		// PK
	public final double requestedMaxError ;			// PK
	public final int[]	pointsIdxs ;
	public final double error ;

	public
	PathSimplifierResult( final int		requestedMaxPoints
					    , final double	requestedMaxError
			            , final int[]	pointsIdxs
			            , final double	error
			            )
	{
		super( ) ;

		this.requestedMaxPoints = requestedMaxPoints ;
		this.requestedMaxError	= requestedMaxError ;
		this.pointsIdxs			= pointsIdxs ;
		this.error      		= error ;
	}


    /**
     * Returns a String representation of this result.
     */
    @Override
    public
    String
    toString( )
    {
		return new StringBuffer()
				   .append( requestedMaxPoints )
				   .append( "|" )
				   .append( requestedMaxError )
		           .toString()
		           ;
    }
}
