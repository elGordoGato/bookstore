package com.example.bookstore.model.filter;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record BookFilter(String title, String brand, Integer year) {
    public Specification<Book> toSpecification() {
        return Specification.where(titleContainsSpec())
                .and(brandContainsSpec())
                .and(yearSpec());
    }

    private Specification<Book> titleContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(title)
                ? cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%")
                : null);
    }

    private Specification<Book> brandContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(brand)
                ? cb.like(cb.lower(root.get("brand")), "%" + brand.toLowerCase() + "%")
                : null);
    }

    private Specification<Book> yearSpec() {
        return ((root, query, cb) -> year != null
                ? cb.equal(root.get("year"), year)
                : null);
    }
}