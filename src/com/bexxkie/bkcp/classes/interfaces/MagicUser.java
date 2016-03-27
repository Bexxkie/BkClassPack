package com.bexxkie.bkcp.classes.interfaces;

public abstract interface MagicUser 
{
	
	/**
	 * generate the HUD for displaying mana, currentSpell and manaRegen
	 */
	public abstract void showHud();
	/**
	 * return the players current manaLevel (0.0 - i.i)
	 * @return Double MANA
	 */
	public abstract Double getMana();
	/**
	 * set the players manaLevel (i.i)
	 * @param Double newMana
	 */
	public abstract void setMana(Double newMana);
	/**
	 * update the players manaLevel (i.i)
	 * @param int classID
	 */
	public abstract void refreshMana(final int classID);
	/**
	 * get the players currently selected Spell
	 * @return String[i]
	 */
	public abstract String currentSpell();
	/**
	 * Cycle through a String[] (spells)
	 */
	public abstract void cycleSpell();
	/**
	 * Does the player know a certain spell
	 * @param spellName as String
	 * @return boolean 
	 */
	public abstract boolean hasSpell(String spell);
	/**
	 * get a list of spells
	 */
	public abstract void getSpells();
	/**
	 * gets the users MagicLevel
	 * @return int 
	 */
	public abstract int getLevel();
	/**
	 * sets the users MagicLevel
	 */
	public abstract void setLevel();
	/**
	 * set users MagicBranch
	 * @param String branchName
	 */
	public abstract void setBranch(String args);
	/**
	 * save current classData (6000==5min)
	 */
	public abstract void saveAll();
	/**
	 * return the current magicBranch
	 */
	public abstract String getBranch();
	/**
	 * adds a new spell to the players spellList
	 * @param String <SpellToAdd>
	 */
	public abstract void addSpell(String sName);
	/**
	 * removes a spell from the players spellList
	 * @param String <SpellToremove>
	 */
	public abstract void remSpell(String sName);
	/**
	 * set the players cooldown for a certain spell
	 * @param String spell's ID(ie: fireball)
	 * @param int time in seconds
	 */
	public abstract void setCool(String spellID, int CoolTime);
	/**
	 * is the spell in cooldown
	 * @return boolean cooldown active?
	 */
	public abstract boolean getCool(String spellID);	
	/**
	 * stop all runnable withint <player> class
	 */
	public abstract void stop();
}
