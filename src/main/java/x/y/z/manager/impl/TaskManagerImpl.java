package x.y.z.manager.impl;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import x.y.z.behavior.impl.PeriodicalReoccuranceBehavior;
import x.y.z.behavior.impl.ShouldBeFiredBehavior;
import x.y.z.entity.Task;
import x.y.z.manager.TaskManager;
import x.y.z.priority.Priority;

public class TaskManagerImpl implements TaskManager {

	private int capacity = 10;
	private List<Task> cache = new LinkedList<Task>();

	private PriorityQueue<Task> queue = new PriorityQueue<Task>(capacity, new Comparator<Task>() {
		@Override
		public int compare(Task task1, Task task2) {
			return task1.compareTo(task2);
		}
	});

	@Override
	public synchronized boolean insertTask(int jobNumber, Priority priority) {
		if (hasRoom()) {
			return queue.add(new Task(priority, jobNumber, new ShouldBeFiredBehavior()));
		}
		return false;
	}

	@Override
	public synchronized boolean insertReoccurenceTask(int jobNumber, Priority priority, int interval) {
		if (hasRoom()) {
			return queue.add(new Task(priority, jobNumber, new PeriodicalReoccuranceBehavior(interval)));
		}
		return false;
	}

	@Override
	public synchronized Task getNextTask() {
		Task t = null;
		boolean found = false;
		try {
			while ((t = queue.poll()) != null) {
				if (t.shouldBeFired()) {
					found = true;
					if(!t.canBeDeleted()) {
						cacheIt(t);
					}
					
					break;
				} else {
					cacheIt(t);
				}
			}
		} catch (Throwable th) {
			//TODO add logger
			th.printStackTrace();
		} finally {
			dropCache();
		}

		return found ? t : null;
	}

	private void dropCache() {
		cache.stream().forEach(t -> queue.add(t));
		cache.clear();
	}

	private boolean hasRoom() {
		return queue.size() < capacity;
	}

	private void cacheIt(Task t) {
		cache.add(t);
	}

}
