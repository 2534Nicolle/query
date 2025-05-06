package com.controlePresenca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlePresenca.entity.ListaPresenca;
import com.controlePresenca.repository.ListaPresencaRepository;

@Service
public class ListaPresencaService {
	private final ListaPresencaRepository listaPresencaRepository;
	
	@Autowired
	public ListaPresencaService(ListaPresencaRepository listaPresencaRepository) {
		this.listaPresencaRepository = listaPresencaRepository;
	}
	public List<ListaPresenca> getAllListasPresenca() {
		return listaPresencaRepository.findAll();
	}
	public ListaPresenca getListaPresencaById(Long id) {
		Optional <ListaPresenca> listaPresenca = listaPresencaRepository.findById(id);
		return listaPresenca.orElse(null);
	}
	
	public ListaPresenca salvarListaPresenca(ListaPresenca listaPresenca) {
		return listaPresencaRepository.save(listaPresenca);
	}
	
	public ListaPresenca updateListaPresenca(Long id, ListaPresenca updatedListaPresenca) {
		Optional<ListaPresenca> existingListaPresenca = listaPresencaRepository.findById(id);
		if(existingListaPresenca.isPresent()) {
			updatedListaPresenca.setId(id);
			return listaPresencaRepository.save(updatedListaPresenca);
		}
		return null;
	}

	public boolean deleteListaPresenca (Long id) {
		Optional<ListaPresenca> existingListaPresenca = listaPresencaRepository.findById(id);
		if (existingListaPresenca.isPresent()) {
			listaPresencaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}