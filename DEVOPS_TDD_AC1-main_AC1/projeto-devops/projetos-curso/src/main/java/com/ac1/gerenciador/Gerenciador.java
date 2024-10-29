package com.ac1.gerenciador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ac1.aluno.Aluno;
import com.ac1.curso.Curso;
import com.ac1.curso.CursoMatriculado;
import com.ac1.curso.StatusCurso;

@SpringBootApplication
public class Gerenciador {

	private List<Curso> cursos = new ArrayList<Curso>();
	
	public Gerenciador() {
		
	}
	
	public Boolean VerificarSeGanhouCursos(Aluno aluno, Curso curso) {
		List<CursoMatriculado> cursosMatriculados = aluno.getCursosMatriculados();
		
		for(CursoMatriculado cursoMatriculado : cursosMatriculados) {
			//encontra o curso e verifica se foi concluído
			if(cursoMatriculado.getCurso() == curso && cursoMatriculado.getStatus() == StatusCurso.CONCLUÍDO) {
				if(cursoMatriculado.getNota() >= 7) {
					PresentearCursos(aluno);
					cursoMatriculado.setStatus(StatusCurso.APROVADO);
					return true;
				} else {
					cursoMatriculado.setStatus(StatusCurso.REPROVADO);
					return false;
				}
			}
		}
		
		return false;
	}	
	
	private void PresentearCursos(Aluno aluno) {
	    Collections.shuffle(cursos); //embaralha a lista
	    Set<Curso> cursosEscolhidos = new HashSet<>();
	    
	    //escolhe 3 cursos
	    for (Curso curso : cursos) {
	        if (cursosEscolhidos.size() >= 3) {
	            break; 
	        }
	        cursosEscolhidos.add(curso); 
	    }
	    
	    //adiciona os cursos ao objeto aluno
	    for (Curso cursoEscolhido : cursosEscolhidos) {
	        aluno.MatricularCurso(cursoEscolhido);
	    }
	}
	
	public void AdicionarCurso(Curso curso) {
		cursos.add(curso);
	}
	
	public static void main(String[] args) {
		
	}
}
