package com.lordvlad.units;

import java.util.HashMap;
import java.util.Map;

import com.lordvlad.quantities.Quantity;

public class MetricScale {
	
	final static Map<String, Scale> prefixes = new HashMap<String, Scale>();
	final static Map<Double, Scale> scalings = new HashMap<Double, Scale>();

	private static Scale addScale(Scale scale) {
		prefixes.put(scale.prefix, scale);
		scalings.put(scale.scaling, scale);
		return scale;
	}

	protected static Scale addScale(String prefix, double scaling) {
		return addScale(new Scale(prefix, scaling));
	}

	public static final Scale EXA = addScale("E", 1e18);	
	public static final Scale PETA = addScale("P", 1e15);
	public static final Scale TERA = addScale("T", 1e12);
	public static final Scale GIGA = addScale("G", 1e9);
	public static final Scale MEGA = addScale("M", 1e6);
	public static final Scale KILO = addScale("k", 1e3);
	public static final Scale HEKTO = addScale("h", 1e2);
	public static final Scale DECA = addScale("da", 1e1);
	public static final Scale DECI = addScale("d", 1e-1);
	public static final Scale CENTI = addScale("c", 1e-2);
	public static final Scale MILLI = addScale("m", 1e-3);
	public static final Scale MICRO = addScale("µ", 1e-6);
	public static final Scale NANO = addScale("n", 1e-9);
	public static final Scale PICO = addScale("p", 1e-12);
	public static final Scale FEMTO = addScale("f", 1e-15);
	public static final Scale ATTO = addScale("a", 1e-18);

	public static final <Q extends Quantity> Unit<Q> EXA(Unit<Q> u) { return MetricScale.EXA.scale(u); }
	public static final <Q extends Quantity> Unit<Q> PETA(Unit<Q> u) { return PETA.scale(u); }
	public static final <Q extends Quantity> Unit<Q> TERA(Unit<Q> u) { return TERA.scale(u); }
	public static final <Q extends Quantity> Unit<Q> GIGA(Unit<Q> u) { return GIGA.scale(u); }
	public static final <Q extends Quantity> Unit<Q> MEGA(Unit<Q> u) { return MEGA.scale(u); }
	public static final <Q extends Quantity> Unit<Q> KILO(Unit<Q> u) { return KILO.scale(u); }
	public static final <Q extends Quantity> Unit<Q> HEKTO(Unit<Q> u) { return HEKTO.scale(u); }
	public static final <Q extends Quantity> Unit<Q> DECA(Unit<Q> u) { return DECA.scale(u); }
	public static final <Q extends Quantity> Unit<Q> DECI(Unit<Q> u) { return DECI.scale(u); }
	public static final <Q extends Quantity> Unit<Q> CENTI(Unit<Q> u) { return CENTI.scale(u); }
	public static final <Q extends Quantity> Unit<Q> MILLI(Unit<Q> u) { return MILLI.scale(u); }
	public static final <Q extends Quantity> Unit<Q> MICRO(Unit<Q> u) { return MICRO.scale(u); }
	public static final <Q extends Quantity> Unit<Q> NANO(Unit<Q> u) { return NANO.scale(u); }
	public static final <Q extends Quantity> Unit<Q> PICO(Unit<Q> u) { return PICO.scale(u); }
	public static final <Q extends Quantity> Unit<Q> FEMTO(Unit<Q> u) { return FEMTO.scale(u); }
	public static final <Q extends Quantity> Unit<Q> ATTO(Unit<Q> u) { return ATTO.scale(u); }
	
	
}
