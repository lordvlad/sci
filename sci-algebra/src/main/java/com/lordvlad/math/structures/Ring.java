package com.lordvlad.math.structures;

public interface Ring<G> extends  Additive<G>{
	G times(G that);
}
