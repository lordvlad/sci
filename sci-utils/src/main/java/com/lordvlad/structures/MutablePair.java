package com.lordvlad.structures;

public class MutablePair<A, B> extends Pair<A, B> {

	private static final long serialVersionUID = 1217461445125231119L;
	public MutablePair(A a, B b) {
		super(a, b);
	}

	public void setA(A a){this.a = a;}
	public void setB(B b){this.b = b;}
}
