package com.poc.graphql.springgraphql.repository;

import com.poc.graphql.springgraphql.entity.Phone;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhoneRepository extends PagingAndSortingRepository<Phone, Long> {
  Iterable<Phone> findByPersonId(Long id);
}
