package pt.karambola.commons.util;

import java.util.Date;


public class
NullSafeComparator
{
	public static
	int
	compare( String v1, String v2 )
	{
		if (v1 == v2)    return  0 ;
		if (v1 == null)  return -1 ;
		if (v2 == null)  return  1 ;

		return v1.compareTo( v2 ) ;
	}


	public static
	int
	compare( Integer v1, Integer v2 )
	{
		if (v1 == v2)    return  0 ;
		if (v1 == null)  return -1 ;
		if (v2 == null)  return  1 ;

		return v1.compareTo( v2 ) ;
	}


	public static
	int
	compare( Double v1, Double v2 )
	{
		if (v1 == v2)    return  0 ;
		if (v1 == null)  return -1 ;
		if (v2 == null)  return  1 ;

		return v1.compareTo( v2 ) ;
	}


	public static
	int
	compare( Date v1, Date v2 )
	{
		if (v1 == v2)    return  0 ;
		if (v1 == null)  return -1 ;
		if (v2 == null)  return  1 ;

		return v1.compareTo( v2 ) ;
	}


	public static
	boolean
	equals( String v1, String v2 )
	{
		if (v1 == v2)                    return true ;
		if (v1 == null  &&  v2 != null)  return false ;
		if (v1 != null  &&  v2 == null)  return false ;

		return v1.equals( v2 ) ;
	}
}