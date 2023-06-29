package com.ascending3.learnrestapi3.dto;

import com.ascending3.learnrestapi3.entity.Coach;
import com.ascending3.learnrestapi3.entity.Member;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoachDto {
    private Long id;
    private String name;
    private String description;
    private List<MemberDto> memberDtoList = new ArrayList<>();

    public Coach convertCoachDtoToCoach(){
        Coach coach = new Coach();
        if(getId() != null)
            coach.setId(getId());
        coach.setName(getName());
        coach.setDescription(this.getDescription());
        Set<Member> memberSet = getMemberSetByMemberDtoList(this.getMemberDtoList());
        coach.setMembers(memberSet);
        return coach;
    }

    private Set<Member> getMemberSetByMemberDtoList(List<MemberDto> memberDtoList) {
        Set<Member> memberSet = new HashSet<>();
        for(MemberDto memberDto : memberDtoList){
            Member member = memberDto.convertMemberDtoToMember();
            memberSet.add(member);
        }
        return memberSet;
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

    public List<MemberDto> getMemberDtoList() {
        return memberDtoList;
    }

    public void setMemberDtoList(List<MemberDto> memberDtoList) {
        this.memberDtoList = memberDtoList;
    }

    @Override
    public String toString() {
        return "CoachDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
