package com.bartoszszymanski.app.shnorr.Service;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;


public class RandomBigIntegerGeneratorTest {
    private final static RandomBigIntegerGenerator generator = new RandomBigIntegerGenerator();

    @Test(expected = RuntimeException.class)
    public void testGenerate_whenEqualExpectRuntimeException() {
        generator.generate(BigInteger.ONE, BigInteger.ONE);
    }

    @Test(expected = RuntimeException.class)
    public void testGenerate_whenMinLessOrEqualZeroExpectRuntimeException() {
        generator.generate(BigInteger.ZERO, BigInteger.TWO);
    }

    @Test(expected = RuntimeException.class)
    public void testGenerate_whenMaxLessOrEqualZeroExpectRuntimeException() {
        generator.generate(BigInteger.TWO, BigInteger.ZERO);
    }

    @Test(expected = RuntimeException.class)
    public void testGenerate_whenMaxLessThanMinExpectRuntimeException() {
        generator.generate(BigInteger.TWO, BigInteger.ONE);
    }

    @Test
    public void testGenerate_resultShouldBeBetweenOneAndTen() {
        BigInteger ten = BigInteger.valueOf(10);
        BigInteger result = generator.generate(BigInteger.ONE, ten);
        assertTrue(result.compareTo(BigInteger.ONE) >= 0);
        assertTrue(result.compareTo(ten) <= 0);
    }
}