package com.book.store.bookstore.dto.request.book;

public record BookSearchParameters(
        String[] titles,
        String[] authors,
        String[] isbns
) {
}
