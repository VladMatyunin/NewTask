package repo.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Vlad.M on 18.08.2016.
 */
@Entity
@Table(name = "images")
public class MyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer foreignId;
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinColumn(name = "items_id")
    private MyPoint[] myPoints;
    private Date date;
    private String name;
    private Integer height;
    private Integer width;
    private Integer patient_id;
    private Integer study_id;
    public MyImage(MyPoint[] points, Date date, String name, int height, int width,int foreignId){
        this.myPoints = points;
        this.date = date;
        this.name = name;
        this.height = height;
        this.width = width;
        this.foreignId = foreignId;
    }
    public MyImage(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForeignId() {
        return foreignId;
    }

    public void setForeignId(Integer foreignId) {
        this.foreignId = foreignId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }


    public MyPoint[] getMyPoints() {
        return myPoints;
    }

    public void setMyPoints(MyPoint[] myPoints) {
        this.myPoints = myPoints;
    }

    public Integer getStudy_id() {
        return study_id;
    }

    public void setStudy_id(Integer study_id) {
        this.study_id = study_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }
}
