package com.lordvlad.math.numbers;

import java.util.Formattable;

public abstract class NumBase<T extends NumBase<T>> extends Number implements Formattable, Num<T> {
	private static final long serialVersionUID = -3391385078859427115L;

	/* (non-Javadoc)
	 * @see com.lordvlad.math.numbers.Num#plus(T)
	 */
	@Override
	public abstract T plus(T o);

	/* (non-Javadoc)
	 * @see com.lordvlad.math.numbers.Num#inverse()
	 */
	@Override
	public abstract T inverse();

	/* (non-Javadoc)
	 * @see com.lordvlad.math.numbers.Num#times(T)
	 */
	@Override
	public abstract T times(T o);

	/* (non-Javadoc)
	 * @see com.lordvlad.math.numbers.Num#opposite()
	 */
	@Override
	public abstract T opposite();

	/* (non-Javadoc)
	 * @see com.lordvlad.math.numbers.Num#minus(T)
	 */
	@Override
	public T minus(T o) {
		return plus(o.opposite());
	};

	/* (non-Javadoc)
	 * @see com.lordvlad.math.numbers.Num#over(T)
	 */
	@Override
	public T over(T o) {
		return times(o.inverse());
	}
}
