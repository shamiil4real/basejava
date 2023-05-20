package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.getInstance().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String uuid = request.getParameter("uuid");
        Writer writer = response.getWriter();
        writer.write("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                "    <title>Список резюме</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<section>\n" +
                "<table>\n" +
                "    <thead>\n" +
                "        <tr>\n" +
                "            <th>UUID</th>\n" +
                "            <th>Full Name</th>\n" +
                "        </tr>\n" +
                "    </thead>\n" +
                "<tbody>\n");

        if (uuid == null) {
            for (Resume r : storage.getAllSorted()) {
                writer.write("<tr>\n" +
                        "            <td>" + r.getUuid() + "</td>\n" +
                        "            <td>" + r.getFullName() + "</td>\n" +
                        "        </tr>\n");
            }
        } else {
            writer.write("<tr>\n" +
                    "            <td>" + uuid + "</td>\n" +
                    "            <td>" + storage.get(uuid).getFullName() + "</td>\n" +
                    "        </tr>\n");
        }

        writer.write("</tbody>\n" +
                "</table>\n" +
                "</section>\n" +
                "</body>\n" +
                "</html>\n");
    }
}