package br.com.senac.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.senac.dominio.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u where u.nome = ?1")
	Usuario findByNome(String nome);
}
