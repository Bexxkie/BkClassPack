package com.bexxkie.bkcp.util;

import java.util.Random;

public class RandInt 
{
	/**
	 * Generates a random 'integer' within the given parameters
	 * @param min integer
	 * @param max integer
	 * @return integer
	 */
	public static int randInt(int min, int max) 
	{
		Random rand = new Random();
		int iRandom = rand.nextInt((max - min) + 1) + min;
		return iRandom;
	}
	/**
	 * Generates a random 'double' within the given parameters
	 * @param min double
	 * @param max double
	 * @return double
	 */
	public static double randDouble(double min, double max)
	{
		Random rand = new Random();
		double dRandom = min + (max - min) * rand.nextDouble();
		return dRandom;
	}
	/**
	 * Generates a random 'float' within the given parameters
	 * @param min float
	 * @param max float
	 * @return float
	 */
	public static float randFloat(float min, float max)
	{
		Random rand = new Random();
		float fRandom = min + (max - min) * rand.nextFloat();
		return fRandom;
	}
}
