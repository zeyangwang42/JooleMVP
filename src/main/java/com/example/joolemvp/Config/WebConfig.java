package com.example.joolemvp.Config;

import com.example.joolemvp.Enums.Converter.StringToProductAccessoricesEnumConverter;
import com.example.joolemvp.Enums.Converter.StringToProductApplicationEnumConverter;
import com.example.joolemvp.Enums.Converter.StringToProductMountingLocationEnumConverter;
import com.example.joolemvp.Enums.Converter.StringToProductUseTypeEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToProductUseTypeEnumConverter());
        registry.addConverter(new StringToProductAccessoricesEnumConverter());
        registry.addConverter(new StringToProductApplicationEnumConverter());
        registry.addConverter(new StringToProductMountingLocationEnumConverter());
    }
}
