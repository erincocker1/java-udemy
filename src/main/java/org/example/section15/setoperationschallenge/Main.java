package org.example.section15.setoperationschallenge;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<Task> tasks = TaskData.getTasks("all");
        Set<Task> annsTasks = TaskData.getTasks("Ann");
        Set<Task> bobsTasks = TaskData.getTasks("Bob");
        Set<Task> carolsTasks = TaskData.getTasks("Carol");

        Set<Task> allTasks = SetMethods.getUnion(List.of(tasks, annsTasks, bobsTasks, carolsTasks));
        SetMethods.sortAndPrint("All Tasks", allTasks);

        Set<Task> assignedTasks = SetMethods.getUnion(List.of(annsTasks, bobsTasks, carolsTasks));
        SetMethods.sortAndPrint("Tasks assigned to at least one person", assignedTasks);

        Set<Task> notAssigned = SetMethods.getDifference(tasks, assignedTasks);
        SetMethods.sortAndPrint("Tasks not yet assigned", notAssigned);

        Set<Task> multipleAssignees = SetMethods.getUnion(List.of(
                SetMethods.getIntersect(annsTasks, bobsTasks),
                SetMethods.getIntersect(bobsTasks, carolsTasks),
                SetMethods.getIntersect(carolsTasks, annsTasks)));
        SetMethods.sortAndPrint("Tasks assigned to multiple employees", multipleAssignees);
    }
}
