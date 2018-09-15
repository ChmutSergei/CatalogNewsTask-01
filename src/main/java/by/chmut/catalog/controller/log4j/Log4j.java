package by.chmut.catalog.controller.log4j;

import lombok.Data;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;


@Data
public class Log4j {

    private static final Logger log = Logger.getLogger(Log4j.class);


    @After("execution(* by.chmut.catalog.controller.MainController.mainPage(..))")

    public void log(JoinPoint point) {
        log.info(point.getSignature().getName() + " called...");
    }


}
