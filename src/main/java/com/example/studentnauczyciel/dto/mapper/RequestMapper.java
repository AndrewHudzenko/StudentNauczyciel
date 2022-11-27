package com.example.studentnauczyciel.dto.mapper;

public interface RequestMapper<D, T> {
    T toModel(D dto);
}
