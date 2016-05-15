/*
 * RouteBiPredicate_Overlapping.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import java.util.List;

import pt.karambola.commons.collections.BiPredicate;
import pt.karambola.gpx.beans.Route;
import pt.karambola.gpx.beans.RoutePoint;

public class
RouteBiPredicate_Overlapping
implements BiPredicate<Route,Route>
{
	@Override
	public boolean
	test( Route rte1, Route rte2 )
	{
		if (rte1 == rte2)
			return false ;

		List<RoutePoint> rte1Pts = rte1.getRoutePoints() ;
		List<RoutePoint> rte2Pts = rte2.getRoutePoints() ;
		int rte1PtsSize = rte1Pts.size() ;
		int rte2PtsSize = rte2Pts.size() ;

		if (rte1PtsSize != rte2PtsSize)
			return false ;

		for (int rtePtIdx = 0  ;  rtePtIdx < rte1PtsSize  ;  ++rtePtIdx)
			if (!GenericPointConflict.OVERLAPPING.test( rte1Pts.get(rtePtIdx), rte2Pts.get(rtePtIdx) ))
				return false ;

		return true ;
	}
}
