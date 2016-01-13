package aplication.exceptions;

import javax.swing.JOptionPane;

public class InsufficientMedicoesException extends Exception {

	private static final long serialVersionUID = 8593821579031270961L;
	
	private int qtdeMedicoes;
	
	public InsufficientMedicoesException(int qtdeMedicoes) {
		super();
		this.qtdeMedicoes = qtdeMedicoes;
	}

	@Override
	public String getMessage(){
		return "Quantidade de medições " +this.qtdeMedicoes +" insuficientes"; 
	}

	public int getQtdeMedicoes() {
		return qtdeMedicoes;
	}

	public void setQtdeMedicoes(int qtdeMedicoes) {
		this.qtdeMedicoes = qtdeMedicoes;
	}

}
