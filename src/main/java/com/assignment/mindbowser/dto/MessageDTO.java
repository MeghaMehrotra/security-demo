package com.assignment.mindbowser.dto;
import java.io.Serializable;

public class MessageDTO<T extends Object> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 
     */
    public String responseMessage;
    public Boolean Status = true;
    public T body;

    /**
     * 
     */
    public MessageDTO() {
        super();
    }

    public MessageDTO(String responseMessage, boolean Status) {
        super();
        this.responseMessage = responseMessage;
        this.Status = Status;
    }

    public MessageDTO(String responseMessage, T body, boolean Status) {
        super();
        this.responseMessage = responseMessage;
        this.body = body;
        this.Status = Status;
    }

    /**
     * @param responseMessage
     * @param body
     */
    public MessageDTO(String responseMessage, T body) {
        super();
        this.responseMessage = responseMessage;
        this.body = body;
    }

    /**
     * @param body
     */
    public MessageDTO(String responseMessage) {
        super();
        this.responseMessage = responseMessage;
    }

    /**
     * @return the responseMessage
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * @param responseMessage
     *            the responseMessage to set
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    /**
     * @return the body
     */
    public T getBody() {
        return body;
    }

    /**
     * @param body
     *            the body to set
     */
    public void setBody(T body) {
        this.body = body;
    }

}