package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {

    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) throws SQLException {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.executePreparedStatement("DELETE FROM resume;", PreparedStatement::executeUpdate);
    }

    @Override
    public void update(Resume r) {
        sqlHelper.executePreparedStatement("UPDATE resume r SET full_name = ? WHERE r.uuid = ?;", executor -> {
            executor.setString(1, r.getUuid());
            executor.setString(2, r.getFullName());
            if (executor.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.executePreparedStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?);", executor -> {
            executor.setString(1, r.getUuid().trim());
            executor.setString(2, r.getFullName());
            executor.executeUpdate();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.executePreparedStatement("SELECT * FROM resume r WHERE r.uuid = ?;", executor -> {
            executor.setString(1, uuid.trim());
            ResultSet rs = executor.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(rs.getString("uuid").trim(), rs.getString("full_name"));
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.executePreparedStatement("DELETE FROM resume r WHERE r.uuid = ?;", executor -> {
            executor.setString(1, uuid.trim());
            if (executor.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.executePreparedStatement("SELECT * FROM resume ORDER BY full_name, uuid;", executor -> {
            List<Resume> resumes = new ArrayList<>();
            ResultSet rs = executor.executeQuery();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.executePreparedStatement("SELECT count(*) AS size FROM resume;", executor -> {
            ResultSet rs = executor.executeQuery();
            rs.next();
            return rs.getInt("size");
        });
    }
}