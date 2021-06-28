package net.knaur.java.performance.benchmark;

import net.knaur.java.performance.benchmark.exception.CustomException;
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

	@Benchmark
	public void throwAndCatchException(Blackhole blackhole) {
		for (int i = 0; i < LIMIT; i++) {
			try {
				throw new Exception();
			} catch (Exception e) {
				blackhole.consume(e);
			}
		}
	}

	@Benchmark
	public void throwAndCatchCustomExceptionWithoutFillingStacktrace(Blackhole blackhole) {
		for (int i = 0; i < LIMIT; i++) {
			try {
				throw new CustomException();
			} catch (Exception e) {
				blackhole.consume(e);
			}
		}
	}

	@Benchmark
	@Fork(value = 1, jvmArgs = "-XX:-StackTraceInThrowable")
	public void throwExceptionWithoutAddingStackTrace(Blackhole blackhole) {
		for (int i = 0; i < LIMIT; i++) {
			try {
				throw new Exception();
			} catch (Exception e) {
				blackhole.consume(e);
			}
		}
	}

	@Benchmark
	@Fork(value = 1, jvmArgs = "-XX:-StackTraceInThrowable")
	public void throwMyExceptionWithoutAddingStackTrace(Blackhole blackhole) {
		for (int i = 0; i < LIMIT; i++) {
			try {
				throw new CustomException();
			} catch (Exception e) {
				blackhole.consume(e);
			}
		}
	}

	@Benchmark
	@Fork(value = 1, jvmArgs = "-XX:MaxJavaStackTraceDepth=1")
	public void throwExceptionWithStackTraceDepthLimit(Blackhole blackhole) {
		for (int i = 0; i < LIMIT; i++) {
			try {
				throw new Exception();
			} catch (Exception e) {
				blackhole.consume(e);
			}
		}
	}

	@Benchmark
	public void throwAndCatchWithoutException(Blackhole blackhole) {
		for (int i = 0; i < LIMIT; i++) {
			try {
				blackhole.consume(new Object());
			} catch (Exception exception) { }
		}
	}
}
