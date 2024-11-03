package com.library;

//import java.util.Locale;
import com.github.javafaker.Faker;

public class FakerDataFactory {

	private static final Faker faker = new Faker(); 
	
	private FakerDataFactory() {
		 
	}

	public static String getCompanyName() {
		return faker.company().name().replaceAll("[^a-zA-Z ]", "");
	}

	public static String getUrl() {
		return faker.company().url();
	}

	public static String getAddress() {
		return faker.address().streetAddress();
	}
	
	public static String getCity() {
		return faker.address().city();
	}

	public static String getCountry() {
		return faker.country().toString();
	}

	public static String getUserName() {
		return faker.name().username();
	}
	
	
	public static String getFirstName() {
		return faker.name().firstName();
	}
	
	public static String getLastName() {
		return faker.name().lastName();
	}
	
	public static String getMiddleName() {
		return faker.name().nameWithMiddle();
	}
	
	public static String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public static String getContactNumber() {
		return faker.phoneNumber().cellPhone();
	}

	public static String getBankAccountNumber() {
		return ""+faker.number().randomNumber(8, false);
	}
	
	public static String getRandomNumber() {
		return ""+faker.number().randomNumber(9, false);
	}
	
	public static String getSwiftCode() {
		return ""+faker.number().randomNumber(4, false);
	}
	public static String getTaxNumber() {
		return ""+faker.number().randomNumber(7, false);
	}
	
	public static String getRating() {
		return faker.options().option("Hot","Warm", "Cold");
	}
	
	public static String getSalutation() {
		return faker.options().option("Mr.","Ms.", "Mrs.", "Dr.", "Prof.");
	}
	
	public static String getTitle() {
		return faker.options().option("Mr","Ms", "Mrs");
	}
	
	public static String getGender() {
		return faker.options().option("Male","Female");
	}
	
	public static String getPassword() {
		return faker.regexify("[A-Za-z0-9]{8}");
	}

}
