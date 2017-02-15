package io.qameta.samples;

import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ObjectMapperExample {

    private String userName = "Artem";
    private ObjectMapperDao usersDao;

    @Before
    public void initDBI() {
        DBI dbi = DatabaseFactory.connect();
        usersDao = dbi.open(ObjectMapperDao.class);
    }

    @Test
    public void objectMapperShouldConvertResultSetToObject() {
        User user = usersDao.getUser(userName);
        assertThat(user.getName(), equalTo(userName));
    }

    @Test
    public void objectMapperShouldConvertResultSetToObjectWithColumnAnnotation() {
        User2 user = usersDao.getUser2(userName);
        assertThat(user.getFullName(), equalTo(userName));
    }

}
