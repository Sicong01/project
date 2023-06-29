package com.ascending3.learnrestapi3.entity;

import com.ascending3.learnrestapi3.dto.CourseDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "create_date")
    private LocalDate createDate;

    public CourseDto convertCourseToCourseDto() {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(getId());
        courseDto.setName(getName());
        courseDto.setDescription(getDescription());
        courseDto.setCreateDate(getCreateDate());
        return courseDto;
    }

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<Member> members;

    public Set<Member> getMembers(){
        if(members == null)
            members = new HashSet<Member>();
        return members;
    }

    public void setMembers(Set<Member>members){
        this.members = members;
    }

    public void addMember(Member member){
        member.getCourses().add(this);
        this.getMembers().add(member);
    }

    public boolean removeMember(Member member){
        boolean sucessfulFlag = member.getCourses().remove(this);
        sucessfulFlag = this.getMembers().remove(member);
        return sucessfulFlag;
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
