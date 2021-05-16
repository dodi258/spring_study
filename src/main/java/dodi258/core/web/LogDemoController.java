//package dodi258.core.web;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//@RequiredArgsConstructor
//public class LogDemoController {
//    private final LogDemoService logDemoService;
////    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;
//
//    @RequestMapping("log-demo")
//    @ResponseBody
//    public String logDemo(HttpServletRequest request) {
//        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject();
//        myLogger.setRequestURL(requestURL);
//
//        myLogger.log("controller test");
//        logDemoService.logic("testId");
//        return "OK";
//    }
//}
//
///*
//로거가 잘 작동하는지 확인하는 테스트용 컨트롤러다. 여기서 HttpServletRequest를 통해서 요청 URL을 받았다.
//requestURL 값 http://localhost:8080/log-demo
//이렇게 받은 requestURL 값을 myLogger에 저장해둔다. myLogger는 HTTP 요청 당 각각 구분되므로 다른 HTTP 요청 때문에 값이 섞이는 걱정은 하지 않아도 된다.
//컨트롤러에서 controller test라는 로그를 남긴다.
//
//request 스코프 빈은 실제 고객의 요청이 와야 생성할 수 있으므로,
//provider를 사용해준다. (그 전에 사용하고 싶다먄) !
//
// */package dodi258.core.web;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//@RequiredArgsConstructor
//public class LogDemoController {
//    private final LogDemoService logDemoService;
////    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;
//
//    @RequestMapping("log-demo")
//    @ResponseBody
//    public String logDemo(HttpServletRequest request) {
//        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject();
//        myLogger.setRequestURL(requestURL);
//
//        myLogger.log("controller test");
//        logDemoService.logic("testId");
//        return "OK";
//    }
//}
//
///*
//로거가 잘 작동하는지 확인하는 테스트용 컨트롤러다. 여기서 HttpServletRequest를 통해서 요청 URL을 받았다.
//requestURL 값 http://localhost:8080/log-demo
//이렇게 받은 requestURL 값을 myLogger에 저장해둔다. myLogger는 HTTP 요청 당 각각 구분되므로 다른 HTTP 요청 때문에 값이 섞이는 걱정은 하지 않아도 된다.
//컨트롤러에서 controller test라는 로그를 남긴다.
//
//request 스코프 빈은 실제 고객의 요청이 와야 생성할 수 있으므로,
//provider를 사용해준다. (그 전에 사용하고 싶다먄) !
//
// */