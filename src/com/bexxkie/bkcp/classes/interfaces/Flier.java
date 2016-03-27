package com.bexxkie.bkcp.classes.interfaces;


public abstract interface Flier 
{
	/**
	 * generate the HUD for displaying energy and energyRegen
	 */
	public abstract void showHud();
	/**
	 * return the players current energyLevel (0.0 - i.i)
	 * @return double ENERGY
	 */
	public abstract double getEnergy();
	/**
	 * set the players energyLevel (i.i)
	 * @param Double newEnergy
	 */
	public abstract void setEnergy(Double newEnergy);
	/**
	 * update the players energyLevel (i.i)
	 * @param int classID
	 */
	public abstract void refreshEnergy(final int classID);
	/**
	 * display 'creative style' flight time
	 * @param intCool time to fly
	 */
	public abstract void setCool(double intCool);
	public abstract double getCool();
	/**
	 * save classData
	 */
	public abstract void saveAll();
	/**
	 * set flightLevel
	 * @param i new Level
	 */
	public abstract void setLevel(int i);
	/**
	 * get flightLevel
	 * @return int flightLevel
	 */
	public abstract int getLevel();
	/**
	 * set Flight EXP
	 * @param int new flightExp
	 */
	public abstract void setFxp(int i);
	/**
	 * return FlightExp
	 * @return int currentFlightEXP
	 */
	public abstract int getFxp();
	/**
	 * activate flight time for 'creativeMode' flight
	 */
	public abstract void coolDown();
	/**
	 * stop all runnable withint <player> class
	 */
	public abstract void stop();
}
