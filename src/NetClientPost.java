

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NetClientPost 
{
	

	public static void main(String[] args) 
	{
		try 
		{
			PropertyReader propReader = new PropertyReader();
			propReader.getPropValues();
			String CSI_REST_URL = propReader.prop.getProperty("CSI_REST_API_URL");
			System.out.println(CSI_REST_URL);
			URL url = new URL(CSI_REST_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = "{\"ban\":\"1234567890\"}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
/*
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) 
			{
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			*/

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			Gson gson = new GsonBuilder().create();
            BanJson p = gson.fromJson(br, BanJson.class);
            System.out.println(p);
            
			/*
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) 
			{
				System.out.println(output);
				JsonParser parser = new JsonParser();
				JsonObject jObj = (JsonObject) parser.parse(output);

				List<String> keys = jObj.entrySet()
				    .stream()
				    .map(i -> i.getKey())
				    .collect(Collectors.toCollection(ArrayList::new));

				keys.forEach(System.out::println);
				
			}
*/
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

}