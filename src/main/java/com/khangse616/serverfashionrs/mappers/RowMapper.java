package com.khangse616.serverfashionrs.mappers;

import com.khangse616.serverfashionrs.repositories.ImageDataRepository;

public interface RowMapper<T, S> {
    T mapRow(S s);
    T mapRow(S s, ImageDataRepository repository);
}