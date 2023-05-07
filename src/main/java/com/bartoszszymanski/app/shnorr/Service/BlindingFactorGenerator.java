package com.bartoszszymanski.app.shnorr.Service;

import com.bartoszszymanski.app.shnorr.Model.LargePrimeNumberPair;

import java.math.BigInteger;

public class BlindingFactorGenerator {
    private final RandomBigIntegerGenerator randomBigIntegerGenerator;

    private LargePrimeNumberPair largePrimeNumberPair;

    public BlindingFactorGenerator(RandomBigIntegerGenerator randomBigIntegerGenerator) {
        this.randomBigIntegerGenerator = randomBigIntegerGenerator;
    }

    public BlindingFactorGenerator withLargePrimeNumberPair(LargePrimeNumberPair largePrimeNumberPair) {
        this.largePrimeNumberPair = largePrimeNumberPair;
        return this;
    }

    public BigInteger generate() {
        BigInteger p = largePrimeNumberPair.getP();
        BigInteger r = randomBigIntegerGenerator.generate(
                BigInteger.ONE,
                p.subtract(BigInteger.ONE)
        );
        // b = g^r mod p
        return largePrimeNumberPair
                .getG()
                .modPow(r, p);
    }
}
