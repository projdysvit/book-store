package com.book.store.bookstore.repository.specification.book;

import com.book.store.bookstore.dto.request.book.BookSearchParameters;
import com.book.store.bookstore.model.Book;
import com.book.store.bookstore.repository.specification.SpecificationBuilder;
import com.book.store.bookstore.repository.specification.SpecificationProviderManager;
import com.book.store.bookstore.repository.specification.book.field.AuthorSpecification;
import com.book.store.bookstore.repository.specification.book.field.IsbnSpecification;
import com.book.store.bookstore.repository.specification.book.field.TitleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book, BookSearchParameters> {
    private final SpecificationProviderManager<Book> specificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameters searchParams) {
        Specification<Book> spec = Specification.where(null);
        spec = checkSpecification(spec, searchParams.titles(), new TitleSpecification().getKey());
        spec = checkSpecification(spec, searchParams.authors(), 
                                            new AuthorSpecification().getKey());
        spec = checkSpecification(spec, searchParams.isbns(), new IsbnSpecification().getKey());
        return spec;
    }

    private Specification<Book> checkSpecification(Specification<Book> spec,
                                                    String[] params,
                                                    String key) {
        if (!(params != null && params.length > 0)) {
            return spec;
        }

        return spec.and(specificationProviderManager.getSpecificationProvider(key)
                            .getSpecification(params));
    }
}
