package com.bartoszszymanski.app.shnorr.Service;

import com.bartoszszymanski.app.shnorr.Model.KeyPair;
import com.bartoszszymanski.app.shnorr.Model.LargePrimeNumberPair;

import java.math.BigInteger;

public class KeyPairGenerator extends AbstractLargePrimeNumberPairDependent {
    private final RandomBigIntegerGenerator randomBigIntegerGenerator;

    public KeyPairGenerator(RandomBigIntegerGenerator randomBigIntegerGenerator) {
        this.randomBigIntegerGenerator = randomBigIntegerGenerator;
    }

    public KeyPair generate() {
        checkLargeNumberPair();
        BigInteger secretKey = computeSecretKey();

        return new KeyPair(
                secretKey,
                computePublicKey(secretKey)
        );
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
