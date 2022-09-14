package com.example.joolemvp.Enums.Converter;

import com.example.joolemvp.Enums.ProductMountingLocation;
import com.example.joolemvp.Enums.ProductUseType;
import org.springframework.core.convert.converter.Converter;

public class StringToProductMountingLocationEnumConverter implements Converter<String, ProductMountingLocation> {
    @Override
    public ProductMountingLocation convert(String source) {
        try{
            return ProductMountingLocation.valueOf(source);
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
