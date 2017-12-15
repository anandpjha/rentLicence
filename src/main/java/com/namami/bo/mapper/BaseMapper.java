package com.namami.bo.mapper;

public interface BaseMapper<D, E> {

	/**
	 * This method converts an object of type E to D
	 * @param object
	 * @return
	 */
	public D map(E object);

	/**
	 * This method converts an object of type D to E
	 * @param object
	 * @return
	 */
	public E reverseMap(D object);

}
