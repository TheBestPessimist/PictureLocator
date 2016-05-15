/*
 * GenericPointDecorator.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import pt.karambola.commons.util.StringDecorator;
import pt.karambola.gpx.beans.GenericPoint;


public class
GenericPointDecorator
{
	public static final StringDecorator<GenericPoint> TYPE	= new GenericPointDecorator_Type( ) ;
	public static final StringDecorator<GenericPoint> AGE	= new GenericPointDecorator_Age( ) ;
}
