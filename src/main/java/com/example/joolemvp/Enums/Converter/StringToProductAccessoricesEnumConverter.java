package com.example.joolemvp.Enums.Converter;

import com.example.joolemvp.Enums.ProductAccessories;

import org.springframework.core.convert.converter.Converter;

public class StringToProductAccessoricesEnumConverter implements Converter<String, ProductAccessories> {
    @Override
    public ProductAccessories convert(String source) {
        try{
            return ProductAccessories.valueOf(source);
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
