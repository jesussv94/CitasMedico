package com.practicas.metaEnlace.CitasMedico.exceptions;

public class DuplicateDataException extends RuntimeException {
        public DuplicateDataException(String error){
            super(error);
        }
}
