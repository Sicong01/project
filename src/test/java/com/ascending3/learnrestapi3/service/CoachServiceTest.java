package com.ascending3.learnrestapi3.service;

import com.ascending3.learnrestapi3.dao.CoachDao;
import com.ascending3.learnrestapi3.dao.CourseDao;
import com.ascending3.learnrestapi3.dao.MemberDao;
import com.ascending3.learnrestapi3.dto.CoachDto;
import com.ascending3.learnrestapi3.entity.Coach;
import com.ascending3.learnrestapi3.service.Impl.CoachServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CoachServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Mock
    private CoachDao mockCoachDao;

    @Mock
    private CourseDao mockCourseDao;

    @Mock
    private MemberDao mockMemberDao;

    @InjectMocks
    private CoachServiceImpl coachService;

    @BeforeEach
    public void initEach(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavedCoachDto(){
        CoachDto mockCoachDto = mock(CoachDto.class);
        Coach mockCoach = mock(Coach.class);
        when(mockCoachDto.convertCoachDtoToCoach()).thenReturn(mockCoach);
        when(mockCoach.convertCoachToCoachDto()).thenReturn(mockCoachDto);
        when(mockCoachDao.save(mockCoach)).thenReturn(mockCoach);

        CoachDto coachDto = coachService.save(mockCoachDto);
        verify(mockCoachDao,times(1)).save(mockCoach);
        verify(mockCoach, times(1)).convertCoachToCoachDto();
        verify(mockCoach,times(1)).convertCoachToCoachDto();
    }

    @Test
    public void testDeleteCoachByName(){
        when(mockCoachDao.deleteByName(anyString())).thenReturn(false);
        boolean deleteResult = coachService.deleteByName(anyString());
        assertFalse(deleteResult);
        verify(mockCoachDao, times(1)).deleteByName(anyString());
    }

    @Test
    public void testGetAllCoachesUsingSpyList(){
        List<Coach> spyCoachList = spy(ArrayList.class);
        Coach mockCoach = mock(Coach.class);

        spyCoachList.add(mockCoach);
        spyCoachList.add(mockCoach);
        spyCoachList.add(mockCoach);

        CoachDto mockCoachDto = mock(CoachDto.class);
        when(mockCoach.convertCoachToCoachDto()).thenReturn(mockCoachDto);
        when(mockCoachDao.getCoaches()).thenReturn(spyCoachList);

        List<CoachDto> coachDtoList = coachService.getCoaches();

        assertEquals(3,coachDtoList.size());

        verify(mockCoachDao, times(1)).getCoaches();
        verify(mockCoach, times(3)).convertCoachToCoachDto();
        verify(mockCoachDao, times(1)).getCoaches();

    }

    @Test
    public void testGetAllCoachesUsingMockList(){
        Coach mockCoach = mock(Coach.class);
        CoachDto mockCoachDto = mock(CoachDto.class);
        List<Coach> mockCoachList = mock(ArrayList.class);

        Iterator mockIterator = mock(Iterator.class);

        when(mockCoachDao.getCoaches()).thenReturn(mockCoachList);

        when(mockCoachList.iterator()).thenReturn(mockIterator);
        when(mockIterator.next()).thenReturn(mockCoach);
        when(mockIterator.hasNext()).thenReturn(true,true,false);

        when(mockCoach.convertCoachToCoachDto()).thenReturn(mockCoachDto);

        List<CoachDto> coachDtoList = coachService.getCoaches();

        verify(mockCoachDao, times(1)).getCoaches();
        verify(mockCoach,times(2)).convertCoachToCoachDto();
    }
}
