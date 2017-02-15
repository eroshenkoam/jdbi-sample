package io.qameta.samples.jdbi;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.StatementLocator;

public class TableStatement implements StatementLocator {

    static final String TABLE_KEY = "[table]";

    private final String table;

    public TableStatement(String table) {
        this.table = table;
    }

    public String locate(String s, StatementContext statementContext) throws Exception {
        return s.replace(TABLE_KEY, table);
    }

}
