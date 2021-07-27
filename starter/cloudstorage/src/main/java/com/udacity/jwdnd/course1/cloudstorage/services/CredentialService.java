package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    CredentialMapper credentialMapper;
    EncryptionService encryptionService;


    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentials(Integer userid) {
        return credentialMapper.selectCredentialsByUserid(userid);
    }

    public int addCredential(CredentialForm credentialForm, Integer userid) {
        if (credentialForm.getCredentialid() != null) {
            return editCredential(credentialForm,userid);
        }
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), encodedKey);
        return credentialMapper.insertCredential(new Credential(null,
                credentialForm.getUrl(),
                credentialForm.getUsername(),
                encodedKey,
                encryptedPassword,
                userid));
    }

    public int editCredential(CredentialForm credentialForm, Integer userid) {
        String encodedKey = credentialMapper.selectCredentialById(credentialForm.getCredentialid()).getKey();
        String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), encodedKey);
        return credentialMapper.updateCredential(new Credential(credentialForm.getCredentialid(),
                credentialForm.getUrl(),
                credentialForm.getUsername(),
                encodedKey,
                encryptedPassword,
                userid));
    }

    public boolean deleteCredential(Integer id) {
        credentialMapper.deleteCredential(id);
        return credentialMapper.selectCredentialById(id) == null;
    }

    public List<CredentialForm> getAllCredentialForms(Integer userid) {
        List<CredentialForm> credentialFormList = new ArrayList<>();
        List<Credential> credentialList = getCredentials(userid);
        for (Credential credential : credentialList) {
            String encodedKey = credential.getKey();
            String password = encryptionService.decryptValue(credential.getPassword(),encodedKey);
            credentialFormList.add(new CredentialForm(credential.getCredentialid(),
                    credential.getUrl(),
                    credential.getUsername(),
                    password,
                    credential.getPassword()));
        }
        return credentialFormList;
    }
}
