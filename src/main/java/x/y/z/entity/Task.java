package x.y.z.entity;

import x.y.z.priority.Priority;
import x.y.z.behavior.Behavior;

public class Task {
	
	private Priority piority;
	private int jobNumber;
	private Behavior behavior;

	public Task(Priority piority, int jobNumber, Behavior behavior) {
		super();
		this.piority = piority;
		this.jobNumber = jobNumber;
		this.behavior = behavior;
	}
	
	public boolean shouldBeFired() {
		return behavior.shouldBeFired();
	}
	
	public Priority getPiority() {
		return piority;
	}

	public void setPiority(Priority piority) {
		this.piority = piority;
	}

	public int getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(int jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((piority == null) ? 0 : piority.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (piority != other.piority)
			return false;
		return true;
	}

	public Behavior getBehavior() {
		return behavior;
	}

	public boolean canBeDeleted() {
		return behavior.canBeDeleted();
	}
	
	public int compareTo(Task task2) {
		Priority task1Priority = getPiority();
		Priority task2Priority = task2.getPiority();
		Priority task1BehaviorPriority = getBehavior().getPriority();
		Priority task2BehaviorPriority = task2.getBehavior().getPriority();
		if(task1BehaviorPriority != task2BehaviorPriority) {
			return task1BehaviorPriority.compareTo(task2BehaviorPriority);
		}
		return task1Priority.compareTo(task2Priority);
	}

	@Override
	public String toString() {
		return "Task [piority=" + piority + ", jobNumber=" + jobNumber + ", behavior=" + behavior + "]";
	}
	
}
