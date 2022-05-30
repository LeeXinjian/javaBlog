package org.example.spring;

import org.springframework.stereotype.Service;

@Service
public class Seat {

    public void personOnSeat(){
        System.out.println("人在座位上");
    }
}
