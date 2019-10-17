package com.poc.graphql.springgraphql.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.poc.graphql.springgraphql.entity.Person;
import com.poc.graphql.springgraphql.entity.Phone;
import com.poc.graphql.springgraphql.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonResolver implements GraphQLResolver<Person> {
  @Autowired
  PhoneRepository phoneRepository;

  public Iterable<Phone> phones(Person person) {
    return phoneRepository.findByPersonId(person.getId());
  }
}
