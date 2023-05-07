package com.bartoszszymanski.app.shnorr.Service;

import com.bartoszszymanski.app.shnorr.Model.KeyPair;
import com.bartoszszymanski.app.shnorr.Model.LargePrimeNumberPair;

import java.math.BigInteger;

public class KeyPairGenerator {
    private final RandomBigIntegerGenerator randomBigIntegerGenerator;
    private LargePrimeNumberPair largePrimeNumberPair;

    public KeyPairGenerator(RandomBigIntegerGenerator randomBigIntegerGenerator) {
        this.randomBigIntegerGenerator = randomBigIntegerGenerator;
    }

    public KeyPairGenerator withLargePrimeNumberPair(LargePrimeNumberPair largePrimeNumberPair) {
        this.largePrimeNumberPair = largePrimeNumberPair;
        return this;
    }

    public KeyPair generate() {
        checkLargeNumberPair();
        BigInteger secretKey = computeSecretKey();

        return new KeyPair(
                secretKey,
                computePublicKey(secretKey)
        );
    }

    private void checkLargeNumberPair() {
        if (this.largePrimeNumberPair == null) {
            throw new RuntimeException("Large prime number pair should be set");
        }
    }

    private BigInteger computeSecretKey() {
        BigInteger min = BigInteger.ONE;
        BigInteger max = largePrimeNumberPair
                .getP()
                .subtract(BigInteger.ONE);
        return randomBigIntegerGenerator.generate(min, max);
    }

    private BigInteger computePublicKey(BigInteger x) {
        // y = g^x mod p
        return largePrimeNumberPair
                .getG()
                .modPow(x, largePrimeNumberPair.getP());
    }
}
