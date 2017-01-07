import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class PropertyReader 
{
	public Properties prop = new Properties();
	String result = "";
	InputStream inputStream;
 
	public String getPropValues() throws IOException 
	{
 
		try 
		{

			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) 
			{
				prop.load(inputStream);
			}
			else 
			{
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			Date time = new Date(System.currentTimeMillis());
 
			// get the property value and print it out
			String user = prop.getProperty("API_AI_CLIENT_ACCESS_KEY");
			String company1 = prop.getProperty("API_AI_BASE_URL");
			String company2 = prop.getProperty("CSI_REST_API_URL");
			String company3 = prop.getProperty("BAN");
 
			result = "Company List = " + company1 + ", " + company2 + ", " + company3;
			System.out.println(result + "\nProgram Ran on " + time + " by user=" + user);
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e);
		}
		finally 
		{
			inputStream.close();
		}
		return result;
	}
}