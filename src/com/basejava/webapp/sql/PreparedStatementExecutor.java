package com.basejava.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementExecutor<T> {
    T execute(PreparedStatement ps) throws SQLException;
}