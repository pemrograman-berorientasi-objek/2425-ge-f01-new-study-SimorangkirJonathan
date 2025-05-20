package pbo.model;

import javax.persistence.*;

@Entity
@Table(name = "Course")

public class Course {
    @Id
    @Column(name = "kode", nullable = false, length = 30)
    private String kode;
    @Column(name = "nama", nullable = false, length = 30)
    private String nama;
    @Column(name = "semester", nullable = false, length = 30)
    private String semester;
    @Column(name = "kredit", nullable = false, length = 30)
    private String kredit;

    public Course() {
    }

    public Course(String kode, String nama, String semester, String kredit) {
        this.kode = kode;
        this.nama = nama;
        this.semester = semester;
        this.kredit = kredit;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getKredit() {
        return kredit;
    }

    public void setKredit(String kredit) {
        this.kredit = kredit;
    }

    @Override
    public String toString() {
        return kode + "|" + nama + "|" + semester + "|" + kredit;
    }

}
