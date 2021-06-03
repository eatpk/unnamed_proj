package src.util;

import src.spacestation.TransitCard;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class HashUtil
{
    private static final int iterations = 10;
    private static final int desiredKeyLen = 256;
    static byte[] salt = new byte[]{1,2,3,4,5,6,7};

    // This method returns the hash value of a TransitCard id, florple balance, and transaction history
    public static String hash( TransitCard card ) {
      String hash = null;

      try
      {
        String stringToHash = card.toHashableString();

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA1" );
        SecretKey key = keyFactory.generateSecret(
            new PBEKeySpec(
                stringToHash.toCharArray(),
                salt,
                iterations,
                desiredKeyLen
            )
        );

        hash = Base64.getEncoder().encodeToString( key.getEncoded() );
      } catch ( NoSuchAlgorithmException | InvalidKeySpecException e) {
        System.err.println( e.toString() );
      }

      return hash;
    }
}
