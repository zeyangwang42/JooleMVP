package com.example.joolemvp.Enums.Converter;

import com.example.joolemvp.Enums.ProductAccessories;
import com.example.joolemvp.Enums.ProductApplication;
import com.example.joolemvp.Enums.ProductMountingLocation;
import org.springframework.core.convert.converter.Converter;

public class StringToProductApplicationEnumConverter implements Converter<String, ProductApplication> {
    @Override
    public ProductApplication convert(String source) {
        try{
            return ProductApplication.valueOf(source);
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
