package com.book.store.bookstore.repository.specification.book.field;

import com.book.store.bookstore.model.Book;
import com.book.store.bookstore.repository.specification.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class IsbnSpecification implements SpecificationProvider<Book> {
    private static final String FIELD_NAME = "isbn";

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, cb) -> root.get(FIELD_NAME).in(Arrays.stream(params).toArray());
    }

    @Override
    public String getKey() {
        return FIELD_NAME;
    }
}
