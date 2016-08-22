package repo.entity;



import javax.persistence.*;

/**
 * Created by Vlad.M on 18.08.2016.
 */
@Entity
@Table(name = "points")
public class MyPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer x;
    private Integer y;
    public MyPoint(int x, int y){
        this.x = x;
        this.y = y;
    }
    public MyPoint(){}

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
