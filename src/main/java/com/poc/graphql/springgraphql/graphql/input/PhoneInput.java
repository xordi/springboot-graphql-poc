package com.poc.graphql.springgraphql.graphql.input;

import com.poc.graphql.springgraphql.entity.PhoneType;
import lombok.Data;

@Data
public class PhoneInput {
  PhoneType type;
  String phone;
}
