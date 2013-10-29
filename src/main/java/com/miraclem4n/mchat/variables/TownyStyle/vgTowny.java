package com.miraclem4n.mchat.variables.TownyStyle;

import java.util.regex.Pattern;

import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.TownyFormatter;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;

public class vgTowny {

	static Resident getResident(Player p) {
		try {
			return TownyUniverse.getDataSource().getResident(p.getName());
		} catch (NotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	static Town getTown(Player p) {
		try {
			return getResident(p).getTown();
		} catch (NotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	static {

		RegisterManager<Void, String, Callable<Player>> rm = new RegisterManager<Void, String, Callable<Player>>() {

			@Override
			public Void register(String p, Callable<Player> c) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		rm.register(Pattern.quote("town"), new Callable<Player>() {

			@Override
			public String Call(Player p) {
				// TODO Auto-generated method stub
				return getTown(p).getName();
			}

		});
	}

	@Variable(keys = "townname")
	static String getTownyFormattedString(Player p) {
		return TownyFormatter.getFormattedTownName(getTown(p));
	}

	@Variable(keys = "townysurname")
	static String getResidentSurname(Player p) {
		return getResident(p).getSurname();
	}

	@Variable(keys = "townytitle")
	static String getResidentTitle(Player p) {
		return getResident(p).getTitle();
	}

	@Variable(keys = "townyresidentname")
	static String getResidentName(Player p) {
		return getResident(p).getFormattedName();
	}

	@Variable(keys = "townyprefix")
	static String getResidentPrefix(Player p) {
		Resident r = getResident(p);
		return r.hasTitle() ? r.getTitle() : TownyFormatter.getNamePrefix(r);
	}

	@Variable(keys = "townynameprefix")
	static String getResidentNamePrefix(Player p) {
		return TownyFormatter.getNamePrefix(getResident(p));
	}

	@Variable(keys = "townypostfix")
	static String getResidentPostfix(Player p) {
		Resident r = getResident(p);
		return r.hasSurname() ? r.getSurname() : TownyFormatter.getNamePostfix(r);
	}

	@Variable(keys = "townynamepostfix")
	static String getResidentNamePostfix(Player p) {
		return TownyFormatter.getNamePostfix(getResident(p));
	}

	static Nation getNation(Player p) {
		try {
			return getTown(p).getNation();
		} catch (NotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Variable(keys = "townynation")
	static String getNationString(Player p) {
		return getNation(p).getName();
	}

	@Variable(keys = "townynationname")
	static String getNationFormatted(Player p) {
		return getNation(p).getFormattedName();
	}

	@Variable(keys = "townynationtag")
	static String getNationTag(Player p) {
		return getNation(p).getTag();
	}

}
