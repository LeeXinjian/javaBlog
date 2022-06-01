package org.example.spring.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect //定义为切面
@Component // 定义成 Bean
public class MyAop {

    @Before("MyAop.pointCut1()")
    public void before(JoinPoint joinPoint) {
        System.out.println("开车之前要检查……");
    }

    @After("MyAop.pointCut1()")
    public void after(JoinPoint joinPoint) {
        System.out.println("下车之后要停稳……");
    }

    /**
     * 将 net.biancheng.c.dao包下的 UserDao 类中的 get() 方法 定义为一个切点
     */


    @Pointcut(value = "execution(* org.example.spring.beans.Car.*(..))")
    public void pointCut1() {
    }

//    /**
//     * 将 net.biancheng.c.dao包下的 UserDao 类中的 delete() 方法 定义为一个切点
//     */
//    @Pointcut(value = "execution(* net.biancheng.c.dao.UserDao.delete(..))")
//    public void pointCut2() {
//    }
//
//    //使用切入点引用
//    @Around("MyAspect.pointCut2()")
//    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        System.out.println("环绕增强……1");
//        proceedingJoinPoint.proceed();
//        System.out.println("环绕增强……2");
//    }
//
//    //使用切入点表达式
//    @AfterReturning(value = "execution(* net.biancheng.c.dao.UserDao.modify(..))", returning = "returnValue")
//    public void afterReturning(Object returnValue) {
//        System.out.println("后置返回增强……,方法返回值为：" + returnValue);
//    }
}