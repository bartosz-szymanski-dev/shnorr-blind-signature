package com.bartoszszymanski.app.shnorr.Service;


import com.bartoszszymanski.app.shnorr.Model.KeyPair;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class SignatureComputer extends AbstractLargePrimeNumberPairDependent {
    private final RandomBigIntegerGenerator randomBigIntegerGenerator;
    private final Hasher hasher;

    private KeyPair keyPair;

    public SignatureComputer(RandomBigIntegerGenerator randomBigIntegerGenerator, Hasher hasher) {
        this.randomBigIntegerGenerator = randomBigIntegerGenerator;
        this.hasher = hasher;
    }

    public void withKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public BigInteger compute(BigInteger messageBlinded) throws NoSuchAlgorithmException {
        checkLargeNumberPair();
        checkKeyPair();

        BigInteger q = largePrimeNumberPair.getQ();
        BigInteger kMaxValue = q.subtract(BigInteger.ONE);
        BigInteger k = randomBigIntegerGenerator.generate(
                BigInteger.ONE,
                kMaxValue
        );
        BigInteger r1 = largePrimeNumberPair
                .getG()
                .modPow(k, largePrimeNumberPair.getP());
        BigInteger h = hasher.computeSha256(
                buildParts(messageBlinded, r1)
        );
        BigInteger x = keyPair.getSecretKey();
        return k
                .subtract(x.multiply(h))
                .mod(q);
    }

    private void checkKeyPair() {
        if (this.keyPair == null) {
            throw new RuntimeException("Key pair should be set");
        }
    }

    private BigInteger[] buildParts(BigInteger messageBlinded, BigInteger r1) {
        return new BigInteger[]{
                largePrimeNumberPair.getP(),
                largePrimeNumberPair.getQ(),
                largePrimeNumberPair.getG(),
                keyPair.getPublicKey(),
                messageBlinded,
                r1,
        };
    }
}
