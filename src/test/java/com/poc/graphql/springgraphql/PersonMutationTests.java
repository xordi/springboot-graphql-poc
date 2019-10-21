package com.poc.graphql.springgraphql;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.poc.graphql.springgraphql.entity.Person;
import com.poc.graphql.springgraphql.repository.PersonRepository;
import com.poc.graphql.springgraphql.repository.PhoneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/*
Those tests only test GraphQL related classes,
that's why we are mocking the repositories, since
every @Component, @Service or @Repository element is not loaded
in the application context when using @GraphQLTest annotation
 */
@RunWith(SpringRunner.class)
@GraphQLTest
public class PersonMutationTests {

  @Autowired
  private GraphQLTestTemplate graphQLTestTemplate;

  @MockBean
  PersonRepository personRepository;

  @MockBean
  PhoneRepository phoneRepository;

  @Test
  public void personMutationAddPerson() throws IOException {
    GraphQLResponse response = graphQLTestTemplate.postForResource("addperson.graphql");
    assertThat(response).isNotNull();
    assertThat(response.isOk()).isTrue();
  }

  @Test
  public void personMutationAddPhone() throws IOException {

    Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(new Person()));

    ObjectNode variables = JsonNodeFactory.instance.objectNode();

    variables.set("id", JsonNodeFactory.instance.numberNode(1));
    variables.set("phone", JsonNodeFactory.instance.objectNode()
      .put("phone", "971112233")
      .put("type", "HOME"));

    GraphQLResponse response = graphQLTestTemplate.perform("addphone.graphql", variables);

    assertThat(response).isNotNull();
    assertThat(response.isOk()).isTrue();
  }
}
