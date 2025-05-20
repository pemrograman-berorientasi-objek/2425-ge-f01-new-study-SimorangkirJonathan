package pbo.model;

import javax.persistence.*;

@Entity
@Table(name = "Enroll")

public class Enroll {
    @Id
    @Column(name = "nim", nullable = false, length = 30)
    private String nim;
    @Column(name = "kode", nullable = false, length = 30)
    private String kode;

    public Enroll() {

    }

    public Enroll(String nim, String kode) {
        this.nim = nim;
        this.kode = kode;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    @Override
    public String toString() {
        return nim + "|" + kode;
    }

}
