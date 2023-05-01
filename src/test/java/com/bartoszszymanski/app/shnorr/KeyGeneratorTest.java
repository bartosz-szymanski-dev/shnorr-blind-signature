package com.bartoszszymanski.app.shnorr;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class KeyGeneratorTest {
    @Test
    public void testGenerateKeyPair() {
        KeyGenerator keyGenService = new KeyGenerator();
        keyGenService.generateKeyPair();

        BigInteger privateKey = keyGenService.getPrivateKey();
        assertNotNull(privateKey);

        BigInteger publicKey = keyGenService.getPublicKey();
        assertNotNull(publicKey);
    }

    @Test
    public void testPublicKeyIsGeneratorRaisedToPrivateKey() {
        KeyGenerator keyGenService = new KeyGenerator();
        keyGenService.generateKeyPair();

        BigInteger p = keyGenService.getP();
        BigInteger g = keyGenService.getG();
        BigInteger x = keyGenService.getPrivateKey();
        BigInteger y = keyGenService.getPublicKey();

        assertEquals(g.modPow(x, p), y);
    }

    @Test
    public void testPrivateKeyIsValid() {
        KeyGenerator keyGenService = new KeyGenerator();
        keyGenService.generateKeyPair();

        BigInteger p = keyGenService.getP();
        BigInteger x = keyGenService.getPrivateKey();

        assertTrue(x.compareTo(BigInteger.ZERO) > 0 && x.compareTo(p.subtract(BigInteger.ONE)) < 0);
    }

    @Test
    public void testPublicKeyIsValid() {
        KeyGenerator keyGenService = new KeyGenerator();
        keyGenService.generateKeyPair();

        BigInteger p = keyGenService.getP();
        BigInteger g = keyGenService.getG();
        BigInteger y = keyGenService.getPublicKey();

        assertTrue(y.compareTo(BigInteger.ZERO) > 0 && y.compareTo(p.subtract(BigInteger.ONE)) < 0);
        BigInteger pMinusOneDividedBy2 = p.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2));
        assertEquals(g.modPow(pMinusOneDividedBy2, p), BigInteger.ONE);
        assertNotEquals(g.modPow(pMinusOneDividedBy2.add(BigInteger.ONE), p), BigInteger.ONE);
    }
}