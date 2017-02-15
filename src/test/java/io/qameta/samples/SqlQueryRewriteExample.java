package io.qameta.samples;

import io.qameta.samples.jdbi.TableStatement;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class SqlQueryRewriteExample {

    private DBI dbi;

    @Before
    public void initDBI() {
        this.dbi = DatabaseFactory.connect();
    }

    @Test
    public void queryShouldPassedWithCustomStatementLocator() {
        dbi.setStatementLocator(new TableStatement("users"));
        SqlQueryRewriteDao usersDao = dbi.open(SqlQueryRewriteDao.class);
        assertThat(usersDao.getUser("Artem"), notNullValue());
    }

    @Test(expected = UnableToExecuteStatementException.class)
    public void queryShouldFailedWithoutCustomStatementLocator() {
        SqlQueryRewriteDao usersDao = dbi.open(SqlQueryRewriteDao.class);
        usersDao.getUser("Artem");
    }

}
