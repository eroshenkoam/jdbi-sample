package io.qameta.samples;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper({UserMapper.class, User2Mapper.class})
public interface ObjectMapperDao {

    @SqlQuery("SELECT * FROM users WHERE name = :name")
    User getUser(@Bind("name") String name);

    @SqlQuery("SELECT * FROM users WHERE name = :name")
    User2 getUser2(@Bind("name") String name);

}