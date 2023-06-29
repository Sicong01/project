package com.ascending3.learnrestapi3.dto;

import com.ascending3.learnrestapi3.entity.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate createDate;
    private List<MemberDto> memberDtoList = new ArrayList<>();

    public Course convertCourseDtoToCourse(){
        Course course = new Course();
        if(getId() != null)
            course.setId(getId());
        course.setName(getName());
        course.setDescription(getDescription());
        course.setCreateDate(getCreateDate());
        return course;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public List<MemberDto> getMemberDtoList() {
        return memberDtoList;
    }

    public void setMemberDtoList(List<MemberDto> memberDtoList) {
        this.memberDtoList = memberDtoList;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
