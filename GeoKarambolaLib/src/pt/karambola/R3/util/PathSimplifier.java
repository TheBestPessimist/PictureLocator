/*
 * PathSimplifier.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */

package pt.karambola.R3.util;

import java.util.ArrayList;
import java.util.List;

import pt.karambola.R3.R3;

public class
PathSimplifier
{
	private static
	PathSegmentError
	computeSegmentError(final R3[] pathPoints, final int firstIdx, final int lastIdx )
	{
		PathSegmentError segmentError = new PathSegmentError( firstIdx, lastIdx ) ;

		final R3     firstPoint    = pathPoints[firstIdx] ;
		final R3     lastPoint	   = pathPoints[lastIdx] ;
		final R3     segment       = R3.sub( lastPoint, firstPoint ) ;
		final double segmentLength = R3.modulus( segment ) ;

		for (int pointIdx = firstIdx + 1  ;  pointIdx < lastIdx  ;  ++pointIdx )
		{
			final double[] distanceToSegmentResults = R3.distanceToSegment( pathPoints[pointIdx], firstPoint, lastPoint, segment, segmentLength ) ;
			final double   pointErr                 = distanceToSegmentResults[0] ;

			// Retain which path point is the farthest from the segment.
			if (pointErr > segmentError.maxError)
			{
				segmentError.maxError    	  = pointErr ;
				segmentError.maxErrorPointIdx = pointIdx ;
			}
		}

		return segmentError ;
	}


	/**
	 * Finds the path with the minimum amount of points for which none of the discarded points from the original path is more than <b>maxErr</b> distance away.
	 * The simplified path will not have more than <b>maxPoints</b> points even if the <b>maxErr</b> condition cannot be honored.
	 *
	 * @param pathPoints    vector of Cartesian points that define a path in 3D space.
	 * @param fromIdx    	index of the path point where the section to be simplified begins.
	 * @param toIdx	    	index of the path point where the section to be simplified ends.
	 * @param maxErr    	maximum allowed distance error.
	 * @param maxPoints 	maximum number of points of simplified path.
	 *
	 * @return PathSimplifierResult object with the indexes of the points that form the simplified path, worst error of the solution.
	 *
	 * @author Afonso Santos
	 */
	public static
	PathSimplifierResult
	simplifySection( final R3[] pathPoints, final int fromIdx, final int toIdx, final int maxPoints, final double maxErr )
	{
		final List<PathSegmentError> segmentCandidates = new ArrayList<>( ) ;
		PathSegmentError worstSegment = computeSegmentError( pathPoints, fromIdx, toIdx ) ;
		segmentCandidates.add( worstSegment ) ;

		final int maxSegments = maxPoints - 1 ;

		while (segmentCandidates.size() < maxSegments  &&  worstSegment.maxError > maxErr)
		{
			worstSegment = null ;

			// Determine which is the section with the worst error.
			for (PathSegmentError segment: segmentCandidates)
				if (worstSegment == null  ||  segment.maxError > worstSegment.maxError)
					worstSegment = segment ;

			if (worstSegment.maxError == 0.0)
				break ;							// No more need to loop.

			// Split the worst segment in 2 new sub-segments, split occurs where the worst offender (most distant) point is.
			segmentCandidates.remove( worstSegment ) ;
			segmentCandidates.add( computeSegmentError( pathPoints, worstSegment.firstPointIdx   , worstSegment.maxErrorPointIdx ) ) ;
			segmentCandidates.add( computeSegmentError( pathPoints, worstSegment.maxErrorPointIdx, worstSegment.lastPointIdx     ) ) ;
		}

		final boolean[]	isSegmentTipPoint	= new boolean[pathPoints.length] ;

		for (PathSegmentError segment: segmentCandidates)
			isSegmentTipPoint[segment.firstPointIdx] = isSegmentTipPoint[segment.lastPointIdx] = true ;

		final int[] pointsIdxs = new int[segmentCandidates.size() + 1] ;
		int   resultItemIdx = 0 ;

		for (int pointIdx = fromIdx  ;  pointIdx <= toIdx  ;  ++pointIdx )
			if (isSegmentTipPoint[pointIdx])
				pointsIdxs[resultItemIdx++] = pointIdx ;

		return new PathSimplifierResult( maxPoints, maxErr, pointsIdxs, worstSegment.maxError ) ;
	}


	/**
	 * Finds the path with the minimum amount of points for which none of the discarded points from the original path is more than <b>maxErr</b> distance away.
	 * The simplified path will not have more than <b>maxPoints</b> points even if the <b>maxErr</b> condition cannot be honored.
	 *
	 * @param pathPoints    vector of Cartesian points that define a path in 3D space.
	 * @param maxPoints 	maximum number of points of simplified path.
	 * @param maxErr    	maximum allowed distance error.
	 *
	 * @return PathSimplifierResult object with the indexes of the points that form the simplified path, worst error of the solution.
	 *
	 * @author Afonso Santos
	 */
	public static
	PathSimplifierResult
	simplify( final R3[] pathPoints, final int maxPoints, final double maxErr )
	{
		return simplifySection( pathPoints, 0, pathPoints.length - 1, maxPoints, maxErr ) ;
	}
}
