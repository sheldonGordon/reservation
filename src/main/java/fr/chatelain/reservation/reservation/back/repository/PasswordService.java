package fr.chatelain.reservation.reservation.back.repository;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.Password;

@Service
public class PasswordService {

    private static final int NB_ITERATION = 65536;

    private static final int TAILLE_CLE = 128;

    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    @Autowired
    private PasswordRepository passwordRepository;

    private byte[] saltFactory() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public byte[] getHashPassword(String password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, NB_ITERATION, TAILLE_CLE);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return hash;
    }

    public String getEncode(byte[] data) {
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);
    }

    public byte[] getDecode(String data) {
        Decoder decoder = Base64.getDecoder();
        return decoder.decode(data);
    }

    public Password save(String saisiePassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (saisiePassword != null && !saisiePassword.isEmpty()) {
            byte[] salt = saltFactory();
            byte[] hash = getHashPassword(saisiePassword, salt);
            Password password = new Password(getEncode(hash), getEncode(salt));
            passwordRepository.save(password);
            return password;
        } else {
            throw new NullPointerException("Le mot de passe ne peut être vide.");
        }
    }

    public Password findById(String id) {
        return passwordRepository.findById(id).get();
    }
}
