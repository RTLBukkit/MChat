package com.miraclem4n.mchat.variables.reflectionstyle;

import java.lang.reflect.Method;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.herocraftonline.heroes.Heroes;
import com.herocraftonline.heroes.characters.Hero;
import com.herocraftonline.heroes.characters.classes.HeroClass;
import com.herocraftonline.heroes.util.Messaging;

import com.miraclem4n.mchat.types.config.LocaleType;

public class vgHeroes extends VariableGroup {

	@Variable(keys = { "HClass", "HC" })
	static String getHeroClassString(Player p) {
		return getHeroClass(p).getName();
	}

	@Variable(keys = { "HSecClass", "HSC" })
	static String getSecondaryClassString(Player p) {
		return getSecondaryClass(p).getName();
	}

	@Variable(keys = { "HLevel", "HL" })
	static String getHeroLevelString(Player p) {
		return String.valueOf(getHeroLevel(p));
	}

	static int getHeroLevel(Player p) {
		return getHero(p).getLevel();
	}

	@Variable(keys = { "HSecLevel", "HSL" })
	static String getSecondaryLevelString(Player p) {
		return String.valueOf(getSecondaryLevel(p));
	}

	static int getSecondaryLevel(Player p) {
		return getHero(p).getLevel(getSecondaryClass(p));
	}

	@Variable(keys = { "HExp", "HEx" })
	static String getHeroesExperienceString(Player p) {
		return String.valueOf(getHeroesExperience(p));
	}

	static double getHeroesExperience(Player p) {
		return getHero(p).getExperience(getHeroClass(p));
	}

	@Variable(keys = { "HEBar", "HEb" })
	static String getHeroesExperienceBar(Player p) {
		return Messaging.createExperienceBar(getHero(p), getHero(p)
				.getHeroClass());
	}

	@Variable(keys = { "HHBar", "HHB" })
	static String getHealthBar(Player p) {
		return Messaging.createHealthBar(getHealth(p), getHero(p).getPlayer()
				.getMaxHealth());
	}

	@Variable(keys = { "HHealth", "HH" })
	static String getHealthString(Player p) {
		return String.valueOf(getHealth(p));
	}

	static double getHealth(Player p) {
		return getHero(p).getPlayer().getHealth();
	}

	@Variable(keys = { "HMastered", "HMa" })
	static String getMastered(Player p) {
		return getHero(p).isMaster(getHeroClass(p))
				&& (getSecondaryClass(p) == null || getHero(p).isMaster(
						getSecondaryClass(p))) ? LocaleType.MESSAGE_HEROES_TRUE
				.getVal() : LocaleType.MESSAGE_HEROES_FALSE.getVal();
	}

	@Variable(keys = { "HMana", "HMn" })
	static String getManaString(Player p) {
		return String.valueOf(getMana(p));
	}

	static int getMana(Player p) {
		return getHero(p).getMana();
	}

	@Variable(keys = { "HMBar", "HMb" })
	static String getManaBar(Player p) {
		return Messaging.createManaBar(getHero(p).getMana(), getHero(p)
				.getMaxMana());
	}

	@Variable(keys = { "HParty", "HPa" })
	static String getParty(Player p) {
		return getHero(p).getParty().toString();
	}

	@Variable(keys = { "HSecExp", "HSEx" })
	static String getSecondaryExpString(Player p) {
		return String.valueOf(getSecondaryExp(p));
	}

	static double getSecondaryExp(Player p) {
		return getHero(p).getExperience(getSecondaryClass(p));
	}

	@Variable(keys = { "HSecEBar", "HSEb" })
	static String getSecondaryExpBar(Player p) {
		return Messaging.createExperienceBar(getHero(p), getSecondaryClass(p));
	}

	static Hero getHero(Player player) {
		return ((Heroes) Bukkit.getServer().getPluginManager()
				.getPlugin("Heroes")).getCharacterManager().getHero(player);
	}

	static HeroClass getHeroClass(Player p) {
		return getHero(p).getHeroClass();
	}

	static HeroClass getSecondaryClass(Player p) {
		return getHero(p).getSecondClass();
	}

	@Override
	public boolean canRegister() {
		boolean retval = setupPlugin("Heroes");
		return retval;

	}

	@Override
	public boolean register(Map<String, Method> results) {
		if (!canRegister())
			return false;
		return super.register(this, results);
	}

}
