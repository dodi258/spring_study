package dodi258.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleMethodTest {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClientWithCustomMethod client = ac.getBean(NetworkClientWithCustomMethod.class);
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
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClientWithCustomMethod networkClientWithCustomMethod() {
            NetworkClientWithCustomMethod networkClient = new NetworkClientWithCustomMethod();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
