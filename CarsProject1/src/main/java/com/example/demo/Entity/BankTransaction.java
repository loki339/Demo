package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "banktransactions")
public class BankTransaction {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionid", nullable = false)
    private Long transactionId;

    @Column(name = "transactiondate")
    private Date transactionDate;

    @Column(name = "fromcardno", length = 20)
    private String fromCardNo;

    @Column(name = "tocardno", length = 20)
    private String toCardNo;

    @Column(name = "amount")
    private Float amount;

    public BankTransaction() {
        super();
    }

	public BankTransaction(Long transactionId, Date transactionDate, String fromCardNo, String toCardNo, Float amount) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.fromCardNo = fromCardNo;
		this.toCardNo = toCardNo;
		this.amount = amount;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getFromCardNo() {
		return fromCardNo;
	}

	public void setFromCardNo(String fromCardNo) {
		this.fromCardNo = fromCardNo;
	}

	public String getToCardNo() {
		return toCardNo;
	}

	public void setToCardNo(String toCardNo) {
		this.toCardNo = toCardNo;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BankTransaction [transactionId=" + transactionId + ", transactionDate=" + transactionDate
				+ ", fromCardNo=" + fromCardNo + ", toCardNo=" + toCardNo + ", amount=" + amount + "]";
	}

  
  
}
