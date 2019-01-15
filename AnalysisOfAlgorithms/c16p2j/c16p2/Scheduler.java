package c16p2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import c16p2.BatchProcessor.Task;

public class Scheduler {

    // 16-2.a
    /**
     * Schedule a bunch of tasks to be executed on a
     * batch processor by re-arranging the arrangement of
     * tasks. 
     * @param tasks The tasks to execute.
     * POSTCONDITION: tasks has been rearranged into the
     * order to be executed
     */
    public static void scheduleBatch(BatchProcessor.Task[] tasks) {
    	Comparator byProcessingTime = new Comparator<BatchProcessor.Task>() {
			public int compare(Task a, Task b) {
				return a.processingTime - b.processingTime;
			}
    	};
    	Arrays.sort(tasks, byProcessingTime);
    }

    // 16-2.b
    /**
     * Schedule a bunch of tasks to be executed on a 
     * time-sharing system by producing a schedule consisting
     * of units, each unit indicating which task and for how long.
     * Note you can schedule periods for the processor to be idle,
     * and rearranging the tasks array is harmless.
     * @param tasks
     * @return
     */
    public static Iterable<TSProcessor.ScheduleUnit> 
        scheduleTimeSharing(TSProcessor.Task[] tasks) {

        ArrayList<TSProcessor.ScheduleUnit> schedule = 
                new ArrayList<TSProcessor.ScheduleUnit>();

        Comparator byReleaseTime = new Comparator<TSProcessor.Task>() {
			public int compare(TSProcessor.Task a, TSProcessor.Task b) {
				return a.releaseTime - b.releaseTime;
			}
    	};
    	class UsedTask {
    		public TSProcessor.Task task;
    		public final int releaseTime;
    		public int timeLeft;
    		UsedTask(TSProcessor.Task task) {
    			this.task = task;
    			releaseTime = task.releaseTime;
    			timeLeft = task.processingTime;
    		}
    	}
    	Comparator byTimeLeft = new Comparator<UsedTask>() {
			public int compare(UsedTask a, UsedTask b) {
				return a.timeLeft - b.timeLeft;
			}
    	};
    	Arrays.sort(tasks, byReleaseTime);
        // we keep a priority queue of all the tasks that are available
        // with time left as the priority
        PriorityQueue<UsedTask> workingTasks = new PriorityQueue<UsedTask>(tasks.length, byTimeLeft);
        workingTasks.add(new UsedTask(tasks[0]));
        // for any given time unit, we want to use the available task 
        // with minimal processing time.
        for (int i = 1; !workingTasks.isEmpty(); i++) {
        	if (i >= tasks.length) {
        		while (!workingTasks.isEmpty()) {
        			UsedTask min = workingTasks.poll();
    				schedule.add(new TSProcessor.ScheduleUnit(min.task, min.timeLeft));
        		}
        	}
        	else if (tasks[i].releaseTime > tasks[i-1].releaseTime) {
        		int timeToFill = tasks[i].releaseTime - tasks[i-1].releaseTime;
        		while (timeToFill > 0 && !workingTasks.isEmpty()) {
        			UsedTask min = workingTasks.peek();
        			if (min.timeLeft > timeToFill) {
        				schedule.add(new TSProcessor.ScheduleUnit(min.task, timeToFill));
        				min.timeLeft -= timeToFill;
        				timeToFill = 0;
        			}
        			else {
        				schedule.add(new TSProcessor.ScheduleUnit(min.task, min.timeLeft));
        				timeToFill -= min.timeLeft;
        				workingTasks.remove();
        			}
        		}
        		// in case there has to be some idle time for the processor
        		// arbitrarily substitute this for tasks[0] time.
        		if (timeToFill > 0)
    				schedule.add(new TSProcessor.ScheduleUnit(tasks[0], timeToFill));
        		workingTasks.add(new UsedTask(tasks[i]));
        	}
        	else {
        		workingTasks.add(new UsedTask(tasks[i]));
        	}
        }
        
        return schedule;
    }

    
}

