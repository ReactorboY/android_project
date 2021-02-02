package com.organicpy.myapplication.repo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 28-01-2021
 */
public class TaskRepo {
    public static List<Integer> createTaskList() {
        List<Integer> tasks = new ArrayList<>();
        tasks.add(1);
        tasks.add(2);
        tasks.add(3);
        tasks.add(4);
        tasks.add(5);
        tasks.add(6);
        return tasks;
    }
}
