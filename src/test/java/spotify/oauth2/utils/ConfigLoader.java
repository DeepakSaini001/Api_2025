package spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {
	
	private final Properties properties;
	//private static final ConfigLoader configLoader;
	
	private ConfigLoader() {
		properties= PropertyUtils.propertyLoader("/Users/deepaksaini/eclipse-workspace/API_2025/src/test/resources/config.properties");
		
	}

}
