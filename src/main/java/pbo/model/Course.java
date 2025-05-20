package pbo.model;

import javax.persistence.*;

@Entity
@Table(name="Course")

public class Course {
    @Id

    @Column(name="code", nullable = false, length = 30)
    private String code;
    @Column(name="matakuliah", nullable = false, length = 30)
    private String matakuliah;
    @Column(name="sks", nullable = false, length = 30)
    private String sks;
    @Column(name="semester", nullable = false, length = 30)
    private String semester;

public Course (String code, String matakuliah, String sks, String semester){
    this.code=code;
    this.matakuliah=matakuliah;
    this.sks=sks;
    this.semester=semester;

     
}

public String getCode(){
    return code;
}
public void setCode(String code){
    this.code=code;
}
public String getMatakuliah(){
    return matakuliah;
}
public void setMatakuliah(String matakuliah){
    this.matakuliah=matakuliah;
}

public String getSks (){
    return sks;
}

public void setSks (String sks){
    this.sks=sks;
}

public String getSemester () {
    return semester;
}

public void setSemester (String semester){
    this.semester=semester;
}

@Override
 public String toString() {
        return code + "|" + matakuliah + "|" + sks;
    
}
}