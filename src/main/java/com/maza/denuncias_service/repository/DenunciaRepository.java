package com.maza.denuncias_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maza.denuncias_service.entity.Denuncia;



public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {
	public Denuncia findByDni(String dni);
}
