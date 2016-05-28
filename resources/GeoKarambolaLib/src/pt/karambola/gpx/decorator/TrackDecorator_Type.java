/*
 * TrackDecorator_Type.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.commons.util.StringDecorator;
import pt.karambola.gpx.beans.Track;


public class
TrackDecorator_Type
implements StringDecorator<Track>
{
	@Override
	public
	String
	getStringDecoration( Track rte )
	{
		return rte.getType() != null  ?  rte.getType()  :  "?" ;
	}
}
