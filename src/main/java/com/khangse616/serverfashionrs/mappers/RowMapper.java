package com.khangse616.serverfashionrs.mappers;

public interface RowMapper<T, S> {
    T mapRow(S s);
}
