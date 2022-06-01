package org.example.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Car {

    @Autowired
    private Engine engine;

    @Autowired
    private Seat seat;

    public void runCar(){
        System.out.println("出发喽！！！");
        seat.personOnSeat();
        engine.engineRunning();
        System.out.println("到地方喽！！！");
    }
}
