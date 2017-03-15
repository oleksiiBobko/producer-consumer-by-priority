package x.y.z.manager.impl;

import org.junit.Before;
import org.junit.Test;

import x.y.z.entity.Task;
import x.y.z.manager.TaskManager;
import x.y.z.priority.Priority;

public class TaskManagerImplTest {
    TaskManager taskMgr = new TaskManagerImpl();

    @Before
    public void before() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    taskMgr.insertTask(42, Priority.NORMAL);
                    taskMgr.insertTask(43, Priority.HIGH);
                    taskMgr.insertTask(44, Priority.LOW);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "simple-task-producer").start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    taskMgr.insertTask(2, Priority.NORMAL);
                    taskMgr.insertTask(3, Priority.HIGH);
                    taskMgr.insertTask(4, Priority.LOW);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "simple-task-producer-1").start();

        taskMgr.insertReoccurenceTask(73, Priority.LOW, 100);
        taskMgr.insertReoccurenceTask(74, Priority.NORMAL, 2000);
        taskMgr.insertReoccurenceTask(75, Priority.HIGH, 5000);

    }

    @Test
    public void test1() {
        while (true) {
            Task t = taskMgr.getNextTask();
            System.out.println(t);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
