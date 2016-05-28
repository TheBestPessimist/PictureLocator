/*
 * TrackDecorator.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.commons.util.StringDecorator;
import pt.karambola.gpx.beans.Track;


public class
TrackDecorator
{
	public static final StringDecorator<Track> TYPE		= new TrackDecorator_Type( ) ;
}
