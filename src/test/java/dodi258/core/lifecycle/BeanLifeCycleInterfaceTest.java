package dodi258.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleInterfaceTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClientWithInterface client = ac.getBean(NetworkClientWithInterface.class);
        ac.close();

        /*
            result
            생성자 호출, url = null
            connect: http://hello-spring.dev
            call: http://hello-spring.dev message = 초기화 연결 메세지
            close: http://hello-spring.dev
         */
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClientWithInterface networkClientWithInterface() {
            NetworkClientWithInterface networkClient = new NetworkClientWithInterface();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
