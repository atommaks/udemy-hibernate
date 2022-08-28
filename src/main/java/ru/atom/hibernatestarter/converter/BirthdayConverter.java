package ru.atom.hibernatestarter.converter;

import ru.atom.hibernatestarter.dao.entity.Birthday;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.util.Optional;

public class BirthdayConverter implements AttributeConverter<Birthday, Date> {
    @Override
    public Date convertToDatabaseColumn(Birthday attribute) {
        return Optional.ofNullable(attribute)
                .map(attr -> Date.valueOf(attr.birthDate()))
                .orElse(null);
    }

    @Override
    public Birthday convertToEntityAttribute(Date dbData) {
        return Optional.ofNullable(dbData)
                .map(date -> new Birthday(date.toLocalDate()))
                .orElse(null);
    }
}
