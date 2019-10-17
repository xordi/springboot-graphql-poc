package com.poc.graphql.springgraphql.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Person {

  @Id
  @GeneratedValue()
  Long id;

  String name;

  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "person")
  Set<Phone> phones;

}
