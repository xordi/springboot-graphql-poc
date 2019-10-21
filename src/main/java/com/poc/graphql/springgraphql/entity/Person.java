package com.poc.graphql.springgraphql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  @Id
  @GeneratedValue()
  Long id;

  String name;

  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "person")
  Set<Phone> phones;

}
