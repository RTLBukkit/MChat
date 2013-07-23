package com.miraclem4n.mchat.variables.reflectionstyle;

import java.lang.reflect.Method;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import uk.org.whoami.geoip.GeoIPLookup;

import com.maxmind.geoip.Country;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.regionName;
import uk.org.whoami.geoip.GeoIPTools;

public class vgGeoIP extends VariableGroup {

	private static GeoIPLookup getGeoIPLookup() {
		Plugin pl = Bukkit.getServer().getPluginManager()
				.getPlugin("GeoIPTools");
		if (pl != null) {
			return ((GeoIPTools) pl).getGeoIPLookup();
		} else {
			return null;
		}
	}

	static Country getGeoCountry(Player p) {
		return getGeoIPLookup().getCountry(p.getAddress().getAddress());
	}

	static Location getGeoLocation(Player p) {
		return getGeoIPLookup().getLocation(p.getAddress().getAddress());
	}

	@Variable(keys = "geoCountry")
	static String getGeoCountryString(Player p) {
		return getGeoCountry(p).getName();
	}

	@Variable(keys = "geoCountryCode")
	static String getGeoCountryCode(Player p) {
		return getGeoCountry(p).getCode();
	}

	@Variable(keys = "geoRegion")
	static String getGeoRegion(Player p) {
		return regionName.regionNameByCode(getGeoCountryCode(p),
				getGeoLocation(p).region);
	}

	@Variable(keys = "geoCity")
	static String getGeoCity(Player p) {
		return getGeoLocation(p).city;
	}

	@Override
	public boolean register(Map<String, Method> results) {
		if (!canRegister())
			return false;
		return super.register(this, results);
	}

	@Override
	boolean canRegister() {
		boolean retval = setupPlugin("GeoIPTools");
		return retval;
	}
}
