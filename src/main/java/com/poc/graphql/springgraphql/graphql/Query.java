package com.poc.graphql.springgraphql.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.poc.graphql.springgraphql.entity.Person;
import com.poc.graphql.springgraphql.repository.PersonRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
  private final PersonRepository repository;

  public Query(PersonRepository repository) {
    this.repository = repository;
  }

  public Iterable<Person> getAll(int count, int offset) {
    PageRequest request = PageRequest.of(offset/count, count);
    return this.repository.findAll(request);
  }
}
