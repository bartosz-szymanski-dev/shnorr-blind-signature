package com.bartoszszymanski.app.shnorr.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    public BigInteger computeSha256(BigInteger[] parts) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        for (BigInteger part : parts) {
            sha256.update(part.toByteArray());
        }
        byte[] digest = sha256.digest();
        return new BigInteger(1, digest);
    }
}
