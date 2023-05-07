package com.bartoszszymanski.app.shnorr.Model;

import java.math.BigInteger;

public final class LargePrimeNumberPair {
    public static final int BIT_LENGTH = 1024;
    public static final int CERTAINTY = 64;

    private final BigInteger p;
    private final BigInteger q;

    public LargePrimeNumberPair(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }
}
