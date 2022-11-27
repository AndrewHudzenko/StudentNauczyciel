package com.example.studentnauczyciel.dto.mapper;

public interface ResponseMapper<D, T> {
    D toDto(T t);
}
