package net.knaur.java.performance.benchmark.exception;

public class CustomException extends Exception {
	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
