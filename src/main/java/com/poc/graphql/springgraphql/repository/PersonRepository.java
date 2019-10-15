package com.poc.graphql.springgraphql.repository;

import com.poc.graphql.springgraphql.entity.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
}
