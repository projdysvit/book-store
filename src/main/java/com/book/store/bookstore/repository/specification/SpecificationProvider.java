package com.book.store.bookstore.repository.specification;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<E> {
    Specification<E> getSpecification(String[] params);

    String getKey();
}
