package com.ac1.curso;

public class CursoMatriculado {
	private Curso curso;
	private StatusCurso status;
	private float nota;
	
	public CursoMatriculado(Curso curso) {
		this.curso = curso;
		this.status = StatusCurso.CURSANDO;
		this.nota = 0;
	}
	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public StatusCurso getStatus() {
		return status;
	}

	public void setStatus(StatusCurso status) {
		this.status = status;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}
}
