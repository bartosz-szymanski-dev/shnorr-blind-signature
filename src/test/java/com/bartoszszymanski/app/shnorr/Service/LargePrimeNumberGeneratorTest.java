package com.bartoszszymanski.app.shnorr.Service;

import com.bartoszszymanski.app.shnorr.Model.LargePrimeNumberPair;
import org.junit.Test;

import static org.junit.Assert.*;

public class LargePrimeNumberGeneratorTest {
    @Test
    public void testGenerate() {
        LargePrimeNumberGenerator generator = new LargePrimeNumberGenerator();
        LargePrimeNumberPair largePrimeNumberPair = generator.generate();
        assertTrue("p is probable prime", largePrimeNumberPair.getP().isProbablePrime(64));
        assertTrue("q is probable prime", largePrimeNumberPair.getQ().isProbablePrime(64));
    }
}