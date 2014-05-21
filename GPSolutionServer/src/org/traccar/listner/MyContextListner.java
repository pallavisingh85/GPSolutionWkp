package org.traccar.listner;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.traccar.ServerManager;
import org.traccar.helper.Log;

public class MyContextListner implements ServletContextListener {

	public static final ServerManager service = new ServerManager();
	 
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Shutting down server...");
		Log.info("Shutting down server...");
        service.stop();
        System.out.println("Stopped server...");   
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		Locale.setDefault(Locale.ENGLISH);
		ServletContext context = arg0.getServletContext();
		String configFile = context.getRealPath("/WEB-INF/traccar.cfg");
       // String configFile = "/WebContent/WEB-INF/traccar.cfg";
        
        try {
        	System.out.println("Initailizing Server");
			service.init(configFile);
			System.out.println("Starting server...");
			 Log.info("Starting server...");
		        Log.logSystemInfo();
		        service.start();
		        System.out.println("Started server...");   
		} catch (Exception e) {
			System.out.println("Could not init server");
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

       
        
		
	}

}
