package com.poc.graphql.springgraphql.graphql;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.poc.graphql.springgraphql.entity.Person;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Component
public class Subscription implements GraphQLSubscriptionResolver {

  Publisher<Person> getCreatedPeople() {
    return Flux.just(
      new Person(1L, "test", null),
      new Person(2L, "test2", null));
  }
}
