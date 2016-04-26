package com.lordvlad.units;

import com.lordvlad.quantities.DryMass;

public class Bio extends SystemOfUnits {
	public static final Unit<DryMass> GRAM_DRY_WEIGHT = ExtendedSI.GRAM.symbol("gDW").as(DryMass.class);
}
