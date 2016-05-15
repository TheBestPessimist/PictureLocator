/*
 * GenericPointComparator_Type.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.comparator;

import java.util.Comparator;

import pt.karambola.commons.util.NullSafeComparator;
import pt.karambola.gpx.beans.GenericPoint;

public class
GenericPointComparator_Type
	implements Comparator<GenericPoint>
{
	@Override
	public int
	compare( GenericPoint gpt1, GenericPoint gpt2 )
	{
		if (gpt1 == gpt2)	return  0 ;
		if (gpt1 == null)	return -1 ;
		if (gpt2 == null)  	return  1 ;

		return NullSafeComparator.compare( gpt1.getType( ), gpt2.getType( ) ) ;
	}
}
