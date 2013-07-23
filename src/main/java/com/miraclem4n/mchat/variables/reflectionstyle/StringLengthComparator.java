package com.miraclem4n.mchat.variables.reflectionstyle;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String> {
	@Override
	 public int compare(String o1, String o2) {
	    return Integer.signum(o1.length() - o2.length());
	}
}