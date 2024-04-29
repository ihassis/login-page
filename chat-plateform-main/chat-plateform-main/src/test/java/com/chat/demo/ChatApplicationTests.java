package com.chat.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.chat.demo.users.User;
import com.chat.demo.users.UserRepository;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ChatApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepo;

    @Test
	@WithMockUser
    public void testIndexPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testUsersPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("users"));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void testusersPageAsUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
            .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    

    @Test
	
	@WithMockUser
    public void testUserinfoPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/userinfo"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("userinfo"));
    }

    @Test
	@WithMockUser(roles = {"ADMIN"})
    public void testAdduserPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/adduser"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("adduser"));
    }
    @Test
    @WithMockUser(roles = {"USER"})
    public void testAdduserPageAsUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/adduser"))
            .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    

    // Test save method with invalid user
    @Test
	@WithMockUser

    public void testSaveMethodWithInvalidUser() throws Exception {
        User invalidUser = new User(); // create invalid user here
        mockMvc.perform(MockMvcRequestBuilders.post("/save")
               .flashAttr("user", invalidUser))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("users"));
    }

    // Test save method with valid user
    @Test
	@WithMockUser

    public void testSaveMethodWithValidUser() throws Exception {
        User validUser = new User(); // create valid user here
        mockMvc.perform(MockMvcRequestBuilders.post("/save")
               .flashAttr("user", validUser))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("users"));
    }


}
