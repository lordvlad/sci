package com.lordvlad.units;

import com.lordvlad.quantities.Quantity;

public class Extended extends SI {
	
	public static interface Volume extends Quantity {};
	
	protected static SystemOfUnits INSTANCE = null;

	static SystemOfUnits getInstance() {
		if (INSTANCE == null) INSTANCE = new Extended();
		return INSTANCE;
	}
	
	public static final Unit<Volume> LITRE = addUnit("L", Volume.class);
 
}
