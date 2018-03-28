package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class DaoMark {
    public static void main(String[] args){
        Schema schema=new Schema(1,"head.secretspace.entity");
        addStudent(schema);
        addSecretSpace(schema);
        addPwItem(schema);
        addMainValue(schema);
        addBankCard(schema);
        addIdentityCard(schema);
        schema.setDefaultJavaPackageDao("head.secretspace.dao");
        try {
            new DaoGenerator().generateAll(schema,"E:\\android\\workspace\\SecretSpace\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private static void addStudent(Schema schema){
        Entity entity=schema.addEntity("Secretspace");
        entity.addIdProperty();
        entity.addStringProperty("name");
        entity.addStringProperty("address");
        entity.addIntProperty("age");
        entity.addStringProperty("time");
    }
    private static void addSecretSpace(Schema schema){
        Entity entity=schema.addEntity("SecretspaceUser");
        entity.addIdProperty();
        entity.addStringProperty("password");
        entity.addStringProperty("time");
    }
    private static void addPwItem(Schema schema){
        Entity entity=schema.addEntity("AddPwItem");
        entity.addIdProperty();
        entity.addStringProperty("name");
        entity.addStringProperty("password");
        entity.addStringProperty("remarks");
        entity.addStringProperty("time");
        entity.addIntProperty("like");

    }

    private static void  addMainValue(Schema schema){
        Entity entity=schema.addEntity("AddMainValue");
        entity.addIdProperty();
        entity.addStringProperty("sqlName");
        entity.addIntProperty("value");
        entity.addStringProperty("time");
        entity.addIntProperty("like");
    }

    private static void addBankCard(Schema schema){
        Entity entity=schema.addEntity("AddBankCard");
        entity.addIdProperty();
        entity.addStringProperty("bankAccount");
        entity.addStringProperty("cardholderName");
        entity.addStringProperty("telPhoneNum");
        entity.addStringProperty("issuingBank");
        entity.addStringProperty("remarks");
        entity.addStringProperty("registrationDate");
        entity.addStringProperty("time");
        entity.addIntProperty("like");

    }

    private static void addIdentityCard(Schema schema){
        Entity entity=schema.addEntity("AddIdentityCard");
        entity.addIdProperty();
        entity.addStringProperty("name");
        entity.addStringProperty("gender");
        entity.addStringProperty("familyName");
        entity.addStringProperty("idCard");
        entity.addStringProperty("homeAddress");
        entity.addStringProperty("mechanism");
        entity.addStringProperty("startTime");
        entity.addStringProperty("endTime");
        entity.addStringProperty("time");
        entity.addIntProperty("like");
    }
}
