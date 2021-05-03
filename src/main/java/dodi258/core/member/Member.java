package dodi258.core.member;

public class Member {

    private Long id;
    private String name;
    private Grade grade;

    public Member() {
        this.id = 0L;
        this.name = "";
        this.grade = Grade.BASIC;
    }

    public Member(String name, Grade grade) {
        this.id = 0L;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
