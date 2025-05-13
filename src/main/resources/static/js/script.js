function carregarPresenca() {
	$.ajax({
		url: '/listaPresenca/',
		type: 'GET',
		success: function(presenca) {
			let lista = '';
			presenca.forEach(p => {
				lista += `
             <li class="list-group-item d-flex justify-content-between align-items-start">
             <div>
              <strong id="nome-${p.id}">${p.nome}</strong><br>
              <small id="cargo-${p.id}">${p.cargo}</small><br>
              <small id="empresa-${p.id}">${p.empresa}</small>
             </div>
             <div>
              <button class="btn btn-sm btn-warning me-1" onclick="prepararEdicao(${p.id})">Editar</button>
              <button class="btn btn-sm btn-danger" onclick="excluirPresenca(${p.id})">Excluir</button>
             </div>
           </li>`;
			});
			$('#listaPresenca').html(lista);
		}
	});
}

function adicionarPresenca() {
	const presenca = {
		nome: $('#nomeLista').val(),
		cargo: $('#cargoLista').val(),
		empresa: $('#empresaLista').val()
	};

	$.ajax({
		url: '/listaPresenca/',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(presenca),
		success: function() {
			$('#formLista')[0].reset();
			carregarPresenca();
		}
	});
}

function excluirPresenca(id) {
	if (confirm("Tem certeza de que deseja excluir esta presença?")) {
		$.ajax({
			url: `/listaPresenca/${id}`,
			type: 'DELETE',
			success: function() {
				carregarPresenca();
			}
		});
	}
}

let idEditando = null;

function prepararEdicao(id) {
	const nome = $(`#nome-${id}`).text();
	const cargo = $(`#cargo-${id}`).text();
	const empresa = $(`#empresa-${id}`).text();

	$('#nomeEdicao').val(nome);
	$('#cargoEdicao').val(cargo);
	$('#empresaEdicao').val(empresa);
	idEditando = id;

	const modal = new bootstrap.Modal(document.getElementById('modalEdicao'));
	modal.show();
}

function atualizarPresenca() {
	const presencaAtualizada = {
		nome: $('#nomeEdicao').val(),
		cargo: $('#cargoEdicao').val(),
		empresa: $('#empresaEdicao').val()
	};

	$.ajax({
		url: `/listaPresenca/${idEditando}`,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify(presencaAtualizada),
		success: function() {
			carregarPresenca();
			idEditando = null;
			
			
			const modal = bootstrap.Modal.getInstance(document.getElementById('modalEdicao'));
			modal.hide();
		}
	});
}

$(document).ready(function() {
	carregarPresenca();
});
