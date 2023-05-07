package com.bartoszszymanski.app.shnorr.Model;

import java.math.BigInteger;

public final class LargePrimeNumberPair {
    public static final int BIT_LENGTH = 1024;

    private final BigInteger p;
    private final BigInteger q;
    private final BigInteger g;

    public LargePrimeNumberPair(BigInteger p, BigInteger q, BigInteger g) {
        this.p = p;
        this.q = q;
        this.g = g;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getG() {
        return g;
    }
}
