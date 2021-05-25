package hello.core2.web;

import hello.core2.common.MyLogger;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
public class LogDemoService {
    private final ObjectProvider<MyLogger> myLoggerProvider;

    public LogDemoService(ObjectProvider<MyLogger> myLoggerProvider) {
        this.myLoggerProvider = myLoggerProvider;
    }

    public void logic(String id) {
        myLoggerProvider.getObject().log("service id = " + id);
    }
}
