package hello.core2.web;

import hello.core2.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogDemoController {

    private final LogDemoService logDemoServiceProvider;

    // 스프링이 뜰 때 의존주입을 받아야하지만, request 스코프의 생명주기는 HTTP 요청 - 응답까지이다.
    // 그래서 단순 MyLogger를 주입받으려 하면 request가 없기에 스프링 컨테이너에도 존재하지 않아 런타임 오류이다.
    // 해결방안은 Provider로 감싸 request 시점에 주입 받을 수 있다.
    private final ObjectProvider<MyLogger> myLoggerProvider;

    public LogDemoController(LogDemoService logDemoServiceProvider, ObjectProvider<MyLogger> myLoggerProvider) {
        this.logDemoServiceProvider = logDemoServiceProvider;
        this.myLoggerProvider = myLoggerProvider;
    }

    @RequestMapping("log-demo")
    @ResponseBody   // 일단 view 없이 string만 보겠다.
    public String logDemo(HttpServletRequest request) {
        String url = request.getRequestURL().toString();

        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(url);

        myLogger.log("controller test");
        logDemoServiceProvider.logic("testId");

        return "OK";
    }
}
