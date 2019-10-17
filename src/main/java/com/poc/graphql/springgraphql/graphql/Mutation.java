package com.poc.graphql.springgraphql.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.poc.graphql.springgraphql.entity.Person;
import com.poc.graphql.springgraphql.entity.Phone;
import com.poc.graphql.springgraphql.repository.PersonRepository;
import com.poc.graphql.springgraphql.repository.PhoneRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
  private final PersonRepository personRepository;
  private final PhoneRepository phoneRepository;

  public Mutation(PersonRepository personRepository, PhoneRepository phoneRepository) {
    this.personRepository = personRepository;
    this.phoneRepository = phoneRepository;
  }

  public Person addPerson(String name) {
    Person p = new Person();
    p.setName(name);

    this.personRepository.save(p);

    return p;
  }

  public Phone addPhoneToPerson(Long id, PhoneInput phoneInput) {
    Optional<Person> p = this.personRepository.findById(id);
    if (p.isPresent()) {
      Person person = p.get();

      Phone phone = new Phone();
      phone.setPerson(person);
      phone.setType(phoneInput.getType());
      phone.setPhone(phoneInput.getPhone());

      this.phoneRepository.save(phone);

      return phone;
    }
    return null;
  }
}
