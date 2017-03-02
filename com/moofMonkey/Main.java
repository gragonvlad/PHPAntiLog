package com.moofMonkey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.stream.Stream;

class Main {
	public static void main(String[] args) throws Throwable {
		File f = new File("in.php");
		File f2 = new File("out.php");
		try (
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f2)));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			OutputStreamWriter baosOut = new OutputStreamWriter(baos);
		) {
			Stream<String> lines = br.lines();
			lines.forEach((s)->{
				try {
					baosOut.write(Replaces.replaceFunc(s + "\n"));
					baosOut.flush();
				} catch(Throwable t) {}
			});
			
			String result = baos.toString();
			result = CodeReplaces.replace(result);
			
			bw.write(result);
		}
	}
}
