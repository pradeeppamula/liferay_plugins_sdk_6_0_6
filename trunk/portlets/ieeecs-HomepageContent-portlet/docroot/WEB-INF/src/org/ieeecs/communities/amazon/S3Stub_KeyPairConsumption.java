package org.ieeecs.communities.amazon;

import java.io.IOException;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.util.encoders.Base64;


public class S3Stub_KeyPairConsumption {

    public static void main(String[] args) throws IOException {
   
        try {
 
        	// Get these from whatever secure storage mechanism you're using. 
        	// You can use the "S3Stub_KeyPairGeneration" class to generate a KeyPair if you'd like.
        	String base64PublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDL+eGns3OujdAI4IiJc9Csj8puiLPc+X7w5veoK1YrplTapJc9lY9/l/HCbyF4iPz5HrKQeOngGQ8v6Dq8ruYLfEkCMNfI7w/zPNAjfqnp4pi4XztXOBqWoTjv6YTzozzD9qqoquphLuhJXo07P5ORNG+0fuGMj5zwr0ppWpovTwIDAQAB";
        	String base64PrivateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMv54aezc66N0AjgiIlz0KyPym6Is9z5fvDm96grViumVNqklz2Vj3+X8cJvIXiI/PkespB46eAZDy/oOryu5gt8SQIw18jvD/M80CN+qenimLhfO1c4GpahOO/phPOjPMP2qqiq6mEu6ElejTs/k5E0b7R+4YyPnPCvSmlami9PAgMBAAECgYEAxMfAG+Jy0s47CaVb84cIpMFfoL0/EAqenVelStbsHdYsfHZW9fGoa4axlEtT9g3qR4eG6vvLXQev8B7RM6Bjiq02AZoy/ocgNpy4FCopClZisAsaRbVkgKRXXJyRJmaJiGq/ulW+sFpwOR2ktrvnaLOxJdFbX6+5vjqYiOA7JlkCQQDz7u3gchtfLDvw3RmpRhuvbyRiupvNO5nIUxHaFdqE+VGZ46tlnd0AtcwZP2hRN9gsIPCYtEFmCfjQoZ6s4LP9AkEA1hDzV4qS6CSr73RdbMINgTYg2l08KWz6netyelI+rq43P9zSRanIBHUdKM6/ZFrF2e03y+sOKdC3Vb4H8P/EOwJBAJn1r8X9vzDuplZ79npUeZzctUZrKXL7rzA03gubx3QuOEa8360fWiHcnJlC4ACDErmngLOg8bYJjTWBDLloibECQQCroUGJoagxih10SocRSPDM9VoX2gL/b8nsTbcmTcTGBxZzFJhelEazFDcB3enkC0dwd9pxm49qhVZI36i8WES7AkAubxmoeI8syomnifIp/oV/K3UaUg4DDj5b9FwaHUsTzFN/EtUI/tkruE9+Nl3CzAalGUEQvSSgYrKCE8KOh1xk";
        	
        	PublicKey publicKey = getPublicKey(base64PublicKey);
        	PrivateKey privateKey = getPrivateKey(base64PrivateKey);
        	KeyPair keyPair = new KeyPair(publicKey, privateKey);
       
        	// You now have a KeyPair you can use in your code.
        	// See "S3Stub_ConvertAndUploadFile" for an actual example of real usage.
        	// See "S3Stub_ExtractFileAndConvert" for an actual example of real usage.

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        System.out.println("---Process is complete---");
    }
    
    public static PublicKey getPublicKey(String publicKeyString) {
    	
    	PublicKey publicKey = null;
    	
    	try {
    		
	        byte[] keyBytes = (byte[]) Base64.decode(publicKeyString);	        
	        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);	        
	        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        publicKey = keyFactory.generatePublic(x509KeySpec);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String privateKeyString) {
    	
    	PrivateKey privateKey = null;
    
        try {
        	
            byte[] keyBytes = (byte[]) Base64.decode(privateKeyString);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFact = KeyFactory.getInstance("RSA");
            privateKey = keyFact.generatePrivate(pkcs8KeySpec);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return privateKey;
    }
}