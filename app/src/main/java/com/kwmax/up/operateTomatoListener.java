package com.kwmax.up;

/**
 * Created by keweimeng on 2019/10/4.
 */
public interface operateTomatoListener {
    void addTomato(String tomato, String minCount);
    void cancal();
    void giveup(String reason);
}
