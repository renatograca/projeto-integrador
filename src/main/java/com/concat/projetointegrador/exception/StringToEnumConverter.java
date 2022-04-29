package com.concat.projetointegrador.exception;

import com.concat.projetointegrador.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEnumConverter implements Converter<String, Category> {
    @Override
    public Category convert(String source) {
        try {
            return Category.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CategoryNotFoundException("Categoria não encontrada!");
        }
    }
}
