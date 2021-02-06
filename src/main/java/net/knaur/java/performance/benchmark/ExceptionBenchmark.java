package net.knaur.java.performance.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@Fork(1)
@Warmup(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ExceptionBenchmark {
	private static final int LIMIT = 10_000;

	@Benchmark
	public void doNotThrowException(Blackhole blackhole) {
		for (int i = 0; i < LIMIT; i++) {
			blackhole.consume(new Object());
		}
	}
}
