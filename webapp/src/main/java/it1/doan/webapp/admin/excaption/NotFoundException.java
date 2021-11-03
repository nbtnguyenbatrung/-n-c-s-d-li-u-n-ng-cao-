package it1.doan.webapp.admin.excaption;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String messege){
        super(messege);
    }
}
