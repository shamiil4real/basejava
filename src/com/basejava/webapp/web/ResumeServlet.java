package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.*;
import com.basejava.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.getInstance().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume resume;

        if (fullName == null || fullName.trim().equals("")) {
            resume = isNotExist(uuid) ? new Resume() : storage.get(uuid);
            request.setAttribute("resume", resume);
            request.getRequestDispatcher(("/WEB-INF/jsp/edit.jsp")
            ).forward(request, response);
            return;
        }

        if (isNotExist(uuid)) {
            resume = new Resume(deleteExtraSpaces(fullName));
        } else {
            resume = storage.get(uuid);
        }

        resume.setFullName(fullName);

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.setContact(type, deleteExtraSpaces(value));
            } else {
                resume.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                switch (type) {
                    case OBJECTIVE, PERSONAL -> resume.setSection(type,
                            new TextSection(deleteExtraSpaces(value)));
                    case ACHIEVEMENT, QUALIFICATION -> resume.setSection(type,
                            new ListSection(deleteExtraSpaces(value)));
                }
            } else {
                resume.getSections().remove(type);
            }
        }

        if (isNotExist(uuid)) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "add" -> {
                resume = new Resume();
                request.setAttribute("resume", resume);
                request.getRequestDispatcher(("/WEB-INF/jsp/edit.jsp")
                ).forward(request, response);
                return;
            }
            case "delete" -> {
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            }
            case "view", "edit" -> resume = storage.get(uuid);
            default -> throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    private static boolean isNotExist(String uuid) {
        return uuid == null || uuid.length() == 0;
    }

    private static String deleteExtraSpaces(String value) {
        return value.trim().replaceAll(" +", " ");
    }
}