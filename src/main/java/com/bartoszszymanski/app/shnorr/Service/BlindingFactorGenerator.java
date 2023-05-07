package com.bartoszszymanski.app.shnorr.Service;

import com.bartoszszymanski.app.shnorr.Model.LargePrimeNumberPair;

import java.math.BigInteger;

public class BlindingFactorGenerator extends AbstractLargePrimeNumberPairDependent {
    private final RandomBigIntegerGenerator randomBigIntegerGenerator;

    public BlindingFactorGenerator(RandomBigIntegerGenerator randomBigIntegerGenerator) {
        this.randomBigIntegerGenerator = randomBigIntegerGenerator;
    }

    public BigInteger generate() {
        checkLargeNumberPair();

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
