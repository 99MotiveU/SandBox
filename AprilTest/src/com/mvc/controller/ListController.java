package com.mvc.controller;

import com.mvc.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ListController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;  // �⺻ �������� 1
        int pageSize = 10;    // �� �������� ������ �Խñ� ��
        int totalCount = 0;   // ��ü �Խñ� ��

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        BbsDao dao = new BbsDao();
        totalCount = dao.getTotalCount();  // ��ü �Խñ� �� ��������
        int totalPage = (int) Math.ceil((double) totalCount / pageSize);  // ��ü ������ �� ���

        int startRow = (currentPage - 1) * pageSize;
        int endRow = currentPage * pageSize;

        List<BbsDto> list = dao.selectPaged(startRow, endRow);  // ����¡�� ������ ��������

        request.setAttribute("list", list);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", totalPage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }
}
