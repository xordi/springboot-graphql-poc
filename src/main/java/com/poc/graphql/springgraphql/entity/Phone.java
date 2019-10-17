package com.poc.graphql.springgraphql.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Phone {

  @Id
  @GeneratedValue()
  Long id;

  @ManyToOne(optional = false)
  Person person;
  @Enumerated(EnumType.STRING)
  PhoneType type;
  String phone;
}
