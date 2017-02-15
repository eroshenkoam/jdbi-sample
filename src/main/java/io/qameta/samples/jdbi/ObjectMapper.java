package io.qameta.samples.jdbi;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectMapper<T> implements ResultSetMapper<T> {

    @SuppressWarnings("unchecked")
    public T map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {

        // узнаем возвращаемый класс User, User2
        Class<T> resultClass = (Class<T>) statementContext.getSqlObjectMethod().getReturnType();

        // создаем инстанс этого класса
        T instance = newInstance(resultClass);

        FieldUtils.getAllFieldsList(resultClass).forEach(field -> {

            // узнаем название поля в таблице
            String columnLabel = getColumnLabel(field);
            try {

                // берез значение из resultSet
                String columnValue = resultSet.getString(columnLabel);

                // конвертируем строку в нужный объект
                PropertyEditor editor = PropertyEditorManager.findEditor(field.getType());
                editor.setAsText(columnValue);

                // устанавливаем значение в поле
                FieldUtils.writeField(field, instance, editor.getValue(), true);
            } catch (SQLException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return instance;
    }

    private static String getColumnLabel(Field field) {
        return field.isAnnotationPresent(Column.class) ? field.getAnnotation(Column.class).value() : field.getName();
    }

    @SuppressWarnings("unchecked")
    private T newInstance(Class<T> clazz) throws SQLException {
        try {
            return ConstructorUtils.getAccessibleConstructor(clazz).newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new SQLException("Can't instantiate object", e);
        }
    }
}
