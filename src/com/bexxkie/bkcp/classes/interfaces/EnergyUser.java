package com.bexxkie.bkcp.classes.interfaces;

public abstract interface EnergyUser 
{
	/**
	 * generate the HUD for displaying energy, energyRegen
	 */
	public abstract void showHud();
	/**
	 * return the players current energyLevel (0.0 - i.i)
	 * @return Double ENERGY
	 */
	public abstract double getEnergy();
	/**
	 * set the players EnergyLevel (i.i)
	 * @param Double newEnergy
	 */
	public abstract void setEnergy(Double energy);
	/**
	 * update the players energyLevel (i.i)
	 * @param int classID
	 */
	public abstract void refreshEnergy(final int classID);
	/**
	 * return the current energyBranch
	 */
	public abstract String getBranch();
	/**
	 * set users energyBranch
	 * @param String branchName
	 */
	public abstract void setBranch(String args);
	/**
	 * gets the users euLevel (effects energyRegen)
	 * @param int level+1 
	 */
	public abstract void setLevel(int i);
	/**
	 * gets the users euLevel (effects energyRegen)
	 * @return int 
	 */
	public abstract int getLevel();
	/**
	 * stop all runnable withint <player> class
	 */
	public abstract void stop();
}
