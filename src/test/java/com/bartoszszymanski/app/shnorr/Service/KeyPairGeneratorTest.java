package com.bartoszszymanski.app.shnorr.Service;

import com.bartoszszymanski.app.shnorr.Model.KeyPair;
import com.bartoszszymanski.app.shnorr.Model.LargePrimeNumberPair;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class KeyPairGeneratorTest {
    private final static KeyPairGenerator generator = new KeyPairGenerator(
            new RandomBigIntegerGenerator()
    );

    @Test(expected = RuntimeException.class)
    public void testGenerate_withoutLargePrimeNumberPairBeingSet() {
        generator.generate();
    }

    @Test
    public void testGenerate() {
        LargePrimeNumberPair largePrimeNumberPair = new LargePrimeNumberPair(
                BigInteger.valueOf(3),
                BigInteger.ONE,
                BigInteger.TWO
        );
        KeyPair result = generator
                .withLargePrimeNumberPair(largePrimeNumberPair)
                .generate();
        assertEquals("secret key = 1", 0, result.getSecretKey().compareTo(BigInteger.ONE));
        assertEquals("public key = 2", 0, result.getPublicKey().compareTo(BigInteger.TWO));
        generator.withLargePrimeNumberPair(null);
    }
}