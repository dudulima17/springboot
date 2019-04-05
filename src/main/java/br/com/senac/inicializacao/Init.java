package br.com.senac.inicializacao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.dominio.Aluno;
import br.com.senac.dominio.Categoria;
import br.com.senac.dominio.Cidade;
import br.com.senac.dominio.Curso;
import br.com.senac.dominio.Endereco;
import br.com.senac.dominio.Estado;
import br.com.senac.dominio.Usuario;
import br.com.senac.repositorio.AlunoRepositorio;
import br.com.senac.repositorio.CategoriaRepositorio;
import br.com.senac.repositorio.CidadeRepositorio;
import br.com.senac.repositorio.CursoRepositorio;
import br.com.senac.repositorio.EnderecoRepositorio;
import br.com.senac.repositorio.EstadoRepositorio;
import br.com.senac.repositorio.UsuarioRepositorio;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	AlunoRepositorio alunoRepositorio;

	@Autowired
	EstadoRepositorio estadoRepositorio;

	@Autowired
	CidadeRepositorio cidadeRepositorio;

	@Autowired
	EnderecoRepositorio enderecoRepositorio;

	@Autowired
	UsuarioRepositorio usuarioRepositorio;

	@Autowired
	CategoriaRepositorio categoriaRepositorio;

	@Autowired
	CursoRepositorio cursoRepositorio;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Lucas");
		aluno1.setEmail("lucas@gmail.com");

		System.out.println("Iniciliazando a aplicacao");

		// Aluno alunoGravado = alunoRepositorio.findByEmail("lucas@gmail.com");

		Estado estado1 = new Estado();
		estado1.setNome("Rio de Janeiro");

		Estado estado2 = new Estado();
		estado1.setNome("São Paulo");

		Cidade cidade1 = new Cidade();
		cidade1.setNome("Angra dos Reis");
		cidade1.setEstado(estado2);

		Cidade cidade2 = new Cidade();
		cidade2.setNome("Mogi das Cluzes");
		cidade2.setEstado(estado2);

		Cidade cidade3 = new Cidade();
		cidade3.setNome("Cabo Frio");
		cidade3.setEstado(estado1);

		estadoRepositorio.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepositorio.saveAll(Arrays.asList(cidade1, cidade2, cidade3));

		Endereco end1 = new Endereco();
		end1.setLogradouro("Rua ... ");
		end1.setNumero("123");
		end1.setBairro("Centro");
		end1.setCep("520545-551");
		end1.setCidade(cidade1);
		end1.setAluno(aluno1);

		Endereco end2 = new Endereco();
		end2.setLogradouro("Rua b ... ");
		end2.setNumero("321");
		end2.setBairro("tijuca");
		end2.setCep("1231213-551");
		end2.setCidade(cidade2);
		end2.setAluno(aluno1);

		aluno1.getTelefones().addAll(Arrays.asList("21998877545", "1221455225"));

		alunoRepositorio.save(aluno1);

		enderecoRepositorio.saveAll(Arrays.asList(end1, end2));
		Usuario user1 = new Usuario();

		user1.setNome("User 1");
		user1.setEmail("user@gmail.com");
		user1.setSenha("123456");
		user1.setSobrenome("Faustino");

		usuarioRepositorio.save(user1);
		Usuario userGravado = usuarioRepositorio.findByEmail("user@gmail.com");

		System.out.println("################Usuario gravado " + userGravado.getNome());

		// Criando categoria

		Categoria categoria1 = new Categoria(null, "Java");
		Categoria categoria2 = new Categoria(null, "Mobile");

		categoriaRepositorio.saveAll(Arrays.asList(categoria1, categoria2));

		// criando curso

		Curso curso1 = new Curso(null, "Java", "Java para Iniciante", 200.00);
		Curso curso2 = new Curso(null, "Java II", "Java para Intermediario", 400.00);

		curso1.setCategorias(Arrays.asList(categoria1, categoria2));
		curso2.setCategorias(Arrays.asList(categoria1));

		cursoRepositorio.saveAll(Arrays.asList(curso1, curso2));
	}
}
