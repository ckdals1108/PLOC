package com.example.ploc.dto.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnList<T> {
    private T data;
}
