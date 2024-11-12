package someExercises.aspectLearning;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Aspect
@Component
public class MyAspect {
    @Before("someExercises.aspectLearning.Pointcuts.printDate()")
    public void printDateBefore(){
        System.out.println("before " + new Date());
    }
    @After("someExercises.aspectLearning.Pointcuts.printDate()")
    public void printDateAfter(){
        System.out.println("after " + new Date());
    }

  @AfterReturning("someExercises.aspectLearning.Pointcuts.printDate()")
  public void printDateAfterReturning(){
      System.out.println("after returning " + new Date());
  }
  @Around("execution(* someExercises.aspectLearning.checkers.CheckAble.doCheck(..))")
   public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
       System.out.println("around start " + joinPoint.getTarget().getClass().getName());
       Object res = joinPoint.proceed(joinPoint.getArgs());
       System.out.println("around end " + joinPoint.getTarget().getClass().getName());
       return res;
   }
}
class Pointcuts{
    @Pointcut("execution(* someExercises.aspectLearning.checkers.CheckAble.doCheck(..))")
    void printDate(){}
}