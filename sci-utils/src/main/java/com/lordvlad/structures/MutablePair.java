package com.lordvlad.structures;

/**
 * A {@link Pair} where the members can be exchanged.
 * @author wre
 *
 * @param <A>
 * @param <B>
 */
public class MutablePair<A, B> extends Pair<A, B> {

	private static final long serialVersionUID = 1217461445125231119L;
	public MutablePair(A a, B b) {
		super(a, b);
	}

	public void setA(A a){this.a = a;}
	public void setB(B b){this.b = b;}
}
