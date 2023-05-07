package com.bartoszszymanski.app.shnorr.Service;

import java.math.BigInteger;

public class SignatureUnblinder extends AbstractLargePrimeNumberPairDependent{
    public BigInteger unblind(BigInteger s, BigInteger r) {
        checkLargeNumberPair();

        return s.multiply(r.modPow(
                BigInteger.valueOf(-1),
                largePrimeNumberPair.getP()
        ));
    }
}
