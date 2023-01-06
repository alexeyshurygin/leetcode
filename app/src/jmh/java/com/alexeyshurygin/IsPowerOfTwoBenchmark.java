package com.alexeyshurygin;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Alexey Shurygin
 */
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
public class IsPowerOfTwoBenchmark {
    public static final int COUNT = 1_000_000;
    IsPowerOfTwo inst;
    ThreadLocalRandom rnd;

    @Setup(Level.Iteration)
    public void setup(BenchmarkParams params) {
        inst = new IsPowerOfTwo();
        rnd = ThreadLocalRandom.current();
    }

    @Benchmark
    public void compare(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            var r = inst.isPowerOfTwoCompare(Math.abs(rnd.nextInt()));
            bh.consume(r);
        }
    }

    @Benchmark
    public void loop(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            var r = inst.isPowerOfTwoLoop(Math.abs(rnd.nextInt()));
            bh.consume(r);
        }
    }

    @Benchmark
    public void array(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            var r = inst.isPowerOfTwoArray(Math.abs(rnd.nextInt()));
            bh.consume(r);
        }
    }

    @Benchmark
    public void hashSet(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            var r = inst.isPowerOfTwoHashSet(Math.abs(rnd.nextInt()));
            bh.consume(r);
        }
    }

    @Benchmark
    public void treeSet(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            var r = inst.isPowerOfTwoTreeSet(Math.abs(rnd.nextInt()));
            bh.consume(r);
        }
    }
}
