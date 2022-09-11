package rolapperf;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class MetricReport {

	public Map<String, MetricEntry> map = new HashMap<>();

	public MetricEntry get(String name) {
		return map.computeIfAbsent(name, (k) -> new MetricEntry(k));
	}

	public static class MetricEntry {
		String name;
		long duration = 0;

		double call = 0;

		public MetricEntry(String name) {
			this.name = name;
		}

		public void measure(Runnable run) {
			long start = System.currentTimeMillis();

			run.run();
			duration += System.currentTimeMillis() - start;
			call++;
		}

		public String toString() {
			return duration + "ms;" + call + ";" + (duration/call) + "ms";
		}
	}
}
