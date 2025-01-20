package com.korit.servlet_study.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/product/search")
public class ProductSearchPageServlet extends HttpServlet {

    private List<Map<String,Object>> productList = List.of(
            Map.of("productName", "상품1"),
            Map.of("productName", "상품2"),
            Map.of("productName", "상품3"),
            Map.of("productName", "상품4"),
            Map.of("productName", "상품5")
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchValue = req.getParameter("searchValue");

        req.setAttribute("products", productList);

        if(searchValue != null) {
            if(!searchValue.isBlank()) {
                req.setAttribute("products", productList.stream()
                   .filter(p -> p.get("productName").toString().contains(searchValue))
                        .collect(Collectors.toList()));
            }
        }

        req.getRequestDispatcher("/WEB-INF/product_search.jsp").forward(req, resp);
    }
}
