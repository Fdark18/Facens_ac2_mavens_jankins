package com.ac1.aluno;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ac1.curso.Curso;
import com.ac1.curso.CursoMatriculado;

public class Aluno {
	private String nome;
	private int identificador;
	private TipoConta tipoConta;

	private List<CursoMatriculado> cursosMatriculados = new ArrayList<CursoMatriculado>();
	
	public Aluno(String nome, int identificador, TipoConta tipoConta) {
		super();
		this.nome = nome;
		this.identificador = identificador;
		this.tipoConta = tipoConta;
	}
	
	public void MatricularCurso(Curso curso)
	{
		CursoMatriculado novoCurso = new CursoMatriculado(curso);
		cursosMatriculados.add(novoCurso);
	}
	
	public void RemoverCurso(Curso curso)
	{
	    Iterator<CursoMatriculado> iterator = cursosMatriculados.iterator();
	    while (iterator.hasNext()) {
	        CursoMatriculado cursoMatriculado = iterator.next();
	        if (cursoMatriculado.getCurso() == curso) {
	            iterator.remove();
	        }
	    }
	}
	
	public List<CursoMatriculado> getCursosMatriculados() {
		return cursosMatriculados;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
}
