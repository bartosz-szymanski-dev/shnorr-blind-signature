package com.bartoszszymanski.app.shnorr.Service;

import com.bartoszszymanski.app.shnorr.Model.LargePrimeNumberPair;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class BlindingFactorGeneratorTest {
    private final static BlindingFactorGenerator generator = new BlindingFactorGenerator(
            new RandomBigIntegerGenerator()
    );

    @Test(expected = RuntimeException.class)
    public void testGenerate_shouldThrowRuntimeExceptionWhenLargePrimeNumberPairIsNotSet() {
        generator.generate();
    }

    @Test
    public void testGenerate() {
        LargePrimeNumberPair largePrimeNumberPair = new LargePrimeNumberPair(
                BigInteger.valueOf(3),
                BigInteger.ONE,
                BigInteger.ONE
        );
        BigInteger result = generator
                .withLargePrimeNumberPair(largePrimeNumberPair)
                .generate();

        assertTrue(result.compareTo(BigInteger.ONE) >= 0);
        assertTrue(result.compareTo(BigInteger.valueOf(3)) <= 0);

        generator.withLargePrimeNumberPair(null);
    }
}