package com.listen.sql;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

import java.awt.*;
import java.awt.event.KeyEvent;

public class demo extends AbstractVerticle {


    @Override
    public void start() throws Exception {
        vertx.setPeriodic(100L,it -> {
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_1);
                Thread.sleep(10L);
                robot.keyPress(KeyEvent.VK_ESCAPE);
                robot.keyPress(KeyEvent.VK_ALT);

            } catch (AWTException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new demo());
    }
}
