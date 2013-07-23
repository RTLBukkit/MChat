package com.miraclem4n.mchat.variables.reflectionstyle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Variable {
	String[] keys();
}