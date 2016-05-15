/*
 * GpxRouteUtils.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */

package pt.karambola.gpx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.karambola.R3.R3;
import pt.karambola.R3.util.PathSimplifier;
import pt.karambola.R3.util.PathSimplifierResult;
import pt.karambola.geo.Geo;
import pt.karambola.gpx.beans.GenericPoint;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.beans.RoutePoint;

public class
GpxRouteUtils
{
	final private Route 						originalRoute ;
	final private int                           nPoints ;
	final private int                           nSegments ;
	final private int                           lastPointIdx ;
	final private List<RoutePoint>				geodesicRoutePoints	;
	final private R3[] 							cartesianRoutePoints ;
	final private R3[] 							cartesianRouteSegments ;
	final private double[]                      routeSegmentsGeodesicLength ;
	final private double[]                      routeSegmentsCartesianLength ;
	final private double[]                      geodesicDistanceFromFirstPoint ;
	final private double[]                      geodesicDistanceToLastPoint ;
	private Double                        		geodesicDistanceFromFirstPointToLastPoint ;

	final private Map<String,List<RoutePoint>>	cachedSimplifiedRoutePoints	= new HashMap<>() ;
	final private Map<String,Double>			cachedSimplifiedRouteErrors	= new HashMap<>() ;

	/**
	 * This class should only be used in the case one wants to <b>repeatedly</b> simplify the <b>same</b> route with different simplification parameters.
	 * The best example of this would be a graphical interactive route simplificator.
	 * @param route
	 */
	public
	GpxRouteUtils( Route route )
	{
		super( ) ;

		originalRoute                	= route ;
		geodesicRoutePoints	         	= new ArrayList<>( originalRoute.getRoutePoints() ) ;
		cartesianRoutePoints	     	= GpxUtils.cartesianPath( geodesicRoutePoints ) ;
		nPoints          	 		 	= geodesicRoutePoints.size() ;
		lastPointIdx = nSegments     	= nPoints - 1 ;
		cartesianRouteSegments       	= new R3[nSegments] ;
		routeSegmentsGeodesicLength  	= new double[nSegments] ;
		routeSegmentsCartesianLength 	= new double[nSegments] ;

		for (int segmentIdx = 0  ;  segmentIdx < nSegments  ;  ++segmentIdx )
		{
			// The geodesic length of the segment.
			routeSegmentsGeodesicLength[segmentIdx] = GpxUtils.distance( geodesicRoutePoints.get( segmentIdx ), geodesicRoutePoints.get( segmentIdx+1 ) ) ;

			// 3D vector from start point to end point of segment.
			cartesianRouteSegments[segmentIdx] = R3.sub( cartesianRoutePoints[segmentIdx+1], cartesianRoutePoints[segmentIdx] ) ;

			// The Cartesian length of the segment.
			routeSegmentsCartesianLength[segmentIdx] = R3.modulus( cartesianRouteSegments[segmentIdx] ) ;
		}

		// Compute cache of distances from first route point.
		geodesicDistanceFromFirstPoint    = new double[nPoints] ;
		geodesicDistanceFromFirstPoint[0] = 0.0 ;

		for (int pointIdx = 1  ;  pointIdx <= lastPointIdx  ;  ++pointIdx )
			geodesicDistanceFromFirstPoint[pointIdx] = geodesicDistanceFromFirstPoint[pointIdx-1] + routeSegmentsGeodesicLength[pointIdx-1] ;

		// Compute cache of distances to last route point.
		geodesicDistanceToLastPoint               = new double[nPoints] ;
		geodesicDistanceToLastPoint[lastPointIdx] = 0.0 ;

		for (int pointIdx = lastPointIdx-1  ;  pointIdx >= 0  ;  --pointIdx )
			geodesicDistanceToLastPoint[pointIdx] = geodesicDistanceToLastPoint[pointIdx+1] + routeSegmentsGeodesicLength[pointIdx] ;
	}


	/**
	 * Simplifies the route given <b>on instantiation</b> with the given simplification parameters.
	 *
	 * @param maxPoints 	Maximum number of points allowed in the simplified route.
	 * @param accuracyMtr 	Maximum tolerated error (m) of the simplified route, provided <b>maxPoints</b> is not exceeded.
	 * @return 				The error (m) of the simplified route.
	 *
	 * @author 				Afonso Santos
	 */
	public
	double
	simplify( int maxPoints, double accuracyMtr )
	{
		if (accuracyMtr < 0.0)
			accuracyMtr = 0.0 ;

		if (maxPoints > nPoints)
			maxPoints = nPoints ;

		final String requestKey = maxPoints + "|" + accuracyMtr ;

		List<RoutePoint> simplifiedRoutePoints  = null ;
		Double 			 simplifiedRouteError	= null ;

		if ((simplifiedRoutePoints = cachedSimplifiedRoutePoints.get( requestKey )) == null)
		{
			PathSimplifierResult pathSimplifierResult = PathSimplifier.simplifySection( cartesianRoutePoints, 0, lastPointIdx, maxPoints, accuracyMtr ) ;

			simplifiedRoutePoints = new ArrayList<>( ) ;

			for (final int pointIdx: pathSimplifierResult.pointsIdxs)
				simplifiedRoutePoints.add( geodesicRoutePoints.get( pointIdx ) ) ;

			// Remember these results to avoid recalculating them if asked again.
			cachedSimplifiedRoutePoints.put( requestKey, simplifiedRoutePoints ) ;
			cachedSimplifiedRouteErrors.put( requestKey, simplifiedRouteError = pathSimplifierResult.error ) ;
		}
		else
			simplifiedRouteError = cachedSimplifiedRouteErrors.get( requestKey ) ;

		originalRoute.setRoutePoints( simplifiedRoutePoints ) ;

		return simplifiedRouteError ;
	}


	/**
	 * Calculates the Cartesian distance from a point to this route's path line.
	 *
	 * @param v 	the point from which the distance is measured
	 *
	 * @return 		an array of 3 doubles:
	 *              [0] distance from v to the closest point of the route's path line,
	 *       		[1] the integer part is the index of the route segment that contains the closest point.
	 *                  0 is the segment that joins points [0, 1]
	 *                  1 is the segment that joins points [1, 2]
	 *                  i is the segment that joins points [i, i+1]
	 *       		[2] segment coefficient for the closest point within the closest route segment.
	 *                  coefficient values < 0 mean the closest point is the start point of the segment.
	 *                  coefficient values > 1 mean the closest point is the end point of the segment.
	 *                  coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public
	double[]
	distance( final R3 v )
	{
		return R3.distanceToPathSection( v, cartesianRoutePoints, 0, lastPointIdx, cartesianRouteSegments, routeSegmentsCartesianLength ) ;
	}
	
	
	/**
	 * Calculates the Cartesian distance from a point to this route's path line.
	 *
	 * @param refLat 	the latitude  of the point from which the distance is measured
	 * @param refLon 	the longitude of the point from which the distance is measured
	 * @param refEle 	the elevation of the point from which the distance is measured
	 *
	 * @return 		an array of 3 doubles:
	 *              [0] distance to the closest point of the route's path line,
	 *       		[1] the integer part is the index of the route segment that contains the closest point.
	 *                  0 is the segment that joins points [0, 1]
	 *                  1 is the segment that joins points [1, 2]
	 *                  i is the segment that joins points [i, i+1]
	 *       		[2] segment coefficient for the closest point within the closest route segment.
	 *                  coefficient values < 0 mean the closest point is the start point of the segment.
	 *                  coefficient values > 1 mean the closest point is the end point of the segment.
	 *                  coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public
	double[]
	distance( final double refLat, final double refLon, final double refEle )
	{
		return distance( Geo.cartesian( refLat, refLon, refEle ) ) ;
	}
	
	
	/**
	 * Calculates the Cartesian distance from a point to this route's path line.
	 *
	 * @param refLat 	the latitude of the point from which the distance is measured
	 * @param refLon 	the longitude of the point from which the distance is measured
	 *
	 * @return 		an array of 3 doubles:
	 *              [0] distance to the closest point of the route's path line,
	 *       		[1] the integer part is the index of the route segment that contains the closest point.
	 *                  0 is the segment that joins points [0, 1]
	 *                  1 is the segment that joins points [1, 2]
	 *                  i is the segment that joins points [i, i+1]
	 *       		[2] segment coefficient for the closest point within the closest route segment.
	 *                  coefficient values < 0 mean the closest point is the start point of the segment.
	 *                  coefficient values > 1 mean the closest point is the end point of the segment.
	 *                  coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public
	double[]
	distance( final double refLat, final double refLon )
	{
		return distance( refLat, refLon, 0.0 ) ;
	}


	/**
	 * Calculates the Geodesic distance from a point to this route's path line.
	 *
	 * @param p 	the point from which the distance is measured.
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
	public  <P extends GenericPoint>
	double[]
	distance( final P p )
	{
		return p.getElevation() == null
			 ? distance( p.getLatitude(), p.getLongitude() )
			 : distance( p.getLatitude(), p.getLongitude(), p.getElevation() )
			 ;
	}
	
	
	/**
	 * Calculates the Cartesian distance from a point to a section of this route's path line.
	 *
	 * @param v 	    the point from which the distance is measured
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
	 *              coefficient values < 0 mean the closest point is the start point of the segment.
	 *              coefficient values > 1 mean the closest point is the end point of the segment.
	 *              coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public
	double[]
	distanceToSection( final R3 v, final int fromIdx, final int toIdx )
	{
		return R3.distanceToPathSection( v, cartesianRoutePoints, fromIdx, toIdx, cartesianRouteSegments, routeSegmentsCartesianLength ) ;
	}
	
	
	/**
	 * Calculates the Cartesian distance from a point to a section of this route's path line.
	 *
	 * @param refLat 	the latitude of the point from which the distance is measured
	 * @param refLon 	the longitude of the point from which the distance is measured
	 * @param refEle 	the elevation of the point from which the distance is measured
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
	 *              coefficient values < 0 mean the closest point is the start point of the segment.
	 *              coefficient values > 1 mean the closest point is the end point of the segment.
	 *              coefficient values between 0 and 1 mean how far along the segment the closest point is.
	 *
	 * @author 		Afonso Santos
	 */
	public
	double[]
	distanceToSection( final double refLat, final double refLon, final double refEle, final int fromIdx, final int toIdx )
	{
		return distanceToSection( Geo.cartesian( refLat, refLon, refEle ), fromIdx, toIdx ) ;
	}
	
	
	/**
	 * Calculates the Cartesian distance from a point to a section of this route's path line.
	 *
	 * @param refLat 	the latitude of the point from which the distance is measured
	 * @param refLon 	the longitude of the point from which the distance is measured
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
	public
	double[]
	distanceToSection( final double refLat, final double refLon, final int fromIdx, final int toIdx )
	{
		return distanceToSection( refLat, refLon, 0.0, fromIdx, toIdx ) ;
	}


	/**
	 * Calculates the Cartesian distance from a point to a section of this route's path line.
	 *
	 * @param p 	    the point from which the distance is measured.
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
	public  <P extends GenericPoint>
	double[]
	distanceToSection( final P p, final int fromIdx, final int toIdx )
	{
		return p.getElevation() == null
			 ? distanceToSection( p.getLatitude(), p.getLongitude(), fromIdx, toIdx )
			 : distanceToSection( p.getLatitude(), p.getLongitude(), p.getElevation(), fromIdx, toIdx )
			 ;
	}


	/**
	 * Calculates the geodesic length of a segment.
	 *
	 * @param segmentIdx 	the index of the path segment.
	 *
	 * @return 		path segment's length (meters).
	 *
	 * @author 		Afonso Santos
	 */
	public double
	lengthOfSegment( final int segmentIdx )
	{
		return routeSegmentsGeodesicLength[segmentIdx] ;
	}


	/**
	 * Calculates the geodesic length of the route's path from its first point.
	 *
	 * @param pointIdx 	the index of the point up to which length is calculated.
	 *
	 * @return 		path's initial section geodesic length (meters).
	 *
	 * @author 		Afonso Santos
	 */
	public double
	lengthFromFirstPoint( final int pointIdx )
	{
		return geodesicDistanceFromFirstPoint[pointIdx] ;
	}


	/**
	 * Calculates the geodesic length of the route's path till its last point.
	 *
	 * @param pointIdx 	the index of the point from which length is calculated.
	 *
	 * @return 		path's final section geodesic length (meters).
	 *
	 * @author 		Afonso Santos
	 */
	public double
	lengthToLastPoint( final int pointIdx )
	{
		return geodesicDistanceToLastPoint[pointIdx] ;
	}


	/**
	 * Calculates the geodesic length of the 3D line that joins a path section's points in sequence.
	 *
	 * @param fromIdx 	the index of the point where the relevant section begins.
	 * @param toIdx 	the index of the point where the relevant section ends.
	 *
	 * @return 		path's section geodesic length (meters).
	 *
	 * @author 		Afonso Santos
	 */
	public double
	lengthOfSection( final int fromIdx, final int toIdx )
	{
		switch( Integer.signum( toIdx - fromIdx ) )
		{
			case -1 :	// there is a wrap around jump from the route's last point to its first point.
				if (geodesicDistanceFromFirstPointToLastPoint == null)
					geodesicDistanceFromFirstPointToLastPoint = GpxUtils.distance( geodesicRoutePoints.get( 0 ), geodesicRoutePoints.get( lastPointIdx ) ) ;

				return geodesicDistanceToLastPoint[fromIdx]			// Head section.
					 + geodesicDistanceFromFirstPointToLastPoint	// Wrap around route shunt.
				     + geodesicDistanceFromFirstPoint[toIdx]		// Tail section.
				     ;

			case 0 :	// <from> and <to> are the same point.
				return 0.0 ;

			case 1 :	// normal case.
			default:
				return geodesicDistanceFromFirstPoint[toIdx] - geodesicDistanceFromFirstPoint[fromIdx] ;
		}
	}


	/**
	 * Calculates the geodesic length of the 3D line that joins this route's waypoints in sequence.
	 *
	 * @return 		this route's path geodesic length (meters).
	 *
	 * @author 		Afonso Santos
	 */
	public double
	length( )
	{
		return geodesicDistanceToLastPoint[0] ;
	}
}
