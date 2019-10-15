package com.poc.graphql.springgraphql.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.poc.graphql.springgraphql.entity.Person;
import com.poc.graphql.springgraphql.repository.PersonRepository;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {
  private final PersonRepository repository;

  public Mutation(PersonRepository repository) {
    this.repository = repository;
  }

  public Person addPerson(String name, String phone) {
    Person p = new Person();
    p.setName(name);
    p.setPhone(phone);

    this.repository.save(p);

    return p;
  }
}
