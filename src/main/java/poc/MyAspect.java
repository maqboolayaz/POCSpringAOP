package poc;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class MyAspect {

    private final Log log = LogFactory.getLog(this.getClass());

    @Before("execution(* poc..*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        StringBuffer logMessage = new StringBuffer();
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");
        // append args
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logMessage.append(args[i]).append(",");
        }
        if (args.length > 0) {
            logMessage.deleteCharAt(logMessage.length() - 1);
        }

        logMessage.append(")");
        logMessage.append("Before method");
        log.info(logMessage.toString());
    }

    @After("execution(* poc..*.*(..))")
    public void logAfterMethod(JoinPoint joinPoint) {
        StringBuffer logMessage = new StringBuffer();
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");
        // append args
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logMessage.append(args[i]).append(",");
        }
        if (args.length > 0) {
            logMessage.deleteCharAt(logMessage.length() - 1);
        }

        logMessage.append(")");
        logMessage.append("After method");
        log.info(logMessage.toString());
    }

    @AfterReturning(pointcut = "execution(* poc..*.*(..))", returning = "retVal")
    public void logAfterReturningMethod(JoinPoint joinPoint, Object retVal) {
        StringBuffer logMessage = new StringBuffer();
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");
        // append args
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logMessage.append(args[i]).append(",");
        }
        if (args.length > 0) {
            logMessage.deleteCharAt(logMessage.length() - 1);
        }

        logMessage.append(")");
        logMessage.append(" return Value :");
        logMessage.append(retVal);
        log.info(logMessage.toString());
    }

    @AfterThrowing(pointcut = "execution(* poc..*.*(..))", throwing = "exception")
    public void logAfterThrowingMethod(JoinPoint joinPoint, Exception exception) throws Throwable {
        StringBuffer logMessage = new StringBuffer();
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");
        // append args
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logMessage.append(args[i]).append(",");
        }
        if (args.length > 0) {
            logMessage.deleteCharAt(logMessage.length() - 1);
        }

        logMessage.append(")");
        logMessage.append("throwing exception :");
        logMessage.append(exception);
        log.info(logMessage.toString());
    }

    @Around("execution(* poc..*.*(..))")
    public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object retVal = joinPoint.proceed();

        stopWatch.stop();

        StringBuffer logMessage = new StringBuffer();
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");
        // append args
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logMessage.append(args[i]).append(",");
        }
        if (args.length > 0) {
            logMessage.deleteCharAt(logMessage.length() - 1);
        }

        logMessage.append(")");
        logMessage.append(" execution time: ");
        logMessage.append(stopWatch.getTotalTimeMillis());
        logMessage.append(" ms");
        log.info(logMessage.toString());
        return retVal;
    }
}
