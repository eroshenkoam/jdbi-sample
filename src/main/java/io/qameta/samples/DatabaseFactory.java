package io.qameta.samples;

import org.skife.jdbi.v2.DBI;

class DatabaseFactory {

    private DatabaseFactory() {
    }

    public static DBI connect() {
        return new DBI("jdbc:mysql://0.0.0.0:3306/jdbi", "root", "root");
    }
}
