package com.book.store.bookstore.repository.specification;

public interface SpecificationProviderManager<E> {
    SpecificationProvider<E> getSpecificationProvider(String key);
}
