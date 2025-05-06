package com.controlePresenca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlePresenca.entity.ListaPresenca;

public interface ListaPresencaRepository extends JpaRepository<ListaPresenca, Long> {
	List<ListaPresenca> findByNome(String nome);
	List<ListaPresenca> findByCargo(String cargo);
}
