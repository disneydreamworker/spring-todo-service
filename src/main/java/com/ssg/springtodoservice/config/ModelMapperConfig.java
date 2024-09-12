package com.ssg.springtodoservice.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    //이전에는 public enum ModelUtil 클래스로 만들어서 INSTANCE 화 해서 사용했었는데, 이제는 스프링 빈으로 INSTANCE 시켜서 사용하도록 할게~
    //ModelMapperConfig 클래스는 기존의 ModelMapperUtil 클래스를 스프링으로 변경한 버전이야.

    //@Configuration : ModelMapper 라이브러리를 인스턴스화 해서 사용하기 위해 스프링컨테이너 빈으로 등록할게~
    //@Configuration : 스프링 빈에 대한 설정을 하는 클래스 임을 명시=지정해주는 어노테이션이야. 스프링 컨테이너에게 알려주는 역할을 해.
    //<context:component-scan base-package="com.ssg.springtodoservice.config"/> 이렇게 root-context.xml에 추가해주면 스프링 컨테이너가 @Configuration 을 찾고,
    //그 안에 있는 @Bean을 찾아 해당 메소드가 반환하는 객체를 빈으로 등록하고 관리해줄거야~


    //getModelMapper 메소드는 ModelMapper를 반환함
    //@Bean : 실행 결과로 return 된 객체를 스프링의 Bean으로 등록할게~ 그래서 스프링 컨테이너에 저장하고 재사용할게~
    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }



/*
<과거>
 public enum ModelUtil {

    INSTANCE;

    private ModelMapper modelMapper;

    ModelUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

    }

    public ModelMapper get() {
        return modelMapper;
    }
}

*/

}
