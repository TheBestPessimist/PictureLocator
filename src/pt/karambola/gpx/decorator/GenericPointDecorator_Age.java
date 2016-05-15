/*
 * GenericPointDecorator_Age.java
 *
 * Copyright (c) 2016 Karambola. All rights reserved.
 */


package pt.karambola.gpx.decorator;

import java.util.Date;

import pt.karambola.commons.util.StringDecorator;
import pt.karambola.gpx.beans.GenericPoint;

public class
GenericPointDecorator_Age
implements StringDecorator<GenericPoint>
{
	private static final long MSINADAY = 1000 * 60 * 60 * 24 ;

	@Override
	public
	String
	getStringDecoration( GenericPoint gpt )
	{
		Date ptTime = gpt.getTime( ) ;
		if (ptTime == null )  return "?" ;

		Date now = new Date( ) ;
		Long ageInDays = (now.getTime() - ptTime.getTime()) / MSINADAY ;
		return ageInDays.toString( ) + (ageInDays == 1 ? " day" : " days") ;
	}
}
