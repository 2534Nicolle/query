package com.controlePresenca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controlePresenca.entity.ListaPresenca;
import com.controlePresenca.service.ListaPresencaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Módulo Lista de Presença", description = "API REST - GERENCIAMENTO DE LISTAS DE PRESENÇA")
@RestController
@RequestMapping("/listaPresenca")
public class ListaPresencaController {

    private final ListaPresencaService listaPresencaService;

    @Autowired
    public ListaPresencaController(ListaPresencaService listaPresencaService) {
        this.listaPresencaService = listaPresencaService;
    }

    @Operation(summary = "Buscar lista de presença por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ListaPresenca> getListaPresencaById(@PathVariable Long id) {
        ListaPresenca lista = listaPresencaService.getListaPresencaById(id);
        return lista != null ? ResponseEntity.ok(lista) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar todas as listas de presença")
    @GetMapping
    public ResponseEntity<List<ListaPresenca>> getAllListasPresenca() {
        List<ListaPresenca> listas = listaPresencaService.getAllListasPresenca();
        return ResponseEntity.ok(listas);
    }

    @Operation(summary = "Criar nova lista de presença")
    @PostMapping
    public ResponseEntity<ListaPresenca> criarListaPresenca(@RequestBody @Valid ListaPresenca listaPresenca) {
        ListaPresenca criada = listaPresencaService.salvarListaPresenca(listaPresenca);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @Operation(summary = "Atualizar lista de presença por ID")
    @PutMapping("/{id}")
    public ResponseEntity<ListaPresenca> updateListaPresenca(@PathVariable Long id, @RequestBody @Valid ListaPresenca listaPresenca) {
        ListaPresenca atualizada = listaPresencaService.updateListaPresenca(id, listaPresenca);
        return atualizada != null ? ResponseEntity.ok(atualizada) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deletar lista de presença por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListaPresenca(@PathVariable Long id) {
        boolean deleted = listaPresencaService.deleteListaPresenca(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
