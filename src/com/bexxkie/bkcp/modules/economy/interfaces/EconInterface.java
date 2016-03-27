package com.bexxkie.bkcp.modules.economy.interfaces;

public abstract interface EconInterface 
{
	/**
	 * get the current amount of x currency
	 * @param type currency to read [gold, gem]
	 * @return int currency
	 */
	public int getCur(String type);
	/**
	 * s
	 * @param type
	 * @param amount
	 */
	public void setCur(String type, int amount);
	public String hudCur();
}
