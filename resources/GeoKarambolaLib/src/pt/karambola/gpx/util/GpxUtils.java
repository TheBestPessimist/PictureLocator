/*
 * GpxUtils.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.util;

import java.util.ArrayList;
import java.util.List;

import pt.karambola.R3.R3;
import pt.karambola.commons.collections.ListUtils;
import pt.karambola.commons.util.TypedUtils;
import pt.karambola.geo.Units;
import pt.karambola.gpx.beans.GenericPoint;
import pt.karambola.gpx.beans.Gpx;
import pt.karambola.gpx.comparator.GenericPointComparator;
import pt.karambola.gpx.comparator.RouteComparator;
import pt.karambola.gpx.comparator.RouteComparator_DistanceToStart;
import pt.karambola.gpx.decorator.RouteDecorator;
import pt.karambola.gpx.decorator.RouteDecorator_DistanceToStartType;
import pt.karambola.gpx.decorator.RouteDecorator_LengthType;
import pt.karambola.gpx.decorator.RouteDecorator_LengthTypeArity;
import pt.karambola.gpx.decorator.TrackDecorator_DistanceToStartType;
import pt.karambola.R3.util.PathSimplifier;
import pt.karambola.R3.util.PathSimplifierResult;
import pt.karambola.commons.util.NamedUtils;
import pt.karambola.commons.util.StringDecorator;
import pt.karambola.geo.Geo;
import pt.karambola.gpx.beans.Point;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.beans.RoutePoint;
import pt.karambola.gpx.beans.Track;
import pt.karambola.gpx.beans.TrackPoint;
import pt.karambola.gpx.beans.TrackSegment;
import pt.karambola.gpx.comparator.GenericPointComparator_Distance;
import pt.karambola.gpx.comparator.RouteComparator_DistanceToPath;
import pt.karambola.gpx.comparator.TrackComparator;
import pt.karambola.gpx.comparator.TrackComparator_DistanceToPath;
import pt.karambola.gpx.comparator.TrackComparator_DistanceToStart;
import pt.karambola.gpx.decorator.GenericPointDecorator;
import pt.karambola.gpx.decorator.GenericPointDecorator_Distance;
import pt.karambola.gpx.decorator.GenericPointDecorator_DistanceType;
import pt.karambola.gpx.decorator.RouteDecorator_DistanceToRouteType;
import pt.karambola.gpx.decorator.RouteDecorator_Length;
import pt.karambola.gpx.decorator.TrackDecorator_DistanceToTrackType;
import pt.karambola.gpx.decorator.TrackDecorator_LengthTypeArity;
import pt.karambola.gpx.predicate.GenericPointConflict;
import pt.karambola.gpx.predicate.RouteConflict;

public
class
GpxUtils
{
	final static String CREDITS = "by GeoKarambolaLib" ;


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedAlphabeticaly( final Iterable<P>	pts
                                    , List<P>			ptsSorted		// Optional output parameter.
									)
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , GenericPointComparator.NAME	// Sort by ascending name.
									   , null							// no decorator.
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedAlphabeticaly( final Iterable<P>						pts
                                    , final StringDecorator<GenericPoint>	decorator
                                    , List<P>								ptsSorted		// Optional output parameter.
									)
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , GenericPointComparator.NAME	// Sort by ascending name.
									   , decorator						// Externally provided (decorator).
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedAlphabeticaly_Type( final Iterable<P>	pts
										 , List<P>				ptsSorted		// Optional output parameter.
	                                     )
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , GenericPointComparator.NAME	// Sort by ascending point name.
									   , GenericPointDecorator.TYPE		// Decorate with (type).
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedAlphabeticaly_Distance( final Iterable<P>	pts
			                                 , final double			refLat
			                                 , final double			refLon
			                                 , final double			refEle
			                                 , final Units units
			                                 , List<P>				ptsSorted		// Optional output parameter.
											 )
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , GenericPointComparator.NAME											// Sort by ascending point name.
									   , new GenericPointDecorator_Distance( refLat, refLon, refEle, units )	// Decorate with (distance).
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedAlphabeticaly_Distance( final Iterable<P>	pts
			                                 , final double			refLat
			                                 , final double			refLon
			                                 , final Units			units
			                                 , List<P>				ptsSorted		// Optional output parameter.
											 )
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , GenericPointComparator.NAME									// Sort by ascending point name.
									   , new GenericPointDecorator_Distance( refLat, refLon, units )	// Decorate with (distance).
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedAlphabeticaly_DistanceType( final Iterable<P>	pts
			                                     , final double			refLat
			                                     , final double			refLon
			                                     , final double			refEle
			                                     , final Units			units
			                                     , List<P>				ptsSorted		// Optional output parameter.
												 )
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , GenericPointComparator.NAME												// Sort by ascending point name.
									   , new GenericPointDecorator_DistanceType( refLat, refLon, refEle, units )	// Decorate with (distance, type).
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedAlphabeticaly_DistanceType( final Iterable<P>	pts
			                                     , final double			refLat
			                                     , final double			refLon
			                                     , final Units			units
			                                     , List<P>				ptsSorted		// Optional output parameter.
												 )
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , GenericPointComparator.NAME										// Sort by ascending point name.
									   , new GenericPointDecorator_DistanceType( refLat, refLon, units )	// Decorate with (distance, type).
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedByDistance( final Iterable<P>	pts
                                 , final double			refLat
                                 , final double			refLon
                                 , final double			refEle
								 , List<P>				ptsSorted		// Optional output parameter.
                                 )
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , new GenericPointComparator_Distance( refLat, refLon, refEle )	// Sorted, closer to farther.
			   						   , null															// No decoration.
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedByDistance( final Iterable<P>	pts
                                 , final double			refLat
                                 , final double			refLon
								 , List<P>				ptsSorted		// Optional output parameter.
                                 )
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , new GenericPointComparator_Distance( refLat, refLon )	// Sorted, closer to farther.
			   						   , null													// No decoration.
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedByDistance_Distance( final Iterable<P>	pts
                                          , final double		refLat
                                          , final double		refLon
                                          , final double		refEle
                                          , final Units			units
    									  , List<P>				ptsSorted		// Optional output parameter.
                                          )
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , new GenericPointComparator_Distance( refLat, refLon, refEle )			// Sorted, closer to farther.
			   						   , new GenericPointDecorator_Distance( refLat, refLon, refEle, units )	// Decorate with (distance) info.
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedByDistance_Distance( final Iterable<P>	pts
                                          , final double		refLat
                                          , final double		refLon
                                          , final Units			units
    									  , List<P>				ptsSorted		// Optional output parameter.
                                          )
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , new GenericPointComparator_Distance( refLat, refLon )			// Sorted, closer to farther.
			   						   , new GenericPointDecorator_Distance( refLat, refLon, units )	// Decorate with (distance) info.
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedByDistance_DistanceType( final Iterable<P>	pts
                                              , final double		refLat
                                              , final double		refLon
                                              , final double		refEle
                                              , final Units			units
     										  , List<P>				ptsSorted		// Optional output parameter.
                                              )
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , new GenericPointComparator_Distance( refLat, refLon, refEle )				// Sorted, closer to farther.
									   , new GenericPointDecorator_DistanceType( refLat, refLon, refEle, units )	// Decorate with (distance, type) info.
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedByDistance_DistanceType( final Iterable<P>	pts
                                              , final double		refLat
                                              , final double		refLon
                                              , final Units			units
     										  , List<P>				ptsSorted		// Optional output parameter.
                                              )
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , new GenericPointComparator_Distance( refLat, refLon )				// Sorted, closer to farther.
									   , new GenericPointDecorator_DistanceType( refLat, refLon, units )	// Decorate with (distance, type) info.
									   , ptsSorted
									   ) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedByType( final Iterable<P>					pts
                             , final StringDecorator<GenericPoint>	decorator
							 , List<P>								ptsSorted		// Optional output parameter.
                             )
	{
		return NamedUtils
				.getNamesSortedDecorated( pts
										, GenericPointComparator.TYPE		// Sort by ascending point type.
										, decorator							// Externally provided (decorator).
										, ptsSorted
										) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedByType( final Iterable<P>	pts
							 , List<P>				ptsSorted		// Optional output parameter.
                             )
	{
		return getPointNamesSortedByType( pts
										, GenericPointDecorator.TYPE	// Decorate with (type) info
										, ptsSorted
										) ;
	}


	public static <P extends GenericPoint>
	List<String>
	getPointNamesSortedByTime( final Iterable<P>	pts
						     , List<P>				ptsSorted		// Optional output parameter.
                             )
	{
		return NamedUtils
			   .getNamesSortedDecorated( pts
									   , GenericPointComparator.TIME_YOUNGER2OLDER	// Sort by ascending point time.
									   , GenericPointDecorator.AGE					// Decorate with (age) info
									   , ptsSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedAlphabeticaly( final Iterable<R>		rtes
                                    , List<R>				rtesSorted		// Optional output parameter.
									)
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , RouteComparator.NAME	// Sort by ascending route name.
									   , null 					// No decorations
			   						   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedAlphabeticaly_Type( final Iterable<R>	rtes
										 , List<R>				rtesSorted		// Optional output parameter.
            							 )
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , RouteComparator.NAME		// Sort by ascending route name.
									   , RouteDecorator.TYPE		// Decorate with (type) info
			   						   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedAlphabeticaly_Length( final Iterable<R>	rtes
                                           , final Units		units
   	                                       , List<R>			rtesSorted		// Optional output parameter.
            							   )
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , RouteComparator.NAME					// Sort by ascending route name.
									   , new RouteDecorator_Length( units )		// Decorate with (length) info
			   						   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedAlphabeticaly_LengthType( final Iterable<R>	rtes
                                               , final Units		units
       	                                       , List<R>			rtesSorted		// Optional output parameter.
            								   )
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , RouteComparator.NAME						// Sort by ascending route name.
									   , new RouteDecorator_LengthType( units )		// Decorate with (length, type) info
			   						   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedAlphabeticaly_LengthTypeArity( final Iterable<R>	rtes
                                                    , final Units		units
            	                                    , List<R>			rtesSorted		// Optional output parameter.
            										)
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , RouteComparator.NAME							// Sort by ascending route name.
									   , new RouteDecorator_LengthTypeArity( units )	// Decorate with (length, type, #pts) info
			   						   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedByNumber( final Iterable<R>	rtes
							   , final Units		units
                               , List<R>			rtesSorted		// Optional output parameter.
							   )
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , RouteComparator.NUMBER		// Sort by ascending route number.
									   , null 						// No decorations
									   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedByNumber_LengthTypeArity( final Iterable<R>	rtes
											   , final Units		units
				                               , List<R>			rtesSorted		// Optional output parameter.
											   )
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , RouteComparator.NUMBER							// Sort by ascending route number.
									   , new RouteDecorator_LengthTypeArity( units )	// Decorate with (length, type) info
									   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedByLength( final Iterable<R>	rtes
                               , final Units		units
                               , List<R>			rtesSorted		// Optional output parameter.
							   )
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
					   				   , RouteComparator.LENGTH							// Sort by ascending route length.
									   , new RouteDecorator_LengthTypeArity( units )	// Decorate with (length, type) info
					   				   , rtesSorted
					   				   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedByDistanceToPath( final Iterable<R>	rtes
		                               , final double		refLat
		                               , final double		refLon
		                               , final double		refEle
                                       , final Units		units			// Units to use in decorator
                                       , List<R>			rtesSorted		// Optional output parameter.
                                       )
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , new RouteComparator_DistanceToPath( refLat, refLon, refEle )				// Sorted, closer to farther.
			   						   , new RouteDecorator_DistanceToRouteType( refLat, refLon, refEle, units )		// Decorate with (distance, type) info.
									   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedByDistanceToPath( final Iterable<R>	rtes
		                               , final double		refLat
		                               , final double		refLon
                                       , final Units		units			// Units to use in decorator
                                       , List<R>			rtesSorted		// Optional output parameter.
                                       )
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , new RouteComparator_DistanceToPath( refLat, refLon )				// Sorted, closer to farther.
			   						   , new RouteDecorator_DistanceToRouteType( refLat, refLon, units )		// Decorate with (distance, type) info.
									   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedByDistanceToStart( final Iterable<R>	rtes
							            , final double		refLat			// Reference latitude
							            , final double		refLon			// Reference longitude
							            , final double		refEle			// Reference elevation
                                        , final Units		units			// Units to use in decorator
	                                    , List<R>			rtesSorted		// Optional output parameter.
                                        )
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , new RouteComparator_DistanceToStart( refLat, refLon, refEle )				// Sorted, closer to farther.
									   , new RouteDecorator_DistanceToStartType( refLat, refLon, refEle, units )	// Decorate with (distance, type) info.
									   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedByDistanceToStart( final Iterable<R>	rtes
							            , final double		refLat			// Reference latitude
							            , final double		refLon			// Reference longitude
                                        , final Units		units			// Units to use in decorator
	                                    , List<R>			rtesSorted		// Optional output parameter.
                                        )
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , new RouteComparator_DistanceToStart( refLat, refLon )				// Sorted, closer to farther.
									   , new RouteDecorator_DistanceToStartType( refLat, refLon, units )	// Decorate with (distance, type) info.
									   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedByDistance( final Iterable<R>	rtes
		                         , final double			refLat
		                         , final double			refLon
		                         , final double			refEle
                                 , final Units			units			// Units to use in decorator
                                 , final boolean        toClosest		// true: to closest route path point, false: to start point.
                                 , List<R>				rtesSorted		// Optional output parameter.
                                 )
	{
		return toClosest
			 ? getRouteNamesSortedByDistanceToPath( rtes, refLat, refLon, refEle, units, rtesSorted )
			 : getRouteNamesSortedByDistanceToStart( rtes, refLat, refLon, refEle, units, rtesSorted )
			 ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedByDistance( final Iterable<R>	rtes
		                         , final double			refLat
		                         , final double			refLon
                                 , final Units			units			// Units to use in decorator
                                 , final boolean        toClosest		// true: to closest route path point, false: to start point.
                                 , List<R>				rtesSorted		// Optional output parameter.
                                 )
	{
		return toClosest
			 ? getRouteNamesSortedByDistanceToPath( rtes, refLat, refLon, units, rtesSorted )
			 : getRouteNamesSortedByDistanceToStart( rtes, refLat, refLon, units, rtesSorted )
			 ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedByTime( final Iterable<R>	rtes
	                         , final Units			units
                             , List<R>				rtesSorted		// Optional output parameter.
							 )
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , RouteComparator.TIME_YOUNGER2OLDER				// Sort by ascending age.
									   , new RouteDecorator_LengthTypeArity( units )	// Decorate with (length, type, #points) info
									   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	List<String>
	getRouteNamesSortedByType( final Iterable<R>	rtes
                             , final Units			units
                             , List<R>				rtesSorted		// Optional output parameter.
							 )
	{
		return NamedUtils
			   .getNamesSortedDecorated( rtes
									   , RouteComparator.TYPE							// Sort by ascending type.
									   , new RouteDecorator_LengthTypeArity( units )	// Decorate with (length, type, #points) info
									   , rtesSorted
									   ) ;
	}


	public static <R extends Route>
	String
	getRouteNameAnnotated( final R		rte
					     , final Units	units
					     )
	{
		String decoration = new RouteDecorator_LengthTypeArity( units ).getStringDecoration( rte ) ;

		return (rte.getName() != null)
			 ? rte.getName() + " (" + decoration + ")"
			 : decoration
			 ;
	}


    public static <T extends Track>
    List<String>
    getTrackNamesSortedAlphabeticaly( final Iterable<T> trks
                                    , final Units       units
                                    , List<T>           trksSorted  // Optional output parameter
                                    )
    {
        return NamedUtils
               .getNamesSortedDecorated( trks
                                       , TrackComparator.NAME           // Sort by ascending track name.
                                       , null                           // No decorators
                                       , trksSorted
                                       ) ;
    }


    public static <T extends Track>
    List<String>
    getTrackNamesSortedAlphabeticaly_LengthTypeArity( final Iterable<T> trks
                                                    , final Units       units
                                                    , List<T>           trksSorted  // Optional output parameter
                                                    )
    {
        return NamedUtils
               .getNamesSortedDecorated( trks
                                       , TrackComparator.NAME                           // Sort by ascending track name.
                                       , new TrackDecorator_LengthTypeArity( units )    // Decorate with (length, type) info
                                       , trksSorted
                                       ) ;
    }


	public static <T extends Track>
	List<String>
	getTrackNamesSortedByDistanceToPath( final Iterable<T>	trks
                                       , final double		refLat			// Reference latitude
                                       , final double		refLon			// Reference longitude
                                       , final double		refEle			// Reference elevation
                                       , final Units		units
                                       , List<T>			trksSorted		// Optional output parameter
                                       )
	{
		return NamedUtils
			   .getNamesSortedDecorated( trks
									   , new TrackComparator_DistanceToPath( refLat, refLon, refEle )				// Sorted, closer to farther.
									   , new TrackDecorator_DistanceToTrackType( refLat, refLon, refEle, units )		// Decorate with (distance, type) info.
									   , trksSorted
									   ) ;
	}


	public static <T extends Track>
	List<String>
	getTrackNamesSortedByDistanceToPath( final Iterable<T>	trks
                                       , final double		refLat			// Reference latitude
                                       , final double		refLon			// Reference longitude
                                       , final Units		units
                                       , List<T>			trksSorted		// Optional output parameter
                                       )
	{
		return NamedUtils
			   .getNamesSortedDecorated( trks
									   , new TrackComparator_DistanceToPath( refLat, refLon )				// Sorted, closer to farther.
									   , new TrackDecorator_DistanceToTrackType( refLat, refLon, units )		// Decorate with (distance, type) info.
									   , trksSorted
									   ) ;
	}


	public static <T extends Track>
	List<String>
	getTrackNamesSortedByDistanceToStart( final Iterable<T>	trks
							            , final double		refLat			// Reference latitude
							            , final double		refLon			// Reference longitude
                                        , final double		refEle			// Reference elevation
                                        , final Units		units
                                        , List<T>			trksSorted		// Optional output parameter
                                        )
	{
		return NamedUtils
			   .getNamesSortedDecorated( trks
									   , new TrackComparator_DistanceToStart( refLat, refLon, refEle )				// Sorted, closer to farther.
									   , new TrackDecorator_DistanceToStartType( refLat, refLon, refEle, units )	// Decorate with (distance, type) info.
									   , trksSorted
									   ) ;
	}


	public static <T extends Track>
	List<String>
	getTrackNamesSortedByDistanceToStart( final Iterable<T>	trks
                                        , final double		refLat
                                        , final double		refLon
                                        , final Units		units
                                        , List<T>			trksSorted		// Optional output parameter
                                        )
	{
		return NamedUtils
			   .getNamesSortedDecorated( trks
									   , new TrackComparator_DistanceToStart( refLat, refLon )				// Sorted, closer to farther.
									   , new TrackDecorator_DistanceToStartType( refLat, refLon, units )	// Decorate with (distance, type) info.
									   , trksSorted
									   ) ;
	}


	public static <T extends Track>
	List<String>
	getTrackNamesSortedByLength( final Iterable<T>	trks
                               , final Units		units
                               , List<T>			trksSorted	// Optional output parameter
							   )
	{
		return NamedUtils
			   .getNamesSortedDecorated( trks
									   , TrackComparator.LENGTH							// Sort by ascending Track length.
									   , new TrackDecorator_LengthTypeArity( units )	// Decorate with (length, type, #points) info
									   , trksSorted
									   ) ;
	}


	public static <T extends Track>
	List<String>
	getTrackNamesSortedByTime( final Iterable<T>	trks
	                         , final Units			units
                             , List<T>				rtesSorted		// Optional output parameter.
							 )
	{
		return NamedUtils
			   .getNamesSortedDecorated( trks
									   , TrackComparator.TIME_YOUNGER2OLDER				// Sort by ascending age.
									   , new TrackDecorator_LengthTypeArity( units )	// Decorate with (length, type, #points) info
									   , rtesSorted
									   ) ;
	}


	public static <T extends Track>
	List<String>
	getTrackNamesSortedByType( final Iterable<T>	trks
                             , final Units			units
                             , List<T>				rtesSorted		// Optional output parameter.
							 )
	{
		return NamedUtils
			   .getNamesSortedDecorated( trks
									   , TrackComparator.TYPE							// Sort by ascending type.
									   , new TrackDecorator_LengthTypeArity( units )	// Decorate with (length, type, #points) info
									   , rtesSorted
									   ) ;
	}


	public static <T extends Track>
	String
	getTrackNameAnnotated( final T		trk
						 , final Units	units
						 )
	{
		String decoration = new TrackDecorator_LengthTypeArity( units ).getStringDecoration( trk ) ;

		return (trk.getName() != null)
			 ? trk.getName() + " (" + decoration + ")"
			 : decoration
			 ;
	}


	// Similar points: overlapping points with same Name & Type.
	public static	<P extends GenericPoint>
	List<P>
	getPointsSimilar( List<P> pts )
	{
		return ListUtils.getConflictingItems( pts, GenericPointConflict.SIMILAR )  ;
	}


	// Overlapping points: those less than 10m appart.
	public static	<P extends GenericPoint>
	List<P>
	getPointsOverlapping( List<P> pts )
	{
		return ListUtils.getConflictingItems( pts, GenericPointConflict.OVERLAPPING )  ;
	}


	public static	<P extends GenericPoint>
	List<String>
	getDistinctPointTypes( Iterable<P> pts )
	{
		return 	TypedUtils.getTypes( pts ) ;
	}


	public static	<P extends GenericPoint>
	boolean
	arePointsOverlapping( P pt1, P pt2 )
	{
		return GenericPointConflict.OVERLAPPING.test( pt1, pt2 ) ;
	}


	public static	<P extends GenericPoint>
	boolean
	arePointsSimilar( P pt1, P pt2 )
	{
		return GenericPointConflict.SIMILAR.test( pt1, pt2 ) ;
	}


	public static  <P extends GenericPoint>
	List<P>
	purgePointsOverlapping( List<P> pts )
	{
		return ListUtils.purge( pts, GenericPointConflict.OVERLAPPING, GenericPointComparator.TIME_OLDER2YOUNGER ) ;
	}


	public static  <P extends GenericPoint>
	List<P>
	purgePointsSimilar( List<P> pts )
	{
		return ListUtils.purge( pts, GenericPointConflict.SIMILAR, GenericPointComparator.TIME_OLDER2YOUNGER ) ;
	}


	public static
	int
	purgePointsOverlapping( Gpx gpx )
	{
		List<Point>	originalPts			= gpx.getPoints( ) ;
		int			originalPtsCount	= originalPts.size() ;
		List<Point> purgedPts			= purgePointsOverlapping( originalPts ) ;
		int			purgedPtsCount		= purgedPts.size() ;

		if (purgedPtsCount != originalPtsCount)
			gpx.setPoints( purgedPts ) ;

		return originalPtsCount - purgedPtsCount ;
    }


	public static
	int
	purgePointsSimilar( Gpx gpx )
	{
		List<Point>	originalPts			= gpx.getPoints( ) ;
		int			originalPtsCount	= originalPts.size() ;
		List<Point> purgedPts			= purgePointsSimilar( originalPts ) ;
		int			purgedPtsCount		= purgedPts.size() ;

		if (purgedPtsCount != originalPtsCount)
			gpx.setPoints( purgedPts ) ;

		return originalPtsCount - purgedPtsCount ;
	}


	public static	<P extends Point>
	int
	addPointsPurgeOverlapping( Gpx gpx, Iterable<P> pts )
	{
		gpx.addPoints( pts ) ;
		return purgePointsOverlapping( gpx ) ;
	}


	public static	<P extends Point>
	int
	addPointsPurgeSimilar( Gpx gpx, Iterable<P> pts )
	{
		gpx.addPoints( pts ) ;
		return purgePointsSimilar( gpx ) ;
	}


	public static	<R extends Route>
	List<String>
	getDistinctRouteTypes( Iterable<R> rtes )
	{
		return TypedUtils.getTypes( rtes ) ;
	}


	public static
	int
	getRoutesMaxNumber( Gpx gpx )
	{
		int maxNumber = 0 ;

		for (Route rte: gpx.getRoutes())
			if (rte.getNumber() != null  &&  rte.getNumber() > maxNumber)
				maxNumber = rte.getNumber() ;

		return maxNumber ;
	}


	public static	<R extends Route>
	boolean
	areRoutesOverlapping( R rte1, R rte2 )
	{
		return RouteConflict.OVERLAPPING.test( rte1, rte2 ) ;
	}


	// Overlapping routes: those with WPs less than 10m appart.
	public static  <R extends Route>
	List<R>
	getRoutesOverlapping( List<R> rtes )
	{
		return ListUtils.getConflictingItems( rtes, RouteConflict.OVERLAPPING )  ;
	}


	public static  <R extends Route>
	List<R>
	purgeRoutesOverlapping( List<R> rtes )
	{
		return ListUtils.purge( rtes, RouteConflict.OVERLAPPING, RouteComparator.TIME_OLDER2YOUNGER ) ;
	}


	public static
	int
	purgeRoutesOverlapping( Gpx gpx )
	{
		List<Route>	originalRoutes		= gpx.getRoutes( ) ;
		int			originalRoutesCount	= originalRoutes.size() ;
		List<Route> purgedRoutes		= purgeRoutesOverlapping( originalRoutes ) ;
		int			purgedRoutesCount	= purgedRoutes.size() ;

		if (purgedRoutesCount != originalRoutesCount)
			gpx.setRoutes( purgedRoutes ) ;

		return originalRoutesCount - purgedRoutesCount ;
	}


	/**
	 * Reverses route-point order.
	 *
	 * @param route
	 */
	public static  <R extends Route>
	void
	reverseRoute( R route )
	{
		route.setRoutePoints( ListUtils.reverse( route.getRoutePoints( ) ) ) ;
	}


	/**
	 * Reverses track segments order and track-point order within each track segment.
	 *
	 * @param track
	 */
	public static  <T extends Track>
	void
	reverseTrack( T track )
	{
		track.setTrackSegments( ListUtils.reverse( track.getTrackSegments( ) ) ) ;
	
		for (TrackSegment trackSegment: track.getTrackSegments( ))
			trackSegment.setTrackPoints( ListUtils.reverse( trackSegment.getTrackPoints( ) ) ) ;
	}


	public static	<R extends Route>
	int
	addRoutesPurgeOverlapping( Gpx gpx, Iterable<R> rtes )
	{
		gpx.addRoutes( rtes ) ;
		return purgeRoutesOverlapping( gpx ) ;
	}


	/**
	 * Calculates the geodesic length of the 3D line that joins a path section's points in sequence.
	 *
	 * @param path 		the point path who's section length is to be calculated.
	 * @param fromIdx 	the index of the point where the relevant section begins.
	 * @param toIdx 	the index of the point where the relevant section ends.
	 *
	 * @return 		path's length (meters).
	 *
	 * @author 		Afonso Santos
	 */
	public static <P extends GenericPoint>
	double
	lengthOfPathSection( final List<P> path, final int fromIdx, final int toIdx )
	{
		double 	lengthOfPathSection = 0.0 ;

		for (int segmentIdx = fromIdx  ;  segmentIdx < toIdx  ;  ++segmentIdx)
			lengthOfPathSection += distance( path.get( segmentIdx ), path.get( segmentIdx+1 ) ) ;

		return lengthOfPathSection ;			// Distance in meters.
	}


	/**
	 * Calculates the length of the 3D line that joins all the path points in sequence.
	 *
	 * @param path 	the point path who's length is to be calculated.
	 * @return 		path's length (meters).
	 *
	 * @author 		Afonso Santos
	 */
	public static <P extends GenericPoint>
	double
	lengthOfPath( final List<P> path )
	{
		return lengthOfPathSection( path, 0, path.size() - 1 ) ;
	}
	

	/**
	 * Calculates the length of the 3D line that joins all the route's waypoints in sequence.
	 *
	 * @param rte 	the route who's length is to be calculated.
	 * @return 		route's length (meters).
	 *
	 * @author 		Afonso Santos
	 */
	public static  <R extends Route>
	double
	lengthOfRoute( final R rte )
	{
		return lengthOfPath( rte.getRoutePoints( ) ) ;
	}


	/**
	 * Calculates the length of the 3D line that joins all track points in sequence.
	 *
	 * @param trk 		the track who's length is to be calculated.
	 * @return 			track's length (meters)
	 *
	 * @author 			Afonso Santos
	 */
	public static  <T extends Track>
	double
	lengthOfTrack( final T trk )
	{
		double 	acumDistance	= 0.0 ;
	
		for (TrackSegment trkSeg: trk.getTrackSegments( ))
			acumDistance += lengthOfPath( trkSeg.getTrackPoints( ) ) ;

		return acumDistance ;			// Distance in meters.
	}


	public static <P extends GenericPoint>
	R3
	cartesianPoint( final P p )
	{
		return Geo.cartesian( p.getLatitude(), p.getLongitude(), p.getElevation() != null ? p.getElevation() : 0.0 ) ;
	}


	public static <P extends GenericPoint>
	R3[]
	cartesianPath( final List<P> points )
	{
		final R3[] pointsR3	= new R3[points.size()] ;
	
		int pointIdx = 0 ;
	
		for (final P p: points)
			pointsR3[pointIdx++] = cartesianPoint( p ) ;
	
		return pointsR3;
	}


	private static <P extends GenericPoint>
	double
	simplifyPoints( List<P> points, final int maxPoints, final double accuracyMtr )
	{
		PathSimplifierResult pathSimplifierResult = PathSimplifier.simplify( cartesianPath( points ), maxPoints, accuracyMtr ) ;
	
		final List<P>	simplifiedPoints = new ArrayList<>( ) ;
	
		for (final int pointIdx: pathSimplifierResult.pointsIdxs)
			simplifiedPoints.add( points.get( pointIdx ) ) ;
	
		points.clear( ) ;
		points.addAll( simplifiedPoints ) ;
	
		return pathSimplifierResult.error ;
	}


	public static  <R extends Route>
	double
	simplifyRoute( R route, final int maxPoints, final double accuracyMtr )
	{
		final List<RoutePoint>	routePoints	= new ArrayList<>( route.getRoutePoints() ) ;
		final double			resultError = simplifyPoints( routePoints, maxPoints, accuracyMtr ) ;
	
		if (route.getSrc( ) == null)
			route.setSrc( CREDITS ) ;
	
		route.setRoutePoints( routePoints ) ;
	
		return resultError ;
	}


	/**
	 * Each track segment is simplified as an independent path, track segments are NOT joined toguether.
	 *
	 * @param track
	 */
	public static  <T extends Track>
	double
	simplifyTrack( T track, final int maxPoints, final double accuracyMtr  )
	{
		double trackError = 0.0 ;
	
		for (TrackSegment trkSeg: track.getTrackSegments() )
		{
			final List<TrackPoint>	trackSegmentPoints = new ArrayList<>( trkSeg.getTrackPoints() ) ;
			final double			trackSegmentError  = simplifyPoints( trackSegmentPoints, maxPoints, accuracyMtr ) ;
			trkSeg.setTrackPoints( trackSegmentPoints ) ;
	
			if (track.getSrc( ) == null)
				track.setSrc( CREDITS ) ;
	
			if (trackSegmentError > trackError)
				trackError = trackSegmentError ;
		}
	
		return trackError ;
	}


	/**
	 * Calculates the geodesic distance between 2 points.
	 *
	 * @param a 	fist point.
	 * @param a 	second point.
	 * @return 		geodesic distance from a to b (meters).
	 *
	 * @author 		Afonso Santos
	 */
	public static  <P extends GenericPoint>
	double
	distance( final P a, final P b )
	{
		if (a.getElevation() != null  &&  b.getElevation() != null)
			return Geo.distance( a.getLatitude(), a.getLongitude(), a.getElevation(), b.getLatitude(), b.getLongitude(), b.getElevation()) ;

		return Geo.distance( a.getLatitude(), a.getLongitude(), b.getLatitude(), b.getLongitude()) ;
	}


	/**
	 * Calculates the Cartesian distance from a point to a route's path line.
	 *
	 * @param v 	the point from which the distance is measured
	 * @param rte 	the route that defines the path line.
	 *
	 * @return 		an array of 3 doubles:
	 *              [0] distance from v to the closest point of the route's path line,
	 *       		[1] the integer part is the index of the route segment that contains the closest point.
	 *                  0 is the segment that joins points [0, 1]
	 *                  1 is the segment that joins points [1, 2]
	 *                  i is the segment that joins points [i, i+1]
	 *       		[2] segment coefficient for the closest point within the closest route segment.
	 *                  Coefficient values < 0 mean the closest point is the start point of the segment.
	 *                  Coefficient values > 1 mean the closest point is the end point of the segment.
	 *                  Coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public static  <R extends Route>
	double[]
	distanceToRoute( final R3 v, final R rte )
	{
		return R3.distanceToPath( v, cartesianPath( rte.getRoutePoints() ) ) ;
	}
	
	
	/**
	 * Calculates the Cartesian distance from a point to a route's path line.
	 *
	 * @param refLat 	the latitude of the point from which the distance is measured
	 * @param refLon 	the longitude of the point from which the distance is measured
	 * @param refEle 	the elevation of the point from which the distance is measured
	 * @param rte 	    the route that defines the path line.
	 *
	 * @return 		an array of 3 doubles:
	 *              [0] distance to the closest point of the route's path line,
	 *       		[1] the integer part is the index of the route segment that contains the closest point.
	 *                  0 is the segment that joins points [0, 1]
	 *                  1 is the segment that joins points [1, 2]
	 *                  i is the segment that joins points [i, i+1]
	 *       		[2] segment coefficient for the closest point within the closest route segment.
	 *                  Coefficient values < 0 mean the closest point is the start point of the segment.
	 *                  Coefficient values > 1 mean the closest point is the end point of the segment.
	 *                  Coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public static  <R extends Route>
	double[]
	distanceToRoute( final double refLat, final double refLon, final double refEle, final R rte )
	{
		return distanceToRoute( Geo.cartesian( refLat, refLon, refEle ), rte ) ;
	}
	
	
	/**
	 * Calculates the Cartesian distance from a point to a route's path line.
	 *
	 * @param refLat 	the latitude of the point from which the distance is measured
	 * @param refLon 	the longitude of the point from which the distance is measured
	 * @param rte 	    the route that defines the path line.
	 *
	 * @return 		an array of 3 doubles:
	 *              [0] distance to the closest point of the route's path line,
	 *       		[1] the integer part is the index of the route segment that contains the closest point.
	 *                  0 is the segment that joins points [0, 1]
	 *                  1 is the segment that joins points [1, 2]
	 *                  i is the segment that joins points [i, i+1]
	 *       		[2] segment coefficient for the closest point within the closest route segment.
	 *                  Coefficient values < 0 mean the closest point is the start point of the segment.
	 *                  Coefficient values > 1 mean the closest point is the end point of the segment.
	 *                  Coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public static  <R extends Route>
	double[]
	distanceToRoute( final double refLat, final double refLon, final R rte )
	{
		return distanceToRoute( refLat, refLon, 0.0, rte ) ;
	}


	/**
	 * Calculates the Cartesian distance from a point to a route's path line.
	 *
	 * @param p 	the point from which the distance is measured.
	 * @param rte 	the route that defines the path line.
	 *
	 * @return 		an array of 3 doubles:
	 *              [0] distance to the closest point of the route's path line,
	 *       		[1] the (integer part is the) index of the route segment that contains the closest point.
	 *                  0 is the segment that joins points [0, 1]
	 *                  1 is the segment that joins points [1, 2]
	 *                  i is the segment that joins points [i, i+1]
	 *       		[2] segment coefficient for the closest point within the closest route segment.
	 *                  Coefficient values < 0 mean the closest point is the start point of the segment.
	 *                  Coefficient values > 1 mean the closest point is the end point of the segment.
	 *                  Coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public static  <P extends GenericPoint, R extends Route>
	double[]
	distanceToRoute( final P p, final R rte )
	{
		return p.getElevation() == null
			 ? distanceToRoute( p.getLatitude(), p.getLongitude(), rte )
			 : distanceToRoute( p.getLatitude(), p.getLongitude(), p.getElevation(), rte )
			 ;
	}


	/**
	 * Calculates the Cartesian distance from a point to a section of a route's path line.
	 *
	 * @param v 	    the point from which the distance is measured
	 * @param rte 	    the route that defines the path line.
	 * @param fromIdx  	the route point index where the relevant route section begins.
	 * @param toIdx    	the route point index where the relevant route section ends.
	 *
	 * @return 	an array of 3 doubles:
	 *          [0] distance to the closest point of the route's path line,
	 *       	[1] the integer part is the index of the route segment that contains the closest point.
	 *              0 is the segment that joins points [0, 1]
	 *              1 is the segment that joins points [1, 2]
	 *              i is the segment that joins points [i, i+1]
	 *       	[2] segment coefficient for the closest point within the closest route segment.
	 *              Coefficient values < 0 mean the closest point is the start point of the segment.
	 *              Coefficient values > 1 mean the closest point is the end point of the segment.
	 *              Coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public static <R extends Route>
	double[]
	distanceToRouteSection( final R3 v, final R rte, final int fromIdx, final int toIdx )
	{
		return R3.distanceToPathSection( v, cartesianPath( rte.getRoutePoints() ), fromIdx, toIdx ) ;
	}
	
	
	/**
	 * Calculates the Cartesian distance from a point to a section of a route's path line.
	 *
	 * @param refLat 	the latitude of the point from which the distance is measured
	 * @param refLon 	the longitude of the point from which the distance is measured
	 * @param refEle 	the elevation of the point from which the distance is measured
	 * @param rte 	    the route that defines the path line.
	 * @param fromIdx  	the route point index where the relevant route section begins.
	 * @param toIdx    	the route point index where the relevant route section ends.
	 *
	 * @return 	an array of 3 doubles:
	 *          [0] distance to the closest point of the route's path line,
	 *       	[1] the integer part is the index of the route segment that contains the closest point.
	 *              0 is the segment that joins points [0, 1]
	 *              1 is the segment that joins points [1, 2]
	 *              i is the segment that joins points [i, i+1]
	 *       	[2] segment coefficient for the closest point within the closest route segment.
	 *              Coefficient values < 0 mean the closest point is the start point of the segment.
	 *              Coefficient values > 1 mean the closest point is the end point of the segment.
	 *              Coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public static <R extends Route>
	double[]
	distanceToRouteSection( final double refLat, final double refLon, final double refEle, final R rte, final int fromIdx, final int toIdx )
	{
		return distanceToRouteSection( Geo.cartesian( refLat, refLon, refEle ), rte, fromIdx, toIdx ) ;
	}
	
	
	/**
	 * Calculates the Cartesian distance from a point to a section of a route's path line.
	 *
	 * @param refLat 	the latitude of the point from which the distance is measured
	 * @param refLon 	the longitude of the point from which the distance is measured
	 * @param rte 	    the route that defines the path line.
	 * @param fromIdx  	the route point index where the relevant route section begins.
	 * @param toIdx    	the route point index where the relevant route section ends.
	 *
	 * @return 	an array of 3 doubles:
	 *          [0] distance to the closest point of the route's path line,
	 *       	[1] the integer part is the index of the route segment that contains the closest point.
	 *              0 is the segment that joins points [0, 1]
	 *              1 is the segment that joins points [1, 2]
	 *              i is the segment that joins points [i, i+1]
	 *       	[2] segment coefficient for the closest point within the closest route segment.
	 *              Coefficient values < 0 mean the closest point is the start point of the segment.
	 *              Coefficient values > 1 mean the closest point is the end point of the segment.
	 *              Coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public static <R extends Route>
	double[]
	distanceToRouteSection( final double refLat, final double refLon, final R rte, final int fromIdx, final int toIdx )
	{
		return distanceToRouteSection( refLat, refLon, 0.0, rte, fromIdx, toIdx ) ;
	}


	/**
	 * Calculates the Cartesian distance from a point to a section of a route's path line.
	 *
	 * @param p 	    the point from which the distance is measured.
	 * @param rte 	    the route that defines the path line.
	 * @param fromIdx  	the route point index where the relevant route section begins.
	 * @param toIdx    	the route point index where the relevant route section ends.
	 *
	 * @return 	an array of 3 doubles:
	 *          [0] distance to the closest point of the route's path line,
	 *       	[1] the integer part is the index of the route segment that contains the closest point.
	 *              0 is the segment that joins points [0, 1]
	 *              1 is the segment that joins points [1, 2]
	 *              i is the segment that joins points [i, i+1]
	 *       	[2] segment coefficient for the closest point within the closest route segment.
	 *              Coefficient values < 0 mean the closest point is the start point of the segment.
	 *              Coefficient values > 1 mean the closest point is the end point of the segment.
	 *              Coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public static <P extends GenericPoint, R extends Route>
	double[]
	distanceToRouteSection( final P p, final R rte, final int fromIdx, final int toIdx )
	{
		return p.getElevation() == null
			 ? distanceToRouteSection( p.getLatitude(), p.getLongitude(),                   rte, fromIdx, toIdx )
			 : distanceToRouteSection( p.getLatitude(), p.getLongitude(), p.getElevation(), rte, fromIdx, toIdx )
			 ;
	}


	public static  <T extends Track>
	double
	distanceToTrack( final R3 v, final T trk )
	{
		double minDistanceToTrackSegment = Double.MAX_VALUE ;

		for (TrackSegment trkSeg: trk.getTrackSegments() )
		{
			final double[] distanceToPathResults  = R3.distanceToPath( v, cartesianPath( trkSeg.getTrackPoints() ) ) ;
			final double   distanceToTrackSegment = distanceToPathResults[0] ;

			if (distanceToTrackSegment < minDistanceToTrackSegment)
				minDistanceToTrackSegment = distanceToTrackSegment ;
		}

		return minDistanceToTrackSegment ; 
	}


	public static  <T extends Track>
	double
	distanceToTrack( final double refLat, final double refLon, final double refEle, final T trk )
	{
		return distanceToTrack( Geo.cartesian( refLat, refLon, refEle ), trk ) ;
	}


	public static  <T extends Track>
	double
	distanceToTrack( final double refLat, final double refLon, final T trk )
	{
		return distanceToTrack( refLat, refLon, 0.0, trk ) ;
	}


	public static  <P extends GenericPoint, T extends Track>
	double
	distanceToTrack( final P p, final T trk )
	{
		return p.getElevation() == null
			 ? distanceToTrack( p.getLatitude(), p.getLongitude(), trk )
			 : distanceToTrack( p.getLatitude(), p.getLongitude(), p.getElevation(), trk )
			 ;
	}
}
