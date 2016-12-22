package com.kangyonggan.web4j.aop;

import com.kangyonggan.web4j.aop.annotation.LogTime;
import com.kangyonggan.web4j.util.AopUtil;
import com.kangyonggan.web4j.util.DateUtils;
import com.kangyonggan.web4j.util.PropertiesUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 打印入参合出参，打印方法执行时间, 慢方法打印error日志
 *
 * @author kangyonggan
 * @since 2016/11/30
 */
@Component
@Log4j2
public class LogAop {

    /**
     * 设定的方法最大执行时间
     */
    private Long slowMethodTime;

    public LogAop() {
        String val = PropertiesUtil.getPropertiesOrDefault("slow.method.time", "10");
        slowMethodTime = Long.parseLong(val);
    }

    /**
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object args[] = joinPoint.getArgs();
        Class clazz = joinPoint.getTarget().getClass();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = clazz.getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        String targetName = "[" + clazz.getName() + "." + method.getName() + "]";

        LogTime logTime = method.getAnnotation(LogTime.class);
        Object result;
        if (logTime != null) {
            log.info("进入方法:" + targetName + " - args:" + AopUtil.getStringFromRequest(args));

            long beginTime = DateUtils.getNow().getTime();
            result = joinPoint.proceed(args);
            long endTime = DateUtils.getNow().getTime();
            long time = endTime - beginTime;

            log.info("离开方法:" + targetName + " - return:" + AopUtil.getStringFromResponse(result));
            log.info("方法耗时:" + time + "ms - " + targetName);

            if (time > slowMethodTime * 1000) {
                log.error("方法执行超过设定时间" + slowMethodTime + "s," + targetName);
            }
        } else {
            result = joinPoint.proceed(args);
        }

        return result;
    }
}
