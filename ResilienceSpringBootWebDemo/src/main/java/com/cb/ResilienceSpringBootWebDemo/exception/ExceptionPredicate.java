package com.cb.ResilienceSpringBootWebDemo.exception;

import java.util.function.Predicate;

public class ExceptionPredicate  implements Predicate<Throwable> {
    @Override
    public boolean test(Throwable throwable) {
        System.out.println("Exception predicate "+throwable.getMessage());
        return throwable instanceof ArithmeticException;
    }
}
