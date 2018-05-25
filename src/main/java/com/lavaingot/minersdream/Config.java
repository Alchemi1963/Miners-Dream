package com.lavaingot.minersdream;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.lavaingot.minersdream.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config {

	private static final String CATEGORY_GENERAL = "general";
	private static final String CATEGORY_ADVANCED = "advanced";
	private static final String CATEGORY_MULTI_TOOL = "multi_tool";
	
	//Configs
	public static Map<String, Integer> MATERIALCOLOURS = new HashMap<String, Integer>();
	
	public static void readConfig() {
		Configuration config = CommonProxy.config;
		try {
			
			config.load();
			initMultiToolConfig(config);
			
			Main.logger.log(Level.CONFIG, "Configurations loaded!");
			
		} catch (Exception e) {
			
			Main.logger.log(Level.WARNING, "Problem loading configurations!", e);
		} finally {
			
			if (config.hasChanged()) config.save();
		}
	}
	
	private static void initMultiToolConfig(Configuration config) {

		config.addCustomCategoryComment(CATEGORY_MULTI_TOOL, "Configuration for the Multi Tool");
		
		//Vanilla Tool Materials
		MATERIALCOLOURS.put("WOOD", new Color(94, 71, 27).getRGB());
		MATERIALCOLOURS.put("STONE", new Color((int)(0.515*255), (int)(0.515*255), (int)(0.515*255)).getRGB());
		MATERIALCOLOURS.put("IRON", new Color((int)(0.795*255), (int)(0.795*255), (int)(0.795*255)).getRGB());
		MATERIALCOLOURS.put("GOLD", new Color((int)(0.766*255), (int)(0.778*255), (int)(0.307*255)).getRGB());
		MATERIALCOLOURS.put("DIAMOND", new Color((int)(0.160*255), (int)(0.733*255), (int)(0.634*255)).getRGB());
		
		//Miners Dream Tool Materials
		MATERIALCOLOURS.put("ALUMINIUM", new Color((int)(0.819*255), (int)(0.819*255), (int)(0.817*255)).getRGB());
		MATERIALCOLOURS.put("BISMUTH", new Color((int)(0.107*255), (int)(0.161*255), (int)(0.187*255)).getRGB());
		MATERIALCOLOURS.put("CADMIUM", new Color((int)(0.449*255), (int)(0.448*255), (int)(0.459*255)).getRGB());
		MATERIALCOLOURS.put("COBALT", new Color((int)(0.190*255), (int)(0.657*255), (int)(0.794*255)).getRGB());
		MATERIALCOLOURS.put("COPPER", new Color((int)(0.846*255), (int)(0.517*255), (int)(0.253*255)).getRGB());
		MATERIALCOLOURS.put("PLATINUM", new Color((int)(0.656*255), (int)(0.692*255), (int)(0.784*255)).getRGB());
		MATERIALCOLOURS.put("POTASSIUM", new Color((int)(0.538*255), (int)(0.535*255), (int)(0.562*255)).getRGB());
		MATERIALCOLOURS.put("SILVER", new Color((int)(0.737*255), (int)(0.737*255), (int)(0.737*255)).getRGB());
		MATERIALCOLOURS.put("TIN", new Color((int)(0.469*255), (int)(0.471*255), (int)(0.455*255)).getRGB());
		MATERIALCOLOURS.put("TUNGSTEN", new Color((int)(0.491*255), (int)(0.455*255), (int)(0.390*255)).getRGB());
		MATERIALCOLOURS.put("URANIUM", new Color((int)(0.185*255), (int)(0.253*255), (int)(0.183*255)).getRGB());
		MATERIALCOLOURS.put("ZINC", new Color((int)(0.814*255), (int)(0.815*255), (int)(0.814*255)).getRGB());
		
		String[] keys = new String[MATERIALCOLOURS.size()];
		String[] values = new String[MATERIALCOLOURS.size()];
		
		int index = 0;
		for (Map.Entry<String, Integer> entry : MATERIALCOLOURS.entrySet()) {
			keys[index] = entry.getKey();
			int r = new Color(entry.getValue()).getRed();
			int g = new Color(entry.getValue()).getGreen();
			int b = new Color(entry.getValue()).getBlue();
			values[index] = "(r, g, b)".replaceAll("r", String.valueOf(r)).replaceAll("g", String.valueOf(g)).replaceAll("b", String.valueOf(b));
			index ++;
		}
		
		if (config.getCategory(CATEGORY_MULTI_TOOL).keySet().toArray().length > keys.length) keys = (String[]) config.getCategory(CATEGORY_MULTI_TOOL).keySet().toArray();
		
		index = 0;
		for (String key : keys) {
			index++;
			
			String defaultValue = "(0, 0, 0)";
			if (MATERIALCOLOURS.containsKey(key)) {
				int r = new Color(MATERIALCOLOURS.get(key)).getRed();
				int g = new Color(MATERIALCOLOURS.get(key)).getGreen();
				int b = new Color(MATERIALCOLOURS.get(key)).getBlue();
				defaultValue = "(r, g, b)".replaceAll("r", String.valueOf(r)).replaceAll("g", String.valueOf(g)).replaceAll("b", String.valueOf(b));
			}
			
			values[index] = config.get(CATEGORY_MULTI_TOOL, key, defaultValue).getString();
		}
		
		for (int i = 0; i>=MATERIALCOLOURS.size(); i++) {
			MATERIALCOLOURS.clear();
			try {
				MATERIALCOLOURS.put(keys[i], Color.decode(values[i]).getRGB());
			}
			catch (NumberFormatException e){
				MATERIALCOLOURS.put(keys[i], new Color(0,0,0).getRGB());
			}
		}
	}
}
