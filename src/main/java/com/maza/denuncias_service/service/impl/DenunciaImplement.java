package com.maza.denuncias_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maza.denuncias_service.entity.Denuncia;
import com.maza.denuncias_service.exception.GeneralServiceException;
import com.maza.denuncias_service.exception.NoDataFoundException;
import com.maza.denuncias_service.exception.ValidateServiceException;
import com.maza.denuncias_service.repository.DenunciaRepository;
import com.maza.denuncias_service.service.DenunciaService;
import com.maza.denuncias_service.validator.DenunciasValidator;



@Service

public class DenunciaImplement implements DenunciaService{
    @Autowired
    private DenunciaRepository repository;

    @Override
    @Transactional(readOnly = true)
	public List<Denuncia> findAll(Pageable Page) {
		 try {
	            return repository.findAll(Page).toList();
	        } catch (ValidateServiceException | NoDataFoundException e) {
	            throw e;
	        } catch (Exception e) {
	            throw new GeneralServiceException(e.getMessage(), e);
	        }
	}

	@Override
    @Transactional(readOnly = true)
	public Denuncia findByDni(String dni) {
		try {
            return repository.findByDni(dni);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
	}

	@Override
    @Transactional(readOnly = true)
	public Denuncia findById(int id) {
		try {
            return repository.findById(id).orElseThrow(
                () -> new NoDataFoundException("No Denuncia found with the specified ID")
            );
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
	}

	@Override
	@Transactional
	public Denuncia Save(Denuncia obj) {
		try {
            DenunciasValidator.save(obj);
            if (obj.getId() == 0) {
            	Denuncia Denuncianew=repository.save(obj);
                return Denuncianew;
            }
            Denuncia existingDenuncia = findById(obj.getId());
            existingDenuncia.setDescripcion(obj.getDescripcion());
            existingDenuncia.setDireccion(obj.getDireccion());
            return repository.save(existingDenuncia);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
	}

	@Override
	public Denuncia Delete(int id) {
		try {
            Denuncia Denuncia = findById(id);
            repository.delete(Denuncia);
            return Denuncia;
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
	}
}
