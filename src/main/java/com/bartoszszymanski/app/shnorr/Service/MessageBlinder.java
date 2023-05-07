package com.bartoszszymanski.app.shnorr.Service;

import java.math.BigInteger;

public class MessageBlinder extends AbstractLargePrimeNumberPairDependent {
    private final BlindingFactorGenerator blindingFactorGenerator;

    public MessageBlinder(BlindingFactorGenerator blindingFactorGenerator) {
        this.blindingFactorGenerator = blindingFactorGenerator;
    }

    public BigInteger blind(BigInteger message) {
        checkLargeNumberPair();
        blindingFactorGenerator.withLargePrimeNumberPair(largePrimeNumberPair);

        // mBlinded = m * b mod p
        return message
                .multiply(blindingFactorGenerator.generate())
                .mod(largePrimeNumberPair.getP());
    }
}
