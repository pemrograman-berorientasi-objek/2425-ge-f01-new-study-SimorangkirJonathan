package pbo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Student")

public class Student {
    @Id
    @Column(name="nim", nullable = false, length = 30)
    private String nim;
    @Column(name="nama", nullable = false, length = 30)
    private String nama;
    @Column(name="prodi", nullable = false, length = 30)
    private String prodi;

public Student(String nim, String nama, String prodi) {
    this.nim=nim;
    this.nama=nama;
    this.prodi=prodi;

}
    public String getNim () {
        return nim;
    }

    public void setNim (String nim){
        this.nim=nim;
    }

    public String getNama (){
        return nama;
    }

    public void setNama(String nama){
        this.nama=nama;
    }
    public String getProdi (){
        return prodi;
    }

    public void setProdi (String prodi){
        this.prodi=prodi;
    }
@Override
 public String toString() {
        return nim + "|" + nama + "|" + prodi;
    

}
    
}
