package com.bartoszszymanski.app.shnorr.Service;

import com.bartoszszymanski.app.shnorr.Model.LargePrimeNumberPair;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class MessageBlinderTest {
    private final static MessageBlinder blinder = new MessageBlinder(
            new BlindingFactorGenerator(new RandomBigIntegerGenerator())
    );

    @Test(expected = RuntimeException.class)
    public void testBlind_shouldThrowExceptionWhenLargePrimeNumberIsNotSet() {
        blinder.blind(BigInteger.ONE);
    }

    @Test
    public void testBlind() {
        BigInteger message = BigInteger.valueOf(4);
        LargePrimeNumberPair largePrimeNumberPair = new LargePrimeNumberPair(
                BigInteger.valueOf(3),
                BigInteger.ONE,
                BigInteger.ONE
        );
        blinder.withLargePrimeNumberPair(largePrimeNumberPair);
        assertEquals(0, blinder.blind(message).compareTo(BigInteger.ONE));
    }
}