package com.example.joolemvp.Enums.Converter;


import com.example.joolemvp.Enums.ProductUseType;
import org.springframework.core.convert.converter.Converter;

public class StringToProductUseTypeEnumConverter implements Converter<String, ProductUseType> {
    @Override
    public ProductUseType convert(String source) {
        try{
            return ProductUseType.valueOf(source);
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}