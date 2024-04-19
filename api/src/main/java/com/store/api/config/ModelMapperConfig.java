package com.store.api.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //formatear fechas de java a formato json
        Converter<Date, String> dateConverter = new Converter<Date, String>() {
            @Override
            public String convert(MappingContext<Date, String> context) {
                Date source = context.getSource();
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(source);
            }
        };

        modelMapper.addConverter(dateConverter);

        return modelMapper;
    }
}