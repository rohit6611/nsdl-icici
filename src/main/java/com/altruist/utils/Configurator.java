package com.altruist.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configurator {
    
//    private Logger logger = Logger.getLogger(Configurator.class);
    private Properties props = null;       
  
    private static Configurator currObj = null;

     

    /**
     * constructor which initialise all property from resourse property file
     */
    private Configurator() {
    	init();
        
    }
    public static Configurator getInstance()
    {
    	if(currObj==null)
    		currObj = new Configurator();
    	return currObj;
    }

    private void init()
    {
    	 loadProps();
        
     }

    public String getProperty(String _key) {
        try {
            return props.getProperty(_key);
        } catch (Exception e) {
            return null;
        }
    }

    public void loadProps() {
        try {
            props = new Properties();
            props.load(this.getClass().getClassLoader().getResourceAsStream("."+File.separator + "application.properties"));
        } catch (Exception e) {
        	
        	 props = new Properties();
             try {
				props.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				 return;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				 return;
			}
            //e.printStackTrace();
          
        }
    }

   

    public Properties getProps() {
        return props;
    }  
    
  
	
		
}
