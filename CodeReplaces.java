package com.moofMonkey;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CodeReplaces {
	BADELSE (
		"(\\})" +
		"(?:\\s+)?" +
		"(else)" +
		"(?:\\s+)?" +
		"(\\{)" +
		"(?:\\s+)?" +// \s Matches the whitespace. Equivalent to [\t\n\r\f].
		"(\\})"
		,
		"$1"
	),
	;


	String from, to;
	private CodeReplaces(String _from, String _to) {
		from = _from;
		to = _to;
	}
	
	public static String replace(String s) {
		for(CodeReplaces r : CodeReplaces.values())
			if(checkWithRegExp(s, r.from))
				s = s.replaceAll(r.from, r.to);
		
		
		return s;
	}
	
	public static boolean checkWithRegExp(String s, String regexp){  
		Pattern p = Pattern.compile(regexp);  
		Matcher m = p.matcher(s);  
		return m.find();  
	}  
}
