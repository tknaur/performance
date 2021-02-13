package net.knaur.java.performance.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(1)
@Warmup(iterations = 3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BoxedTypeBenchmark {
	private static final long LOOP_LIMIT = 1000;

	@Benchmark
	public void boxedLongTypeLoop() {
		Long sum = 0L;
		for (long i = 0; i < LOOP_LIMIT; i++) {
			sum +=i;
		}
	}

	@Benchmark
	public void longTypeLoop() {
		long sum = 0L;
		for (long i = 0; i < LOOP_LIMIT; i++) {
			sum +=i;
		}
	}
}
