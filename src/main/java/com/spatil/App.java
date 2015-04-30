package com.spatil;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spatil.person.model.Person;
import com.spatil.person.service.PersonService;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"spring/config/spring-config.xml");
		PersonService personService = appContext.getBean("personService",
				PersonService.class);

		Person person = new Person();
		person.setName("sp");
		person.setEmail("sunil@gmail.com");

		personService.addPerson(person);
		System.out.println("Person Saved Successfully...");
		
		Person p = personService.findPersonById(2);
		p.setName("newname");
		
		personService.updatePerson(p);
		System.out.println("Person Information is updated :"+p.getName());
		

		
		System.out.println("***************** All Persons **************");
		for(Person per : personService.getAllPersons())
			System.out.println(per);

		p = personService.findPersonByName("sunil");
		System.out.println("By Name:"+p);
		
		
	}
}
