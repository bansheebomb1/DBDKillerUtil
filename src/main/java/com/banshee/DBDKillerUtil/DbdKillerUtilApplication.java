package com.banshee.DBDKillerUtil;




import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.banshee.DBDKillerUtil.form.KillerInfoForm;


@SpringBootApplication
public class DbdKillerUtilApplication implements CommandLineRunner{
	private static final Logger logger = Logger.getLogger(DbdKillerUtilApplication.class.getName());
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(DbdKillerUtilApplication.class);
		builder.headless(false).run(args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		StringBuilder sb =  new StringBuilder();
		sb.append("CREDIT: \n"
				+ "Wanted to make sure to mention special credit to Alex Baker with his jnativehook project for providing a "
				+ "\nwonderful interface "
				+ "I stumbled upon and decided to implement in my project "
				+ "to provide a global keyboard and mouse listener for Java.  "
				+ "\nPlease checkout his repo here: \n"
				+ "https://github.com/kwhat/jnativehook \n"
				+ "and provide any support you may be able to provide.  Additionally, this tool is free and will remain so enjoy! ");
				
		logger.log(Level.SEVERE, sb.toString());
		KillerInfoForm kif = KillerInfoForm.getInstance();
		logger.log(Level.INFO,"KillerInfoForm is visible: " + Boolean.valueOf((kif != null ? kif.isVisible() : false)));
	}
}
