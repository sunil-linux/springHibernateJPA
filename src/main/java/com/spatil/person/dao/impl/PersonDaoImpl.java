package com.spatil.person.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spatil.person.dao.PersonDao;
import com.spatil.person.model.Person;

@Repository("personDao")
@Transactional(propagation = Propagation.REQUIRED)
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void savePerson(Person person) {
		entityManager.persist(person);
	}

	@Override
	public void updatePerson(Person person) {
		entityManager.merge(person);
	}

	@Override
	public void deletePerson(Integer personId) {
		entityManager.remove(personId);
	}

	@Override
	public Person findPersonById(Integer personId) {
		return (Person)entityManager.find(Person.class, personId);		
	}

	@Override
	public Person findPersonByName(String personName) {
		final String FIND_PERSON_BY_NAME_QUERY = "select p from Person p where p.name=:name";
		
		Query query = entityManager.createQuery(FIND_PERSON_BY_NAME_QUERY);
		query.setParameter("name", personName);
		
		return (Person)query.getResultList().get(0);		
	}

	@Override
	public List<Person> getAllPersons() {

		final String SELECT_QUERY = "select p from Person p";

		Query query = entityManager.createQuery(SELECT_QUERY);
		return (List<Person>) query.getResultList();
	}

}
