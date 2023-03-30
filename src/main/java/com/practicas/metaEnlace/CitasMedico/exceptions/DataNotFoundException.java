package com.practicas.metaEnlace.CitasMedico.exceptions;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String error){
        super(error);
    }
}
