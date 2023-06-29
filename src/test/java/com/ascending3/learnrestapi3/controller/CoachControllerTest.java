package com.ascending3.learnrestapi3.controller;

import com.ascending3.learnrestapi3.dto.CoachDto;
import com.ascending3.learnrestapi3.exception.ItemNotFoundException;
import com.ascending3.learnrestapi3.service.CoachService;
import com.ascending3.learnrestapi3.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CoachControllerTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoachService mockCoachService;

    @MockBean
    private MemberService mockMemberService;

    @InjectMocks
    private CoachController coachController;

    @BeforeEach
    public void initEach(){

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(coachController).build();
    }

    @Test
    public void testFindAllCoaches() throws Exception {
        List<CoachDto> coachDtoList = new ArrayList<>();
        CoachDto coachDto = creatCoachDtoByName("aaa");
        Long coachId = 100L;
        coachDto.setId(coachId);
        coachDtoList.add(coachDto);

        when(mockCoachService.getCoaches()).thenReturn(coachDtoList);
        String responseJsonString = JsonStringUtil.convertObjectToJsonString(coachDtoList);

        String restUriForFindingAllCoaches = "/project/coaches";
        mockMvc.perform(get(restUriForFindingAllCoaches)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(responseJsonString))
                .andExpect(status().isOk());

        verify(mockCoachService, times(1)).getCoaches();

    }

    @Test
    public void testFindCoachByCoachId_happy_path() throws Exception {
        CoachDto coachDto = creatCoachDtoByName("aaa");
        Long coachId = 101L;
        coachDto.setId(coachId);

        when(mockCoachService.getCoachById(anyLong())).thenReturn(coachDto);
        String requestJsonString = JsonStringUtil.convertObjectToJsonString(coachId);
        String responseJsonString = JsonStringUtil.convertObjectToJsonString(coachDto);

        String restUriForGettingCoachById = "/project/coaches/{id}";

        /*
         * not using RequestBuilder
         */
//        mockMvc.perform(get(restUriForGettingCoachById, coachId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestJsonString))
//                .andDo(print())
//                .andExpect(content().json(responseJsonString))
//                .andExpect(status().isOk());

        /*
         * using RequestBuilder
         */
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(restUriForGettingCoachById, coachId)
                        .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJsonString);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(content().json(responseJsonString))
                .andExpect(status().isOk());

        verify(mockCoachService, times(1)).getCoachById(anyLong());
    }

    @Test
    public void testFindCoachByCoachId_Not_Found_usingResponseEntity() throws Exception {
        CoachDto coachDto = creatCoachDtoByName("aaa");
        Long coachId = 101L;
        coachDto.setId(coachId);

        when(mockCoachService.getCoachById(anyLong())).thenThrow(new ItemNotFoundException("Could not find Coach with id = " + coachId));
        String requestJsonString = JsonStringUtil.convertObjectToJsonString(coachId);


        String restUriForGettingCoachById = "/project/coaches/{id}";

        mockMvc.perform(get(restUriForGettingCoachById, coachId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonString))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message",is("ItemNotFoundException")))
                .andExpect(jsonPath("$.description",is("Could not find Coach with id = " + coachId)));

        verify(mockCoachService, times(1)).getCoachById(anyLong());
    }

    @Test
    public void testCreateCoachDto() throws Exception {
        String coachName = "aaa";
        CoachDto inputCoachDto = creatCoachDtoByName(coachName);

        CoachDto savedCoachDto = creatCoachDtoByName(coachName);
        Long coachId = 100L;
        savedCoachDto.setId(coachId);

        String requestJsonString = JsonStringUtil.convertObjectToJsonString(inputCoachDto);
        String responseJsonString = JsonStringUtil.convertObjectToJsonString(savedCoachDto);

        when(mockCoachService.save(inputCoachDto)).thenReturn(savedCoachDto);

        String restUriForCreatingCoachDto = "/project/coaches";

        mockMvc.perform(post(restUriForCreatingCoachDto)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonString))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(responseJsonString));

        verify(mockCoachService, times(1)).save(inputCoachDto);

    }

    @Test
    public void testUpdateCoachDto() throws Exception {
        String coachName = "aaa";
        CoachDto inputCoachDto = creatCoachDtoByName(coachName);
        Long coachId = 100L;
        inputCoachDto.setId(coachId);

        CoachDto updatedCoachDto = creatCoachDtoByName(coachName);
        updatedCoachDto.setId(coachId);
        updatedCoachDto.setDescription(updatedCoachDto.getDescription() + "_updated");

        String requestJsonString = JsonStringUtil.convertObjectToJsonString(inputCoachDto);
        String responseJsonString = JsonStringUtil.convertObjectToJsonString(updatedCoachDto);

        when(mockCoachService.update(inputCoachDto)).thenReturn(updatedCoachDto);

        String restUriForUpdatingCoachDto = "/project/coaches";

        mockMvc.perform(put(restUriForUpdatingCoachDto)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJsonString))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(responseJsonString))
                .andExpect(jsonPath("$.id", is(coachId.intValue())))
                .andExpect(jsonPath("$.name",comparesEqualTo(updatedCoachDto.getName())))
                .andExpect(jsonPath("$.description",comparesEqualTo(updatedCoachDto.getDescription())));


        verify(mockCoachService, times(1)).update(inputCoachDto);

    }


    @Test
    public void testDeleteCoachById_happy_path() throws Exception {
        when(mockCoachService.deleteById(anyLong())).thenReturn(true);

        Long coachId = 100L;
        String requestJsonString = JsonStringUtil.convertObjectToJsonString(coachId);

        String restUriForDeletingCoachById = "/project/coaches/{id}";

        mockMvc.perform(delete(restUriForDeletingCoachById, coachId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonString))
                .andDo(print())
                .andExpect(status().isOk());

        verify(mockCoachService, times(1)).deleteById(anyLong());

    }

    @Test
    public void testDeleteCoachById_failed() throws Exception {
        when(mockCoachService.deleteById(anyLong())).thenReturn(false);

        Long coachId = 100L;
        String requestJsonString = JsonStringUtil.convertObjectToJsonString(coachId);

        String restUriForDeletingCoachById = "/project/coaches/{id}";

        mockMvc.perform(delete(restUriForDeletingCoachById, coachId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJsonString))
                .andDo(print())
                .andExpect(status().is4xxClientError());

        verify(mockCoachService, times(1)).deleteById(anyLong());

    }

    private CoachDto creatCoachDtoByName(String name) {
        CoachDto coachDto = new CoachDto();
        coachDto.setName(name);
        coachDto.setDescription(name + "description");
        return coachDto;
    }

}
