package x.y.z.priority;

public enum Priority {
	
	HIGH(0),
	NORMAL(1),
	LOW(2);
	
	private int priority;
	
	Priority(int priority) {
		this.priority = priority;
	}

	public int getValue() {
		return priority;
	}
}
