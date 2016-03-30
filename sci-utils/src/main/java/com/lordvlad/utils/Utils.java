package com.lordvlad.utils;

public class Utils {
	
	/**
	 * Returns the first argument that is not null.
	 * 
	 * @param obj the objects to pick one from
	 * @return {@code null} if all arguments are null or no arguments are provided.
	 */
	public static Object firstNonNull(Object...obj){
		for (Object o: obj) if (o != null) return o;
		return null;
	}

	public static boolean equals(double d, double e) {
		return Math.abs(d-e) < Constants.EPSILON;
	}
}
