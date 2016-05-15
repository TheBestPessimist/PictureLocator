/*
 * GpxBeanFactory.java
 * 
 * Copyright (c) 2016, Karambola. All rights reserved.
 */

package pt.karambola.gpx.parser;

import pt.karambola.gpx.beans.Gpx;
import pt.karambola.gpx.beans.Point;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.beans.RoutePoint;
import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.beans.TrackPoint;
import pt.karambola.gpx.beans.TrackSegment;



public interface
GpxBeanFactory
{
	Point			newPoint( ) ;

	RoutePoint		newRoutePoint( ) ;

	Route			newRoute( ) ;

	TrackPoint		newTrackPoint( ) ;

	TrackSegment	newTrackSegment( ) ;

	Track			newTrack( ) ;

	Gpx				newGpx( ) ;
}
