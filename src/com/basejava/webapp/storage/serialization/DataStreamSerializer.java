package com.basejava.webapp.storage.serialization;

import com.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements StreamSerializerStrategy {

    @FunctionalInterface
    private interface Writer<T> {
        void write(T t) throws IOException;
    }

    @FunctionalInterface
    private interface Reader<T> {
        T read() throws IOException;
    }

    @FunctionalInterface
    private interface Setter {
        void set() throws IOException;
    }

    private void writeDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonthValue());
        dos.writeInt(localDate.getDayOfMonth());
    }

    private <T> void writeSection(DataOutputStream dos, Collection<T> collection, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T content : collection) {
            writer.write(content);
        }
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }

    private <T> List<T> readCollection(DataInputStream dis, Reader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private AbstractSection readSection(DataInputStream dis, SectionType type) throws IOException {
        return switch (type) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATION -> new ListSection(readCollection(dis, dis::readUTF));
            case EXPERIENCE, EDUCATION -> new OrganizationSection(readCollection(dis, () ->
                    new Organization(dis.readUTF(), dis.readUTF(), readCollection(dis, () ->
                            new Organization.Period(readDate(dis), readDate(dis), dis.readUTF(), dis.readUTF()))
                    )));
        };
    }

    private void setContent(DataInputStream dis, Setter setter) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            setter.set();
        }
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            writeSection(dos, r.getContacts().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeSection(dos, r.getSections().entrySet(), entry -> {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();

                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getText());
                    case ACHIEVEMENT, QUALIFICATION ->
                            writeSection(dos, ((ListSection) section).getContents(), dos::writeUTF);
                    case EXPERIENCE, EDUCATION ->
                            writeSection(dos, ((OrganizationSection) section).getOrganizations(), org -> {
                                dos.writeUTF(org.getTitle());
                                dos.writeUTF(org.getWebsite().getUrl());
                                writeSection(dos, org.getPeriods(), period -> {
                                    writeDate(dos, period.getStartDate());
                                    writeDate(dos, period.getEndDate());
                                    dos.writeUTF(period.getTitle());
                                    dos.writeUTF(period.getDescription());
                                });
                            });
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();

            Resume resume = new Resume(uuid, fullName);

            setContent(dis, () -> {
                ContactType type = ContactType.valueOf(dis.readUTF());
                resume.setContact(type, dis.readUTF());
            });

            setContent(dis, () -> {
                SectionType type = SectionType.valueOf(dis.readUTF());
                resume.setSection(type, readSection(dis, type));
            });

            return resume;
        }
    }
}
