package com.lordvlad.units;

import static com.lordvlad.units.SI.METRE;
import static com.lordvlad.units.SI.SECOND;


import com.lordvlad.quantities.Acceleration;
import com.lordvlad.quantities.AngularVelocity;
import com.lordvlad.quantities.Area;
import com.lordvlad.quantities.Dimensionless;
import com.lordvlad.quantities.Mass;
import com.lordvlad.quantities.ThermodynamicTemperature;
import com.lordvlad.quantities.Time;
import com.lordvlad.quantities.Volume;

public class ExtendedSI extends SystemOfUnits {
	
	public static final Unit<Time> MINUTE = SECOND.scale(60).symbol("min").alias("'");
	public static final Unit<Time> HOUR = MINUTE.scale(60).symbol("h");
	public static final Unit<Time> DAY = HOUR.scale(24).symbol("d");
	
	public static final Unit<Volume> LITRE = MetricScale.DECI(SI.METRE).pow(3).symbol("L").as(Volume.class);
	public static final Unit<ThermodynamicTemperature> CELSIUS = SI.KELVIN.plus(-273.15).symbol("°C");
	public static final Unit<Mass> GRAM = SI.KILOGRAM.scale(1e-3).symbol("g");
	
	public static final Unit<Acceleration> METER_PER_SQ_SECOND = METRE.over(SECOND.pow(2)).as(Acceleration.class);
	public static final Unit<Area> SQUARE_METER = METRE.pow(2).as(Area.class);
	
	public static final Unit<Dimensionless> PERCENT = Unit.ONE.scale(1e-3).symbol("%");
	public static final Unit<AngularVelocity> ROTATIONS_PER_MINUTE = Unit.ONE.over(MINUTE).symbol("rpm").as(AngularVelocity.class);
}
