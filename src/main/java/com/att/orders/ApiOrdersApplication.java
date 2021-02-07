package com.att.orders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author pjonnalagadda
 *
 */
@SpringBootApplication
public class ApiOrdersApplication implements CommandLineRunner{

	public static AttOrdersDataStore attOrdersDataStore = new AttOrdersDataStore();
	
	public static void main(String[] args) {
		SpringApplication.run(ApiOrdersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		attOrdersDataStore.buildCatalogData();
		attOrdersDataStore.buildOrderData();
	}

}
