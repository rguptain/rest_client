import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NetClientGet 
{
	public static void main(String[] args) throws IOException 
	{
		PropertyReader propReader = new PropertyReader();
		propReader.getPropValues();
		String CLIENT_ACCESS_KEY = propReader.prop.getProperty("API_AI_CLIENT_ACCESS_KEY");
		String BASE_URL = propReader.prop.getProperty("API_AI_BASE_URL");
		try 
		{
			System.out.println(CLIENT_ACCESS_KEY);
			System.out.println(BASE_URL);
			URL url = new URL(BASE_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestProperty("Authorization", "Bearer " + CLIENT_ACCESS_KEY);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) 
			{
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			JsonObject obj = convertFileToJSON (br);

			   JsonObject result = getJsonObject(obj, "result");
			   JsonObject fulfillment = getJsonObject(result, "fulfillment");
			   printValue(fulfillment, "speech");
			
			conn.disconnect();

		}
		catch (MalformedURLException e) 
		{

			e.printStackTrace();
		}
		catch (IOException e) 
		{

			e.printStackTrace();
			
		}

	}
	
	public static void printValue(JsonObject obj, String k)
	{
		 JsonElement je = obj.get(k);
		 System.out.println(je.toString());
	}

	public static JsonObject getJsonObject (JsonObject obj, String k)
	{
		JsonObject result = null;
		
		 Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
		   for (Map.Entry<String, JsonElement> entry: entries) 
		   {
		       String key = entry.getKey();
		       if (key.equalsIgnoreCase(k))
		       {
		    	   JsonElement je = entry.getValue();
		    	   result = je.getAsJsonObject();
		       }
		   }
		   return result;
	}

	public static void printKeys(JsonObject obj)
	{
		 Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
		   for (Map.Entry<String, JsonElement> entry: entries) 
		   {
		       String key = entry.getKey();
		       System.out.println(key);
		   }
	}
	
	public static JsonObject convertFileToJSON (BufferedReader bf)
	{

	        // Read from File to String
	        JsonObject jsonObject = new JsonObject();
	        
	        try 
	        {
	            JsonParser parser = new JsonParser();
	            JsonElement jsonElement = parser.parse(bf);
	            jsonObject = jsonElement.getAsJsonObject();
	        } 
	        catch (Exception ioe)
	        {
	        	System.out.println("Exception");
	        }
	        
	        
	        return jsonObject;
	    }

}