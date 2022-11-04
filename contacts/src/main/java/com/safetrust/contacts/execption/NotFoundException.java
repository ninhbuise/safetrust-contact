package com.safetrust.contacts.execption;

public class NotFoundException extends RuntimeException{

  public NotFoundException(final String message) {
    super(message);
  }
}
