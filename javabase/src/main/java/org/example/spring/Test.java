package org.example.spring;

import org.example.spring.beans.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example.spring");

        Car car = context.getBean(Car.class);
        car.runCar();
    }
}
