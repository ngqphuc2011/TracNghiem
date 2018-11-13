package com.example.nguyenphuc.tracnghiem.CauHoi;

import java.io.Serializable;

public class CauHoi implements Serializable {
    private int _id, subject;
    private String question, ans_a, ans_b, ans_c, ans_d, result;
    private String traloi = "";
    public int choiceID = -1; //Hỗ trợ check ID của RadioGroup

    public void setTraloi(String traloi) {
        this.traloi = traloi;
    }

    public String getTraloi() {
        return traloi;
    }

    public CauHoi(int _id, String question, String ans_a, String ans_b, String ans_c, String ans_d, String result, int subject, String traloi) {
        this._id = _id;
        this.question = question;
        this.ans_a = ans_a;
        this.ans_b = ans_b;
        this.ans_c = ans_c;
        this.ans_d = ans_d;
        this.result = result;
        this.subject = subject;
        this.traloi = traloi;
    }

    public CauHoi() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns_a() {
        return ans_a;
    }

    public void setAns_a(String ans_a) {
        this.ans_a = ans_a;
    }

    public String getAns_b() {
        return ans_b;
    }

    public void setAns_b(String ans_b) {
        this.ans_b = ans_b;
    }

    public String getAns_c() {
        return ans_c;
    }

    public void setAns_c(String ans_c) {
        this.ans_c = ans_c;
    }

    public String getAns_d() {
        return ans_d;
    }

    public void setAns_d(String ans_d) {
        this.ans_d = ans_d;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }
}
