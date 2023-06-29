package com.ascending3.learnrestapi3.entity;

import com.ascending3.learnrestapi3.dto.CoachDto;
import com.ascending3.learnrestapi3.dto.MemberDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "coach")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    public CoachDto convertCoachToCoachDto(){
        CoachDto coachDto = new CoachDto();
        coachDto.setId(getId());
        coachDto.setName(getName());
        coachDto.setDescription(getDescription());
        List<MemberDto> memberDtoList = getCoachDtoListFromCoachSet(getMembers());
        coachDto.setMemberDtoList(memberDtoList);
        return coachDto;
    }

    private List<MemberDto> getCoachDtoListFromCoachSet(Set<Member> members) {
        List<MemberDto> memberDtoList = new ArrayList<>();
        for (Member member : members){
            MemberDto memberDto = member.convertMemberToMemberDto();
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }


    @OneToMany(mappedBy = "coach", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Member> members;

    public Set<Member> getMembers() {
        if(members == null)
            members = new HashSet<Member>();
        return members;
    }

    public void setMembers(Set<Member>members){
        this.members = members;
    }

    public void addMember(Member member){
        this.getMembers().add(member);
        member.setCoach(this);
    }

    public void removeMember(Member member){
        this.getMembers().remove(member);
        member.setCoach(null);
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

    @Override
    public String toString() {
        return "Coach{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
