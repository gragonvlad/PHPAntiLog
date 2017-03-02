package com.moofMonkey;

public enum Replaces {
	ECHO(PHPGetRegexpForFunc("echo"), "$6"),
	DIE(PHPGetRegexpForFunc("die"), "$1$2\\(\\)$5$6$7"),
	ERROR_REPORTING(PHPGetRegexpForFunc("error_reporting"), "$1$2\\(0\\)$5$6$7"),
	;
	
	String from, to;
	private Replaces(String _from, String _to) {
		from = _from;
		to = _to;
	}
	
	private static String PHPGetRegexpForFunc(String func) {
		return
			"((?:\\s+)?)" + // Optional whitespace group 1
			"(" + func + ")" + // Function name, group 2
			"((?:\\s+)?)" + // Optional whitespace group 3
			"((?:\\()?.*(?:\\))?)" + // Function arguments, group 4
			"(\\;)" + // Ending group 5
			"(.*)" + // Ending group 6
			"((?:\\r)?\\n)"; // New-line group 7
	}
	
	public static String replaceFunc(String s) {
		for(Replaces r : Replaces.values())
			if(s.matches(r.from))
				s = s.replaceAll(r.from, r.to);
		
		return s;
	}
}
