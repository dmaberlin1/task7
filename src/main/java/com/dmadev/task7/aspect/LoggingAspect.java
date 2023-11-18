package com.dmadev.task7.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Component
@Aspect
public class LoggingAspect {

    // Этот метод будет вызываться вокруг всех методов в пакете ru.itsinfo.repository
    @Around("execution( *com.dmadev.task7.repositories.*.*(..))")
    public Object aroundAllRepositoriesMethodsAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        // Выводим в консоль сообщение о начале выполнения метода
        System.out.println("Start Method (joinPoint.getSignature()) = " + joinPoint.getSignature());

        //Засекаем время начала выполнения метода
        long start = System.currentTimeMillis();

        //Вызываем метод вокруг которого оборачиваемся
        Object proceeded = joinPoint.proceed();

        // Выводим в консоль сообщение о завершении выполнения метода и времени, затраченном на выполнение
        System.out.println(joinPoint.getSignature().getName() + " executed in" + (System.currentTimeMillis() - start) + "ms");

        // Возвращаем результат выполнения метода
        return proceeded;
    }
}
