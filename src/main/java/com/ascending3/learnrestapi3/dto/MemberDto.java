package com.ascending3.learnrestapi3.dto;

import com.ascending3.learnrestapi3.entity.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberDto {
    private Long id;
    private String loginName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate enrolledDate;
    private List<CourseDto> courseDtoList = new ArrayList<>();

    public Member convertMemberDtoToMember(){
        Member member = new Member();
        if(getId() != null)
            member.setId(getId());
        member.setEmail(getEmail());
        member.setFirstName(getFirstName());
        member.setLastName(getLastName());
        member.setLoginName(getLoginName());
        member.setEnrolledDate(getEnrolledDate());
        member.setPassword(getPassword());
        return member;
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

    public List<CourseDto> getCourseDtoList() {
        return courseDtoList;
    }

    public void setCourseDtoList(List<CourseDto> courseDtoList) {
        this.courseDtoList = courseDtoList;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
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
