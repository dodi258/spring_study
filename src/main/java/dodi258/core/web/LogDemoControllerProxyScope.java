//package dodi258.core.web;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//@RequiredArgsConstructor
//public class LogDemoControllerProxyScope {
//
//    private final LogDemoServiceProxyScope logDemoService;
//    private final MyLoggerProxyScope myLogger;
//
//    @RequestMapping(value = "/log-demo-proxy-scope", method = RequestMethod.GET)
//    @ResponseBody
//    public String logDemoProxyScope(HttpServletRequest request) {
//        String requestURL = request.getRequestURL().toString();
//        myLogger.setRequestURL(requestURL);
//
//        myLogger.log("controller test");
//        logDemoService.logic("testId");
//        return "OK - proxy scope";
//    }
//}
