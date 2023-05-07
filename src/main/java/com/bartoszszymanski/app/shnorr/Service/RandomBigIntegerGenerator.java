package com.bartoszszymanski.app.shnorr.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomBigIntegerGenerator {
    public BigInteger generate(BigInteger min, BigInteger max) {
        checkMinAndMax(min, max);

        BigInteger range = max.subtract(min);
        SecureRandom random = new SecureRandom();
        int maxNumberBitLength = range.bitLength();
        BigInteger randomBigInteger = new BigInteger(maxNumberBitLength, random);
        if (randomBigInteger.compareTo(min) < 0) {
            randomBigInteger = randomBigInteger.add(min);
        }

        if (randomBigInteger.compareTo(max) >= 0) {
            randomBigInteger = randomBigInteger.mod(range).add(min);
        }

        return randomBigInteger;
    }

    private void checkMinAndMax(BigInteger min, BigInteger max) {
        if (min.compareTo(max) == 0) {
            throw new RuntimeException("Min and max should not be equal");
        }

        if (min.compareTo(BigInteger.ZERO) <= 0) {
            throw new RuntimeException("Min value should not be equal or less than 0");
        }

        if (max.compareTo(BigInteger.ZERO) <= 0) {
            throw new RuntimeException("Max value should not be equal or less than 0");
        }

        if (max.compareTo(min) < 0) {
            throw new RuntimeException("Max should not be less than min");
        }
    }
}
