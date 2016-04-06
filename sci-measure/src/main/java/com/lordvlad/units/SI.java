package com.lordvlad.units;

import com.lordvlad.quantities.AmountOfSubstance;
import com.lordvlad.quantities.ElectricCurrent;
import com.lordvlad.quantities.Length;
import com.lordvlad.quantities.LuminousIntensity;
import com.lordvlad.quantities.Mass;
import com.lordvlad.quantities.Quantity;
import com.lordvlad.quantities.ThermodynamicTemperature;
import com.lordvlad.quantities.Time;

public class SI extends SystemOfUnits {
	protected static SystemOfUnits INSTANCE = null;

	static SystemOfUnits getInstance() {
		if (INSTANCE == null) INSTANCE = new SI();
		return INSTANCE;
	}

	public static final Unit<Length> METRE = addUnit("m", Length.class);
	public static final Unit<Time> SECOND = addUnit("s", Time.class, "\"");
	public static final Unit<ElectricCurrent> AMPERE = addUnit("A", ElectricCurrent.class);
	public static final Unit<ThermodynamicTemperature> KELVIN = addUnit("K", ThermodynamicTemperature.class);
	public static final Unit<AmountOfSubstance> MOLE = addUnit("mol", AmountOfSubstance.class);
	public static final Unit<LuminousIntensity> CANDELA = addUnit("cd", LuminousIntensity.class);
	public static final Unit<Mass> KILOGRAM = MetricScale.KILO(addUnit("g", Mass.class));
	
	public static final Unit<Time> MINUTE = addUnit(SECOND.scale("min", 60), Time.class, "'");
	public static final Unit<Time> HOUR = addUnit(MINUTE.scale("h", 60), Time.class);
	public static final Unit<Time> DAY = addUnit(HOUR.scale("d", 24), Time.class);
	

	protected static <Q extends Quantity> Unit<Q> addUnit(String name, Class<Q> quantity, String... aliases) {
		return getInstance().addUnit(quantity, new BaseUnit<Q>(name), aliases);
	}
	
	protected static <Q extends Quantity> Unit<Q> addUnit(Unit<Q> unit, Class<Q> quantity, String... aliases) {
		return getInstance().addUnit(quantity, unit, aliases);
	}
}
