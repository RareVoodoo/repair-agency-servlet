package ua.testing.repairagency.util;

import org.jasypt.util.text.AES256TextEncryptor;

public class PasswordEncryptor {
    private volatile static PasswordEncryptor instance;
        AES256TextEncryptor encryptor = new AES256TextEncryptor();

    {
        encryptor.setPassword("G&^T&!S==1=s&Y&*Jik");
    }
    private PasswordEncryptor(){ }



    public static PasswordEncryptor getInstance(){
        if(instance == null){
            synchronized (PasswordEncryptor.class){
                if(instance == null){
                    instance = new PasswordEncryptor();
                }
            }
        }
        return instance;
    }


    public String encrypt(String userPassword){
        return encryptor.encrypt(userPassword);
    }
    public String decrypt(String userPassword){
        return encryptor.decrypt(userPassword);
    }

}
