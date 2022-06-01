package org.example.spring.beans;

import org.springframework.stereotype.Service;

@Service
public class Engine {

    public void engineRunning(){
        System.out.println("引擎发动");
    }

}

