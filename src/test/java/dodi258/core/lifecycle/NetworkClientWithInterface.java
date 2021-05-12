package dodi258.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClientWithInterface implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClientWithInterface() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disConnect() {
        System.out.println("close: " + url);
    }

    // DisposableBean: destroy() 메서드로 소멸을 지원한다.
    // 컨테이너의 종료가 호출되면 소멸 메서드가 호출이 된다.
    @Override
    public void destroy() throws Exception {
        disConnect();
    }

    // InitializingBean: afterPropertiesSet() 메서드로 초기화를 지원한다.
    // 초기화 메서드가 주입 완료 후에 호출이 된다.
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메세지");
    }
}
