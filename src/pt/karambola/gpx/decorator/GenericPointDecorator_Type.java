/*
 * GenericPointDecorator_Type.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.commons.util.StringDecorator;
import pt.karambola.gpx.beans.GenericPoint;

public class
GenericPointDecorator_Type
	implements StringDecorator<GenericPoint>
{
	@Override
	public
	String
	getStringDecoration( GenericPoint gpt )
	{
		return (gpt.getType() != null) ? gpt.getType() : "?" ;
	}
}
