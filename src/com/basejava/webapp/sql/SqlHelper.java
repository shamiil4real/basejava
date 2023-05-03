package com.basejava.webapp.sql;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T executePreparedStatement(String sqlQuery, PreparedStatementExecutor<T> executor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw getExceptionType(e);
        }
    }

    private StorageException getExceptionType(SQLException e) {
        if (e.getSQLState().equals("23505")) {
            throw new ExistStorageException("");
        }
        throw new StorageException(e.getMessage());
    }
}