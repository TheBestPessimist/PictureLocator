/*
 * GenericPointBiPredicate_Similar.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.predicate;

import pt.karambola.commons.collections.BiPredicate;
import pt.karambola.gpx.beans.GenericPoint;
import pt.karambola.commons.util.NullSafeComparator;

public class
GenericPointBiPredicate_Similar
extends		GenericPointBiPredicate_Overlapping
implements BiPredicate<GenericPoint,GenericPoint>
{
	public
	GenericPointBiPredicate_Similar( double proximityToleranceMtrs )
	{
		super( proximityToleranceMtrs ) ;
	}

	@Override
	public boolean
	test(GenericPoint gpt1, GenericPoint gpt2)
	{
		return gpt1 != gpt2
			&& NullSafeComparator.equals( gpt1.getName(), gpt2.getName() )
		    && NullSafeComparator.equals( gpt1.getType(), gpt2.getType() )
		    && super.test( gpt1, gpt2 )
		    ;
	}
}
