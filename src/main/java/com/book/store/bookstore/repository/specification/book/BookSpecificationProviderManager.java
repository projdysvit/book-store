package com.book.store.bookstore.repository.specification.book;

import com.book.store.bookstore.exception.IncorrectSpecificationKeyException;
import com.book.store.bookstore.model.Book;
import com.book.store.bookstore.repository.specification.SpecificationProvider;
import com.book.store.bookstore.repository.specification.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationProviderManager 
        implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> specificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return specificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(
                    () -> new IncorrectSpecificationKeyException(
                            "Unhandled specification key " + key
                        )
                );
    }
}
