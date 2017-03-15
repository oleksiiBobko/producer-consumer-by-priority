package x.y.z.behavior.impl;

import x.y.z.behavior.Behavior;
import x.y.z.priority.Priority;

public class ShouldBeFiredBehavior  implements Behavior {
	private final Priority priority = Priority.LOW;
	@Override
	public boolean shouldBeFired() {
		return true;
	}
	public Priority getPriority() {
		return priority;
	}
	@Override
	public String toString() {
		return "ShouldBeFiredBehavior [priority=" + priority + "]";
	}
	@Override
	public boolean canBeDeleted() {
		return true;
	}

}
