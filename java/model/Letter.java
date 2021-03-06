package model;

import constance.AppConfig;
import javafx.scene.control.CheckBox;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Letter {
    private String id;
    private String category;
    private String problem;
    private String idApplicant;
    private String content;
    private String organization;
    private Date applyDate;
    private int statusLetter;
    private String statusString;
    private boolean deleted;

    // khong co cai get set nay thi thang fx no cx khong lay duoc thong tin gi ##
    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    // phai co cai nay moi dc check box
    private CheckBox checkBox;

    public CheckBox getCheckBox(){
        return  checkBox;
    }
    public void setCheckBox(CheckBox checkbox){
        this.checkBox=checkbox;
    }

    public Letter(){
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Letter(String id, String category, String problem, String idApplicant, String content, String organization, Date applyDate, int statusLetter, boolean deleted) {
        this.id = id;
        this.category = category;
        this.problem = problem;
        this.idApplicant = idApplicant;
        this.content = content;
        this.organization=organization;
        this.applyDate = applyDate;
        this.statusLetter = statusLetter;
        this.deleted = deleted;
        statusString= AppConfig.STATUS[statusLetter];
        checkBox=new CheckBox();
    }

    @Override
    public String toString() {
        return "LetterModel{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", problem='" + problem + '\'' +
                ", idApplicant=" + idApplicant +
                ", applyDate=" + applyDate +
                ", category='" + category + '\'' +
                ", statusLetter=" + statusLetter +
                ", deleted=" + deleted +
                '}';
    }


    public boolean equals(Letter other) {

        if (other == this)
            return true;
        if (this.id.equals(other.id)
                && this.category.equals(other.category)
                && this.problem.equals(other.problem)
                && this.content.equals(other.content)
                && this.idApplicant.equals(other.idApplicant)
                && this.organization.equals(other.organization)
                && this.applyDate.equals(other.applyDate)
                && this.category.equals(other.category)
                && this.statusLetter==other.statusLetter
                && this.deleted==other.deleted


            ){
            return true;
        }
        return false;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public String getIdApplicant() {
        return idApplicant;
    }

    public void setIdApplicant(String idApplicant) {
        this.idApplicant = idApplicant;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStatusLetter() {
        return statusLetter;
    }

    public void setStatusLetter(int statusLetter) {
        this.statusLetter = statusLetter;
    }


}