package head.secretspace.entity;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "ADD_BANK_CARD".
 */
@Entity
public class AddBankCard {

    @Id
    private Long id;
    private String bankAccount;
    private String cardholderName;
    private String telPhoneNum;
    private String issuingBank;
    private String remarks;
    private String registrationDate;
    private String time;
    private Integer like;

    @Generated
    public AddBankCard() {
    }

    public AddBankCard(Long id) {
        this.id = id;
    }

    @Generated
    public AddBankCard(Long id, String bankAccount, String cardholderName, String telPhoneNum, String issuingBank, String remarks, String registrationDate, String time, Integer like) {
        this.id = id;
        this.bankAccount = bankAccount;
        this.cardholderName = cardholderName;
        this.telPhoneNum = telPhoneNum;
        this.issuingBank = issuingBank;
        this.remarks = remarks;
        this.registrationDate = registrationDate;
        this.time = time;
        this.like = like;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getTelPhoneNum() {
        return telPhoneNum;
    }

    public void setTelPhoneNum(String telPhoneNum) {
        this.telPhoneNum = telPhoneNum;
    }

    public String getIssuingBank() {
        return issuingBank;
    }

    public void setIssuingBank(String issuingBank) {
        this.issuingBank = issuingBank;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

}
