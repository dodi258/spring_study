package dodi258.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleAnnotationTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClientWithAnnotation client = ac.getBean(NetworkClientWithAnnotation.class);
        ac.close();

        /*
            result
            생성자 호출, url = null
            NetworkClient.init
            connect: http://hello-spring.dev
            call: http://hello-spring.dev message = 초기화 연결 메시지
            NetworkClient.close
            close: http://hello-spring.dev
         */
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClientWithAnnotation networkClientWithAnnotation() {
            NetworkClientWithAnnotation networkClient = new NetworkClientWithAnnotation();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
