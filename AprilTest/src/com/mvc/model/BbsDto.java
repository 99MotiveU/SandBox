package com.mvc.model;

import java.sql.Date;

public class BbsDto {

    private int num;      // �Խñ� ��ȣ
    private String title; // �Խñ� ����
    private String id;    // �ۼ��� ���̵�
    private String content; // �Խñ� ����
    private Date nalja;   // �Խñ� �ۼ���

    // Getter�� Setter
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getNalja() {
        return nalja;
    }

    public void setNalja(Date nalja) {
        this.nalja = nalja;
    }
}
