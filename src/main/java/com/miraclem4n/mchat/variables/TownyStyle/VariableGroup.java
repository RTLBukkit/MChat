package com.miraclem4n.mchat.variables.TownyStyle;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.miraclem4n.mchat.util.MessageUtil;

abstract class VariableGroup {

	Plugin plugin;

	// whether the plugins that are required are loaded.
	abstract boolean canRegister();

	// takes a collection to add the variables to, returns whether it was
	// successful.
	public boolean register(VariableGroup vg, Map<String, Method> results) {
		if(!canRegister())return false;
		try{
		for (Method method : vg.getClass().getDeclaredMethods()) {
			Annotation[] anno = method.getAnnotations();
			for (Annotation var : anno) {
				if (Variable.class == var.annotationType()) {
					String[] keys = ((Variable)var).keys();
					for (String k : keys) {
						results.put(k, method);
					}
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	Boolean setupPlugin(String pluginName) {
		Plugin plugin = Bukkit.getServer().getPluginManager()
				.getPlugin(pluginName);

		if (plugin != null) {
			MessageUtil.logFormatted("<Plugin> "
					+ plugin.getDescription().getName() + " v"
					+ plugin.getDescription().getVersion() + " hooked!.");
			return true;
		}

		return false;
	}

	public abstract boolean register(Map<String, Method> results);

	public String replaceVars(String format, CharSequence prefix, Map<String, Method> map, Player p) {
		for (Entry<String, Method> entry : map.entrySet()) {
			if (format.contains(prefix+entry.getKey())) {
				String value = "";
				try {
					value = (String) entry.getValue().invoke(null, p);
				} catch (Exception e){
					MessageUtil.logFormatted("Error whilst retrieving variable: "+ entry.getKey());
					e.printStackTrace();
				}
				format = format.replace(entry.getKey(),prefix + value);
			}
		}

		return format;
	}
}
