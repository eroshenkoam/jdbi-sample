package io.qameta.samples;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface SqlQueryRewriteDao {

    @SqlQuery("SELECT * FROM [table] WHERE name = :name")
    String getUser(@Bind("name") String name);

}
