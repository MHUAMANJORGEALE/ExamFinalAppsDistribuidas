package com.maza.denuncias_service.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.maza.denuncias_service.entity.Denuncia;



public interface DenunciaService {
	public List<Denuncia> findAll(Pageable Page);
	public Denuncia findByDni(String dni);
	public Denuncia findById(int id);
	public Denuncia Save(Denuncia obj);
	public Denuncia Delete(int id);
}
