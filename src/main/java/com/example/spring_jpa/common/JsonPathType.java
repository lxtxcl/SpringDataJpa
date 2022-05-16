package com.example.spring_jpa.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JsonPathType {

ALL("all"),
ONE("one");
private final String val;
}
