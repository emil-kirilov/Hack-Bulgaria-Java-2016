package openWeather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
	public static void main(String[] args) {
		String stringToParse = null;		
		try {
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + "Sofia" + "&appid=9ed81d9300f326bbd3f1ef06bb0f1207");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			stringToParse = openWeather.bufferedReaderToString(in);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(stringToParse);
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(stringToParse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
