package anony.controller;


import anony.security.jwt.JwtUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTests {

    private final MockMvc mockMvc;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserControllerTests(MockMvc mockMvc, JwtUtils jwtUtils) {
        this.mockMvc = mockMvc;
        this.jwtUtils = jwtUtils;
    }

    @Test
    public void getWithoutToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void getWithToken() throws Exception {
        var token = jwtUtils.generateJwtToken("admin");
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", String.format("Bearer %s", token)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("admin")));
    }
}
