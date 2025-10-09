package br.com.link_box_core_back_springboot.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException(String message) {
        super(message);
    }
}