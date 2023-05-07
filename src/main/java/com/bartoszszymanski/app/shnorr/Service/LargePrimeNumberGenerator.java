package com.bartoszszymanski.app.shnorr.Service;

import com.bartoszszymanski.app.shnorr.Model.LargePrimeNumberPair;

import java.math.BigInteger;
import java.security.SecureRandom;

public class LargePrimeNumberGenerator {
    private static final SecureRandom random = new SecureRandom();

    public LargePrimeNumberPair generate() {
        BigInteger p, q, g, h;
        do {
            p = BigInteger.probablePrime(LargePrimeNumberPair.BIT_LENGTH + 1, random);
            q = calculateQ(p);
            g = findGenerator(p, q);
            h = g.modPow(p.subtract(BigInteger.ONE).divide(q), p);
        } while (h.equals(BigInteger.ONE));

        return new LargePrimeNumberPair(p, q, g);
    }

    private BigInteger calculateQ(BigInteger p) {
        return p
                .subtract(BigInteger.ONE)
                .divide(BigInteger.valueOf(2))
                .nextProbablePrime();
    }

    private BigInteger findGenerator(BigInteger p, BigInteger q) {
        BigInteger g;
        do {
            g = new BigInteger(p.bitLength(), random);
        } while (g.compareTo(BigInteger.ONE) <= 0 || g.compareTo(p.subtract(BigInteger.ONE)) >= 0);
        return g.modPow(
                p.subtract(BigInteger.ONE).divide(q),
                p
        );
    }
}
