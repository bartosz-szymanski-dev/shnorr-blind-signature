package com.bartoszszymanski.app.shnorr.Service;

import com.bartoszszymanski.app.shnorr.Model.KeyPair;

import java.math.BigInteger;

public class SignatureVerifier extends AbstractLargePrimeNumberPairDependent {
    private KeyPair keyPair;

    public void withKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public boolean isVerified(BigInteger r1, BigInteger signatureUnblinded) {
        checkLargeNumberPair();
        checkKeyPair();
        BigInteger r2 = keyPair
                .getPublicKey(); // TODO

        return true;
    }

    private void checkKeyPair() {
        if (this.keyPair == null) {
            throw new RuntimeException("Key pair should be set");
        }
    }
}
