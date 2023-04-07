package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume grigoryKislin = new Resume("Григорий Кислин");

        grigoryKislin.setContact(ContactType.PHONE, "+7(921) 855-0482");
        grigoryKislin.setContact(ContactType.MAIL, "gkislin@yandex.ru");
        grigoryKislin.setContact(ContactType.SKYPE, "skype:grigory.kislin");

        TextSection objective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        grigoryKislin.setSection(SectionType.OBJECTIVE, objective);

        TextSection personal = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность." +
                " Пурист кода и архитектуры.");
        grigoryKislin.setSection(SectionType.PERSONAL, personal);

        String achievement1 = "Организация команды и успешная реализация Java проектов для сторонних заказчиков:" +
                " приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot," +
                " участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет";
        String achievement2 = "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"," +
                " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP)." +
                " Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов." +
                " Более 3500 выпускников.";
        String achievement3 = "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike." +
                " Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.";
        String achievement4 = "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM." +
                " Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.";
        String achievement5 = "Реализация c нуля Rich Internet Application приложения на стеке технологий " +
                "JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.";

        grigoryKislin.setSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(achievement1, achievement2,
                achievement3, achievement4, achievement5)));

        String qualification1 = "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2";
        String qualification2 = "Version control: Subversion, Git, Mercury, ClearCase, Perforce";
        String qualification3 = "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL," +
                " SQLite, MS SQL, HSQLDB";
        String qualification4 = "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy";
        String qualification5 = "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts";

        grigoryKislin.setSection(SectionType.QUALIFICATION, new ListSection(Arrays.asList(qualification1, qualification2,
                qualification3, qualification4, qualification5)));

        LocalDate currentDate = LocalDate.now();
        Period javaOpsPeriod = new Period(LocalDate.of(2013, 10, 1), currentDate,
                "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Organization javaOps = new Organization("Java Online Projects", "url", List.of(javaOpsPeriod));

        Period wrikePeriod = new Period(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1),
                "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, " +
                "Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация," +
                " авторизация по OAuth1, OAuth2, JWT SSO.");
        Organization wrike = new Organization("Wrike", "url", List.of(wrikePeriod));

        Period ritCenterPeriod = new Period(LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1),
                "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика," +
                " версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway)," +
                " конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы." +
                " Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения" +
                " (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера " +
                "документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC," +
                " Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Organization ritCenter = new Organization("RIT Center", "url", List.of(ritCenterPeriod));

        grigoryKislin.setSection(SectionType.EXPERIENCE, new OrganizationSection(Arrays.asList(javaOps, wrike, ritCenter)));

        Period courseraPeriod = new Period(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1),
                "'Functional Programming Principles in Scala' by Martin Odersky");
        Organization coursera = new Organization("Coursera", "url", List.of(courseraPeriod));

        Period luxoftPeriod = new Period(LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1),
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");
        Organization luxoft = new Organization("Luxoft", "url", List.of(luxoftPeriod));

        Period siemensPeriod = new Period(LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1),
                "3 месяца обучения мобильным IN сетям");
        Organization siemens = new Organization("Siemens AG", "url", List.of(siemensPeriod));

        grigoryKislin.setSection(SectionType.EDUCATION, new OrganizationSection(Arrays.asList(coursera, luxoft, siemens)));

        System.out.println(grigoryKislin.getFullName());
        System.out.println(grigoryKislin.getContacts());
        System.out.println(grigoryKislin.getSections());
    }
}
