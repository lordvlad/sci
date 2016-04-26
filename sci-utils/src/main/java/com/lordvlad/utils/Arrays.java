package com.lordvlad.utils;
/**
 * Methods helping to handle Arrays.
 * @author wre
 *
 */
public class Arrays {

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String[] push(String[] a, String ... b) {
		String[] c = new String[a.length+b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

}
