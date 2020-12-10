package com.pack.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ServiceLayerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String breakLine;
	private List<String> messages;
	
	public ServiceLayerException() {
		super();
	}

	public ServiceLayerException(String message, Throwable cause) {
		super(message, cause);
		this.getMessages().add(message);
	}

	public ServiceLayerException(String message) {
		super(message);
		this.getMessages().add(message);
	}

	public ServiceLayerException(Throwable cause) {
		super(cause);
	}
	
	public ServiceLayerException(List<String> messages) {
		this.messages = messages;
	}
	
	protected String getBreakLine() {
		if(this.breakLine == null){
			this.breakLine = "\n";
		}
		return breakLine;
	}

	/**
	 * Define o caracter para quebra de linha das mensagens.
	 * O padrão é \n.
	 * @param breakLine
	 * 				- Caracter para quebra de linha das mensagens.
	 */
	protected void setBreakLine(String breakLine) {
		this.breakLine = breakLine;
	}

	/**
	 * Retorna a lista de mensagens que foram empilhadas na camada Service.
	 * @return
	 */
	public List<String> getMessages() {
		if(this.messages == null){
			this.messages = new ArrayList<String>();
		}
		return this.messages;
	}
	
	/**
	 * Adiciona mensagem a pilha de mensagem
	 * @param Mensagem
	 */
	public void addMessage(String message){
		this.getMessages().add(message);
	}
	
	/**
	 * Retorna as mensagens que foram empilhadas na camada Service (uma por linha).
	 */
	@Override
	public String getMessage() {
		StringBuilder str = new StringBuilder();
		if(!this.getMessages().isEmpty()){
			for (String msg : this.getMessages()) {
				if(str.toString().isEmpty()){
					str.append(msg);
				}else{
					str.append(this.getBreakLine()).append(msg);
				}
			}
		}else{
			str.append(super.getMessage());
		}
		return str.toString();
	}

	@Override
	public String toString() {
		return super.getMessage();
	}

}