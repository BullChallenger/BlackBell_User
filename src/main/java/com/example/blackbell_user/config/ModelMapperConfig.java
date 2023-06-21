package com.example.blackbell_user.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                                      .setSkipNullEnabled(true)
                                      .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                                      .setFieldMatchingEnabled(true);
        return modelMapper;
    }
}
