package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.Author;
import com.korit.servlet_study.entity.Book;
import com.korit.servlet_study.entity.BookCategory;
import com.korit.servlet_study.entity.Publisher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/book")
public class BookRestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Author author = new Author(1, "test auhtor");
        Publisher publisher = new Publisher(10, "test publisher");
        BookCategory bookCategory = new BookCategory(100, "BookCategory");

        Book book = Book.builder()
                .bookId(0)
                .bookName("test")
                .isbn("123")
                .bookImage("http:// test.com/1234")
                .authorId(author.getAuthorId())
                .categoryId(bookCategory.getCategoryId())
                .author(author)
                .publisher(publisher)
                .bookCategory(bookCategory)
                .build();


        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        ObjectMapper mapper = new ObjectMapper();
        String jsonBook = mapper.writeValueAsString(book);

        response.setContentType("application/json");
        response.getWriter().println(jsonBook);

        response.getWriter().write(jsonBook);
    }
}
