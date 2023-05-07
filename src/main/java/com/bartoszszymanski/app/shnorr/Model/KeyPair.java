package com.bartoszszymanski.app.shnorr.Model;

import java.math.BigInteger;

public class KeyPair {
    private final BigInteger secretKey;
    private final BigInteger publicKey;

    public KeyPair(BigInteger secretKey, BigInteger publicKey) {
        this.secretKey = secretKey;
        this.publicKey = publicKey;
    }

    public BigInteger getSecretKey() {
        return secretKey;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }
}
