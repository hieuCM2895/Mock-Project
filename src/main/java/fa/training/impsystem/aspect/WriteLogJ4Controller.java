package fa.training.impsystem.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import fa.training.impsystem.controller.LoginController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WriteLogJ4Controller {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Before("execution(* training.fpt.com.controller.*.*(..))")
    public void before(JoinPoint joinPoint) {
        logger.info(" before called " + joinPoint.toString());
    }

    @After("execution(* training.fpt.com.controller.*.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info(" after called " + joinPoint.toString());
    }

    @AfterReturning("execution(* training.fpt.com.controller.*.*(..))")
    public void afterReturning(JoinPoint joinPoint) {
        logger.info(" afterReturning called " + joinPoint.toString());
    }

    @AfterThrowing(pointcut = "execution(* training.fpt.com.controller.*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        //Log method execution time
        logger.debug("Execution time of {} , methodName : {} , exception : {}", className, methodName, ex.getMessage());

    }

    @Around("execution(* training.fpt.com.controller.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        ObjectMapper mapper = new ObjectMapper();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        Object[] array = joinPoint.getArgs();
        logger.info("method invoked {} , methodName : {} , arguments : {}", className, methodName, mapper.writeValueAsString(array));
        Object object = joinPoint.proceed();
        logger.info("method invoked {} , methodName : {} , arguments : {}", className, methodName, mapper.writeValueAsString(array));

        return object;
    }

    @Around("execution(* training.fpt.com.controller.*.*(..))")
    public Object aroundTrackTime(ProceedingJoinPoint joinPoint) throws Throwable {

        Long startTime = System.currentTimeMillis();
        logger.info("Start Time Taken by {} is {}", joinPoint, startTime);
        Object object = joinPoint.proceed();

        Long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Time Taken by {} is {}", joinPoint, timeTaken);

        return object;
    }
}
