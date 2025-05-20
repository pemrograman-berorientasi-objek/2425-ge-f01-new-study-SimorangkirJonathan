package pbo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Enrollment")

public class Enrollment {
    @Id
    @Column(name="code", nullable = false, length = 30)
    private final String code;
    @Column(name="nim", nullable = false, length = 30)
    private final String nim;
  
    public Enrollment(String code, String nim){
       this.code=code;
       this.nim=nim;
    
    }

    public String getCode(){
        return code;
    }

   public void setCode(String code) {
        this.code = code;
    }

    public String getNim(){
        return nim;
    }

    public void setNim(String nim){
        this.nim = nim;
    }

@Override
public String toString() {
        return code + "|" + nim;
    
}
}
