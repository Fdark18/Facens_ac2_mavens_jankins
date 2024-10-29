package com.ac1.gerenciador;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ac1.aluno.Aluno;
import com.ac1.aluno.TipoConta;
import com.ac1.curso.Curso;
import com.ac1.curso.CursoMatriculado;
import com.ac1.curso.StatusCurso;

@SpringBootTest
class GerenciadorTest {
	private Curso cursoExcel = new Curso("Curso Excel Avançado 2025"); 
	private Curso cursoEdicaoVideo = new Curso("Curso de Adobe Premiere 2024");
	private Curso cursoBootstrap = new Curso("Curso de Bootstrap Iniciante");
	private Curso cursoJogos = new Curso("Curso Unity Engine Iniciante");
	
	private Gerenciador gerenciador = new Gerenciador();
	
	@Test
	@DisplayName("O aluno deve ganhar mais 3 cursos caso concluir um curso com uma nota superior ou igual a 7")
	void alunoPassou() {
		//Declara um novo aluno com um curso matriculado
		Aluno aluno = new Aluno("Jhonatan", 200250, TipoConta.BÁSICA);
		aluno.MatricularCurso(cursoExcel);
		
		//Declara uma nota para o curso do aluno e finaliza ele
		CursoMatriculado cursoMatriculado = aluno.getCursosMatriculados().get(0);
		cursoMatriculado.setNota(9.8f);
		cursoMatriculado.setStatus(StatusCurso.CONCLUÍDO);
		
		//Adiciona alguns cursos no backlog do gerenciador (seria o equivalente a biblioteca de cursos do projeto)
		gerenciador.AdicionarCurso(cursoEdicaoVideo);
		gerenciador.AdicionarCurso(cursoBootstrap);
		gerenciador.AdicionarCurso(cursoJogos);
		
		var quantidadeCursosAntiga = aluno.getCursosMatriculados().size();
		
		//verificar se foi presentado com os cursos
		assertEquals(gerenciador.VerificarSeGanhouCursos(aluno, cursoExcel), true);
		
		var novaQuantidadeDeCursos = aluno.getCursosMatriculados().size();
		
		//verificar se novos cursos foram adicionados na lista de cursos do aluno
		assertEquals((novaQuantidadeDeCursos - quantidadeCursosAntiga) == 3, true);
		
		//verificar se foi aprovado
		assertEquals(cursoMatriculado.getStatus() == StatusCurso.APROVADO, true);
	}
	
	@Test
	@DisplayName("O aluno NÃO deve ganhar cursos caso tirar uma nota inferior a 7")
	void alunoReprovou() {
		//declara aluno
		Aluno aluno = new Aluno("Jhonatan", 200250, TipoConta.BÁSICA);
		aluno.MatricularCurso(cursoExcel);
		
		//modifica a nota para ser reprovado
		CursoMatriculado cursoMatriculado = aluno.getCursosMatriculados().get(0);
		cursoMatriculado.setNota(3f); 
		cursoMatriculado.setStatus(StatusCurso.CONCLUÍDO);
		
		//verifica se foi reprovado 
		assertEquals(gerenciador.VerificarSeGanhouCursos(aluno, cursoExcel), false);
		assertEquals(cursoMatriculado.getStatus() == StatusCurso.REPROVADO, true);
	}
	
	@Test
	@DisplayName("Se o aluno NÃO concluiu o curso ele NÃO deve desbloquear novos cursos gratuítos")
	void alunoNaoConcluiuCurso() {
		//declara aluno
		Aluno aluno = new Aluno("Jhonatan", 200250, TipoConta.BÁSICA);
		aluno.MatricularCurso(cursoExcel);
		
		//adiciona um curso mas não finaliza ele
		CursoMatriculado cursoMatriculado = aluno.getCursosMatriculados().get(0);
		cursoMatriculado.setNota(10f); 
		
		//verifica se não presenteia caso o curso esteja em andamento
		assertEquals(gerenciador.VerificarSeGanhouCursos(aluno, cursoExcel), false);
		assertEquals(cursoMatriculado.getStatus() == StatusCurso.CURSANDO, true);
	}
}
