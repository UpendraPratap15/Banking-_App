package com.bank.entities;

import jakarta.persistence.*;

@Entity
public class Transaction
{
	@Id
	@GeneratedValue(generator = "tran_seq")
	@SequenceGenerator(name = "tran_seq",initialValue = 101,allocationSize = 1)
	private int trid;
	private int accountno;
	private int amount;
	private String date;
	private String time;
	private String type;
	public int getTrid() {
		return trid;
	}
	public void setTrid(int trid) {
		this.trid = trid;
	}
	public int getAccountno() {
		return accountno;
	}
	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
		
}
