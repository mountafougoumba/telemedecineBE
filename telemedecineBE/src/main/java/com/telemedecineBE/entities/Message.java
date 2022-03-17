package com.telemedecineBE.entities;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	@Column(name="CONTENT")
	private String content;
	@Column(name="DATE")
	private LocalDate date;
	@Column(name="TIME")
	private String time;
	@Column(name="SENDER_ID")
	private Integer sender_id;
	@Column(name="RECEIVER_ID")
	private Integer receiver_id;

	public Message(String content, LocalDate date, LocalTime time){
		super();
		this.content = content;
		this.date = date;
		this.time = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

	public Message(String content){
		super();
		this.content = content;
		this.date = LocalDate.now();
		this.time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}