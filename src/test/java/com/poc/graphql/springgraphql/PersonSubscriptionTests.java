package com.poc.graphql.springgraphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLTest;
import com.poc.graphql.springgraphql.repository.PersonRepository;
import com.poc.graphql.springgraphql.repository.PhoneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@GraphQLTest
public class PersonSubscriptionTests {

  @Autowired
  WebTestClient webTestClient;

  @Autowired
  ResourceLoader resourceLoader;

  @MockBean
  PersonRepository personRepository;

  @MockBean
  PhoneRepository phoneRepository;

  public String loadQuery(String graphqlResource) throws IOException {
    Resource resource = resourceLoader.getResource("classpath:" + graphqlResource);
    return loadResource(resource);
  }

  private String loadResource(Resource resource) throws IOException {
    try(InputStream inputStream = resource.getInputStream()) {
      return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    }
  }

  @Test
  public void test() throws IOException {
    String request = getRequestPayload();

    webTestClient.post()
      .uri("/graphql")
      .contentType(MediaType.APPLICATION_JSON)
      .body(Mono.just(request), String.class)
      .exchange()
      .expectStatus().isOk()
      .expectHeader()
      .contentType("text/event-stream;charset=UTF-8")
      .expectBody()
      .consumeWith(response ->
        assertThat(response.getResponseBody()).isNotNull());
  }

  private String getRequestPayload() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode wrapper = mapper.createObjectNode();
    wrapper.put("query", loadQuery("createdpeople.graphql"));
    wrapper.set("variables", null);

    return mapper.writeValueAsString(wrapper);
  }

}
