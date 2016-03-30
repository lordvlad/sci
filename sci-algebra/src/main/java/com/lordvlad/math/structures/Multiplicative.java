package com.lordvlad.math.structures;

public interface Multiplicative<G> {
	G times(G that);
	G inverse();
}
