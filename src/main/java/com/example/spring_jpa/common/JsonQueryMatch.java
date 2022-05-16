package com.example.spring_jpa.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JsonQueryMatch
{
    MATCH_ANY("%"),
    MATCH_ONE("_"),
    MATCH_NULL("");
    private String value;
}
