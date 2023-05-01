package com.bartoszszymanski.app.shnorr;

import java.math.BigInteger;
import java.security.SecureRandom;

public class KeyGenerator {
    private final SecureRandom secureRandom;
    private BigInteger p;
    private BigInteger g;
    private BigInteger x;
    private BigInteger y;

    public KeyGenerator() {
        // Create a secure random number generator
        secureRandom = new SecureRandom();
    }

    public void generateKeyPair() {
        // Generate a prime p and a generator g
        p = generatePrime(256);
        g = findGenerator(p);

        // Generate a random number x and compute y = g^x mod p
        x = new BigInteger(p.bitLength() - 1, secureRandom);
        y = g.modPow(x, p);
    }

    public BigInteger getPublicKey() {
        // Return the public key y
        return y;
    }

    public BigInteger getPrivateKey() {
        // Return the private key x
        return x;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getG() {
        return g;
    }

    // Helper method to generate a prime number of a specified bit length
    private BigInteger generatePrime(int bitLength) {
        return BigInteger.probablePrime(bitLength, secureRandom);
    }

    // Helper method to find a generator of a prime p
    private BigInteger findGenerator(BigInteger p) {
        BigInteger q = p.subtract(BigInteger.ONE).divide(new BigInteger("2"));
        while (true) {
            BigInteger g = new BigInteger(p.bitLength() - 1, secureRandom);
            if (g.modPow(q, p).equals(BigInteger.ONE) && !g.modPow(q.divide(new BigInteger("2")), p).equals(BigInteger.ONE)) {
                return g;
            }
        }
    }
}
