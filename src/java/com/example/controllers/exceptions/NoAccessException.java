/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers.exceptions;

/**
 *
 * @author George
 */
public class NoAccessException extends Exception{
    
    public NoAccessException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoAccessException(String message) {
        super(message);
    }
}
