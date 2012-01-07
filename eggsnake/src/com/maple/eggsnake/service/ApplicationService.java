package com.maple.eggsnake.service;

import com.maple.eggsnake.logger.DefaultLogger;

public class ApplicationService {
	
	static Application application = null;
	static ApplicationService service = null;
	
	public static ApplicationService getInstance(){
		if(application == null)
			application = new DesktopApplication();
		if(service == null)
			service = new ApplicationService();
		return service;
	}
	
	public void exitGame() {
		DefaultLogger.getDefaultLogger().logWithSignature("ApplicationService",
				"exit");
		ApplicationService.application.exit();
	}
	public static void setApplication(com.maple.eggsnake.service.Application application){
		ApplicationService.application = application;
	}
}
