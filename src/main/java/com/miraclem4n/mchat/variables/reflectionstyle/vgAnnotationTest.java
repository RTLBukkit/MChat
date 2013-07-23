package com.miraclem4n.mchat.variables.reflectionstyle;

import java.lang.reflect.Method;
import java.util.Map;

import org.bukkit.entity.Player;

public class vgAnnotationTest extends VariableGroup{

	@Variable(keys={"tes"})
	static String test4(Player p){
		return p.getName()+"tes";
	}	
	
	@Variable(keys={"test1","t1"})
	static String test(Player p){
		return p.getName()+"test1";
	}
	@Variable(keys={"test2","t2"})
	static String test2(Player p){
		return p.getName()+"test2";
	}
	
	@Variable(keys={"te"})
	static String test3(Player p){
		return p.getName()+"te";
	}

	@Override
	boolean canRegister() {
		return true;
	}

	@Override
	public boolean register(Map<String, Method> results) {
		return super.register(this, results);
	}	
		
}
