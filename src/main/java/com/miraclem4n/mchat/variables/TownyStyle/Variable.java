package com.miraclem4n.mchat.variables.TownyStyle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Variable {
	String[] keys();
}