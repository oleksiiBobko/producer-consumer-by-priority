package x.y.z.behavior;

import x.y.z.priority.Priority;

public interface Behavior {
	boolean shouldBeFired();
	Priority getPriority();
	boolean canBeDeleted();
}
