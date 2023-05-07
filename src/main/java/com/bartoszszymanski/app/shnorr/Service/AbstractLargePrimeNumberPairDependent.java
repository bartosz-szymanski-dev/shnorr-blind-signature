package com.bartoszszymanski.app.shnorr.Service;

import com.bartoszszymanski.app.shnorr.Model.LargePrimeNumberPair;

abstract public class AbstractLargePrimeNumberPairDependent {
    protected LargePrimeNumberPair largePrimeNumberPair;

    public void withLargePrimeNumberPair(LargePrimeNumberPair largePrimeNumberPair) {
        this.largePrimeNumberPair = largePrimeNumberPair;
    }

    protected void checkLargeNumberPair() {
        if (this.largePrimeNumberPair == null) {
            throw new RuntimeException("Large prime number pair should be set");
        }
    }
}
