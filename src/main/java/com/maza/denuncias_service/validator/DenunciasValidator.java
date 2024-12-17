package com.maza.denuncias_service.validator;

import com.maza.denuncias_service.entity.Denuncia;
import com.maza.denuncias_service.exception.ValidateServiceException;

public class DenunciasValidator {
    public static void save(Denuncia obj) {
        if (obj.getDni() == null || obj.getDni().trim().isEmpty()) {
            throw new ValidateServiceException("The dni is required");
        }
        if (obj.getDni().length() > 10) {
            throw new ValidateServiceException("The dni must not exceed 10 characters");
        }
    }
}
