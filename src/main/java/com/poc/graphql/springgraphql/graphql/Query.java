package com.poc.graphql.springgraphql.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.poc.graphql.springgraphql.entity.Person;
import com.poc.graphql.springgraphql.repository.PersonRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class Query implements GraphQLQueryResolver {
  private final PersonRepository repository;

  public Query(PersonRepository repository) {
    this.repository = repository;
  }

  public Iterable<Person> getPeople(int limit, int offset) {
    if (limit > 0) {
      PageRequest request = PageRequest.of(offset/limit, limit);
      return this.repository.findAll(request);
    }

    return this.repository.findAll();
  }
}
