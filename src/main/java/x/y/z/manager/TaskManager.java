package x.y.z.manager;

import x.y.z.entity.Task;
import x.y.z.priority.Priority;

public interface TaskManager {
	boolean insertTask(int jobNumber, Priority priority);
	boolean insertReoccurenceTask(int jobNumber, Priority priority, int interval);
	Task getNextTask();
}
