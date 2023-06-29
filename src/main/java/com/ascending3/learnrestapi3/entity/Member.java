package com.ascending3.learnrestapi3.entity;

import com.ascending3.learnrestapi3.dto.MemberDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login_name")
    private String loginName;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "enrolled_date")
    private LocalDate enrolledDate;

    public MemberDto convertMemberToMemberDto() {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(getId());
        memberDto.setEmail(getEmail());
        memberDto.setFirstName(getFirstName());
        memberDto.setLastName(getLastName());
        memberDto.setLoginName(getLoginName());
        memberDto.setEnrolledDate(getEnrolledDate());
        memberDto.setPassword(getPassword());
        return memberDto;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id")
    private Coach coach;

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "member_course",
            joinColumns = {@JoinColumn(name = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private Set<Course> courses;

    public Set<Course> getCourses(){
        if(courses == null)
            courses = new HashSet<Course>();
        return courses;
    }

    public void setCourses(Set<Course>courses){
        this.courses = courses;
    }

    public void addCourse(Course course){
        this.getCourses().add(course);
        course.getMembers().add(this);
    }

    public void removeCourse(Course course){
        this.getCourses().remove(course);
        course.getMembers().remove(this);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(LocalDate enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", enrolledDate=" + enrolledDate +
                '}';
    }
}
