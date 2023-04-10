package com.basejava.webapp.model;

import com.basejava.webapp.model.Organization.Period;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.setContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.setContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.setContact(ContactType.SKYPE, "skype:grigory.kislin");
        resume.setContact(ContactType.LINKEDIN, "linkedin");
        resume.setContact(ContactType.GITHUB, "github");
        resume.setContact(ContactType.HOME_PAGE, "homepage");

        TextSection objective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSection(SectionType.OBJECTIVE, objective);

        TextSection personal = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность." +
                " Пурист кода и архитектуры.");
        resume.setSection(SectionType.PERSONAL, personal);

        String achievement1 = "Организация команды и успешная реализация Java проектов для сторонних заказчиков:" +
                " приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot," +
                " участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет";
        String achievement2 = "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"," +
                " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP)." +
                " Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов." +
                " Более 3500 выпускников.";

        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(achievement1, achievement2)));

        String qualification1 = "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2";
        String qualification2 = "Version control: Subversion, Git, Mercury, ClearCase, Perforce";

        resume.setSection(SectionType.QUALIFICATION, new ListSection(Arrays.asList(qualification1, qualification2)));

        LocalDate currentDate = LocalDate.now();
        Period javaOpsPeriod = new Period(of(2013, 10, 1), currentDate,
                "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Organization javaOps = new Organization("Java Online Projects", "url", List.of(javaOpsPeriod));

        Period wrikePeriod = new Period(of(2014, 10, 1), of(2016, 1, 1),
                "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы " +
                "управления проектами Wrike (Java 8 API, Maven, " +
                "Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация," +
                " авторизация по OAuth1, OAuth2, JWT SSO.");
        Organization wrike = new Organization("Wrike", "url", List.of(wrikePeriod));

        resume.setSection(SectionType.EXPERIENCE, new OrganizationSection(Arrays.asList(javaOps, wrike)));

        Period courseraPeriod = new Period(of(2013, 3, 1), of(2013, 5, 1),
                "'Functional Programming Principles in Scala' by Martin Odersky");
        Organization coursera = new Organization("Coursera", "url", List.of(courseraPeriod));

        Period luxoftPeriod = new Period(of(2011, 3, 1), of(2011, 4, 1),
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");
        Organization luxoft = new Organization("Luxoft", "url", List.of(luxoftPeriod));

        resume.setSection(SectionType.EDUCATION, new OrganizationSection(Arrays.asList(coursera, luxoft)));

        return resume;
    }
}
