package com.health.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.service.dto.user.UserDto;
import com.health.service.user.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureRestDocs
class UserControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected UserService userService;

    @Autowired
    public void findById() throws Exception {
        // given
        given(userService.findById(0L))
                .willReturn(UserDto.builder()
                        .id(0L)
                        .name("테스트")
                        .email("test@test.com")
                        .delete(false)
                        .createdDate(LocalDateTime.now())
                        .modifiedDate(LocalDateTime.now())
                        .build());
        
        // when
        ResultActions result = this.mockMvc.perform(
                get("/user/{id}", 0L)
                        .accept(MediaType.APPLICATION_JSON)
        );
        
        // then
        result.andExpect(status().isOk())
                .andDo(document("유저-검색", 
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()), 
                        pathParameters(
                                parameterWithName("id").description("아이디")
                        ),
                        responseFields(beneathPath("data").withSubsectionId("data"),
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("사용자이름"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("사용자이메일"),
                                fieldWithPath("delete").type(JsonFieldType.STRING).description("삭제여부"),
                                fieldWithPath("createdDate").type(JsonFieldType.STRING).description("생성일자"),
                                fieldWithPath("modifiedDate").type(JsonFieldType.STRING).description("수정일자")
                        )
                ));
    }
}