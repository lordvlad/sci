package com.lordvlad.units;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.util.HashMap;

/**
 * A Facade for simple access to the units package. 
 * 
 * @author reusch
 */
public class Units {
	
	private static class Index<S extends SystemOfUnits> {
		private final HashMap<Unit<?>, String> units = new HashMap<>();
		private final HashMap<String, Unit<?>> names = new HashMap<>();
		Index(Class<S> system) {
			for (Field f : system.getDeclaredFields()) {
				if (Modifier.isStatic(f.getModifiers())) {
					if (Unit.class.isAssignableFrom(f.getType())) {
						try {
							Unit<?> u= (Unit<?>) f.get(null);
							units.put(u, u.getSymbol());
							names.put(u.getSymbol(), u);
							for (String a : u.getAliases()) {
								names.put(a, u);
							}
						} catch (IllegalArgumentException | IllegalAccessException e) {
							// ignore
						}
					}
				}
			}
		}
		public Unit<?> get(String symbol) {
			return names.get(symbol);
		}
		public String get(Unit<?> unit) {
			return units.get(unit);
		}
	}

	private static final HashMap<Class<?>, Index<?>> indices = new HashMap<>();
	
	public static UnitFormat getFormat() {
		return UnitFormat.getInstance();
	}
	
	public static Unit<?> parse(String s) throws ParseException {
		return (Unit<?>) getFormat().parseObject(s);
	}
	
	public static String format(Unit<?> u) {
		return getFormat().format(u);
	}
	
	static String getName(Class<? extends SystemOfUnits> system, Unit<?> unit) {
		if (!indices.containsKey(system)) {
			indexSystem(system);
		}
		return indices.get(system).get(unit);
	}

	static Unit<?> getUnit(Class<? extends SystemOfUnits> system, String symbol) {
		if (!indices.containsKey(system)) {
			indexSystem(system);
		}
		return indices.get(system).get(symbol);
	}
	
	private static <S extends SystemOfUnits>void indexSystem(Class<S> system) {
		indices.put(system, new Index<S>(system));		
	}

	@SuppressWarnings("unchecked")
	@SafeVarargs
	static String getName(Unit<?> obj, Class<? extends SystemOfUnits>... systems) {
		String name;
		for (Object s: systems) {
			if ((name = getName((Class<? extends SystemOfUnits>) s, obj))!=null)
				return name;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@SafeVarargs
	static Unit<?> getUnit(String string, Class<? extends SystemOfUnits>... systems) {
		Unit<?> u;
		for (Object s:systems) {
			if ((u = getUnit((Class<? extends SystemOfUnits>) s, string)) != null) {
				return u;
			}
		}
		return null;
	}
}
