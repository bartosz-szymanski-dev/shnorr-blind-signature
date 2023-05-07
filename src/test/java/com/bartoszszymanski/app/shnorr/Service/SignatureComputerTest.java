package com.bartoszszymanski.app.shnorr.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import com.bartoszszymanski.app.shnorr.Model.LargePrimeNumberPair;
import org.junit.Before;
import org.junit.Test;

import com.bartoszszymanski.app.shnorr.Model.KeyPair;

public class SignatureComputerTest {
    private static final BigInteger P = new BigInteger("541");
    private static final BigInteger Q = new BigInteger("270");
    private static final BigInteger G = new BigInteger("225");
    private static final BigInteger X = new BigInteger("258");
    private static final BigInteger K = new BigInteger("131");
    private static final BigInteger H = new BigInteger("13966777524255989240991348423806297957756739135183424957089040283825518815867");

    private RandomBigIntegerGenerator randomBigIntegerGenerator;
    private Hasher hasher;
    private SignatureComputer signatureComputer;

    @Before
    public void setUp() {
        randomBigIntegerGenerator = mock(RandomBigIntegerGenerator.class);
        hasher = mock(Hasher.class);
        signatureComputer = new SignatureComputer(randomBigIntegerGenerator, hasher);
    }

    @Test
    public void testComputeSignature() throws NoSuchAlgorithmException {
        BigInteger y = G.modPow(X, P);
        BigInteger mBlinded = new BigInteger("123");

        KeyPair keyPair = new KeyPair(X, y);
        signatureComputer.withKeyPair(keyPair);

        BigInteger r1 = G.modPow(K, P);
        BigInteger[] messageParts = {P, Q, G, y, mBlinded, r1};
        BigInteger s = new BigInteger("95");

        when(randomBigIntegerGenerator.generate(BigInteger.ONE, Q.subtract(BigInteger.ONE)))
                .thenReturn(K);
        when(hasher.computeSha256(messageParts))
                .thenReturn(H);

        signatureComputer.withLargePrimeNumberPair(new LargePrimeNumberPair(P, Q, G));
        assertEquals(s, signatureComputer.compute(mBlinded));
    }

    @Test(expected = RuntimeException.class)
    public void testComputeSignatureWithoutKeyPair() throws NoSuchAlgorithmException {
        BigInteger mBlinded = new BigInteger("123");

        signatureComputer.compute(mBlinded);
    }
}
