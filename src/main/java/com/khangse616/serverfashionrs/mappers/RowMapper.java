package com.khangse616.serverfashionrs.mappers;

import com.khangse616.serverfashionrs.services.IImageDataService;

public interface RowMapper<T, S> {
    T mapRow(S s);
    T mapRow(S s, IImageDataService repository);
}