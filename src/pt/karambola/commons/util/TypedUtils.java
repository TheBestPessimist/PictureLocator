/*
 * TypedUtils.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.commons.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class
TypedUtils
{
	public static <T extends Typed>
	List<String>
	getTypes( final Iterable<T> items )
	{
		List<String> types = new ArrayList<>( ) ;

		if (items != null)
			for (Typed item: items)
			{
				String type = item.getType( ) ;

				if (type != null  &&  !types.contains( type ))
					types.add( type ) ;
			}

		Collections.sort( types ) ;

		return types ;
	}
}
