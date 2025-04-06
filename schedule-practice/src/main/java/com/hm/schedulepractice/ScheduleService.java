package com.hm.schedulepractice;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author liuyanoutsee@outlook.com
 **/
public class ScheduleService {


    Trigger trigger = new Trigger();

    ExecutorService executorService = Executors.newFixedThreadPool(6);

    void schedule(Runnable task, long delay) {
        Job job = new Job();
        job.setTask(task);
        job.setStartTime(System.currentTimeMillis() + delay);
        job.setDelay(delay);
        //新的定时任务到来老老实实入队列
        trigger.queue.offer(job);
        trigger.wakeUp();
    }

    //  等待合适的时间，把对应的任务扔到线程池中
    class Trigger {
        //优先级阻塞队列
        PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>();

        Thread thread = new Thread(() -> {
            while (true) {
                //while循环防止虚假唤醒
                while (queue.isEmpty()) {
                    LockSupport.park();
                }
                Job latelyJob = queue.peek();
                //到达执行时机
                if (latelyJob.getStartTime() < System.currentTimeMillis()) {
                    //无任务则阻塞
                    latelyJob = queue.poll();
                    executorService.execute(latelyJob.getTask());
                    //当前任务执行之后，同时创建下一次的定时任务给到队列
                    Job nextJob = new Job();
                    nextJob.setTask(latelyJob.getTask());
                    nextJob.setDelay(latelyJob.getDelay());
                    nextJob.setStartTime(System.currentTimeMillis() + latelyJob.getDelay());
                    queue.offer(nextJob);
                } else {
                    LockSupport.parkUntil(latelyJob.getStartTime());
                }
            }
        });

        {
            thread.start();
            System.out.println("触发器启动了!");
        }

        void wakeUp() {
            LockSupport.unpark(thread);
        }
    }


}
