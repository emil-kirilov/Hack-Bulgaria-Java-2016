package openWeather;

import java.io.BufferedReader;
import java.io.IOException;

public class openWeather {
	static String bufferedReaderToString(BufferedReader in) {
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}