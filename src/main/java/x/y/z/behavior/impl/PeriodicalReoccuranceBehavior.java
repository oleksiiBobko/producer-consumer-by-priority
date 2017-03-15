package x.y.z.behavior.impl;

import x.y.z.behavior.Behavior;
import x.y.z.priority.Priority;

public class PeriodicalReoccuranceBehavior implements Behavior {
	private Priority priority = Priority.HIGH;
	private long lastExcutionTime = 0;
	private int interval;
	public PeriodicalReoccuranceBehavior(int interval) {
		this.interval = interval;
	}
	@Override
	public boolean shouldBeFired() {
		
		long currentTime = System.currentTimeMillis();
		
		if(lastExcutionTime == 0) {
			lastExcutionTime = currentTime;
			return false;
		} 
		
		if(lastExcutionTime + interval <= currentTime) {
			lastExcutionTime = currentTime;
			return true;
		}
		
		return false;
	}
	@Override
	public Priority getPriority() {
		return priority;
	}
	@Override
	public String toString() {
		return "PeriodicalReoccuranceBehavior [priority=" + priority + ", lastExcutionTime=" + lastExcutionTime
				+ ", interval=" + interval + "]";
	}
	@Override
	public boolean canBeDeleted() {
		return false;
	}

}
