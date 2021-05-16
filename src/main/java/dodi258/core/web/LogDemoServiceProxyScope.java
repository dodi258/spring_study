package dodi258.core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoServiceProxyScope {

    private final MyLoggerProxyScope myLogger;

    public void logic(String id) {
        myLogger.log("service id = " + id);
    }
}
