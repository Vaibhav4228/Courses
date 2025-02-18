package com.vaibhav.secure_app.service;

import com.vaibhav.secure_app.Entity.OurUsers;
import com.vaibhav.secure_app.dto.ReqRes;
import com.vaibhav.secure_app.repository.UsersRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersManagementServiceTest {

    @InjectMocks
    private UsersManagementService usersManagementService;

    @Mock
    private UsersRepo usersRepo;

    @Mock
    private com.vaibhav.secure_app.service.JWTUtils jwtUtils;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    private OurUsers user;
    private ReqRes request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new OurUsers();
        user.setId(1);
        user.setEmail("test@example.com");
        user.setName("Test User");
        user.setPassword("encoded_password");
        user.setCity("Test City");
        user.setRole("USER");

        request = new ReqRes();
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setCity("Test City");
        request.setRole("USER");
        request.setName("Test User");
    }

    @Test
    void testRegisterUserSuccess() {
        when(passwordEncoder.encode(anyString())).thenReturn("encoded_password");
        when(usersRepo.save(any(OurUsers.class))).thenReturn(user);

        ReqRes response = usersManagementService.register(request);

        assertEquals(200, response.getStatusCode());
        assertEquals("User Saved Successfully", response.getMessage());
        assertNotNull(response.getOurUsers());
        verify(usersRepo, times(1)).save(any(OurUsers.class));
    }

    @Test
    void testRegisterUserFailure() {
        when(usersRepo.save(any(OurUsers.class))).thenThrow(new RuntimeException("Database error"));

        ReqRes response = usersManagementService.register(request);

        assertEquals(500, response.getStatusCode());
        assertEquals("Database error", response.getError());
    }

    @Test
    void testLoginSuccess() {
        when(usersRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(jwtUtils.generateToken(any())).thenReturn("jwt_token");
        when(jwtUtils.generateRefreshToken(any(), any())).thenReturn("refresh_token");

        ReqRes response = usersManagementService.login(request);

        assertEquals(200, response.getStatusCode());
        assertEquals("Successfully Logged In", response.getMessage());
        assertEquals("jwt_token", response.getToken());
        verify(authenticationManager, times(1)).authenticate(any());
    }

    @Test
    void testLoginFailure() {
        when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException("Authentication failed"));

        ReqRes response = usersManagementService.login(request);

        assertEquals(500, response.getStatusCode());
        assertEquals("Authentication failed", response.getMessage());
    }

    @Test
    void testGetAllUsers() {
        List<OurUsers> usersList = List.of(user);
        when(usersRepo.findAll()).thenReturn(usersList);

        ReqRes response = usersManagementService.getAllUsers();

        assertEquals(200, response.getStatusCode());
        assertEquals("Successful", response.getMessage());
        assertEquals(1, response.getOurUsersList().size());
    }

    @Test
    void testGetUserByIdFound() {
        when(usersRepo.findById(1)).thenReturn(Optional.of(user));

        ReqRes response = usersManagementService.getUsersById(1);

        assertEquals(200, response.getStatusCode());
        assertEquals("Users with id '1' found successfully", response.getMessage());
        assertNotNull(response.getOurUsers());
    }

    @Test
    void testGetUserByIdNotFound() {
        when(usersRepo.findById(1)).thenReturn(Optional.empty());

        ReqRes response = usersManagementService.getUsersById(1);

        assertEquals(500, response.getStatusCode());
        assertTrue(response.getMessage().contains("User Not found"));
    }

    @Test
    void testUpdateUserSuccess() {
        OurUsers updatedUser = new OurUsers();
        updatedUser.setEmail("new@example.com");
        updatedUser.setName("New Name");
        updatedUser.setCity("New City");
        updatedUser.setRole("ADMIN");
        updatedUser.setPassword("new_password");

        when(usersRepo.findById(1)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(anyString())).thenReturn("new_encoded_password");
        when(usersRepo.save(any(OurUsers.class))).thenReturn(updatedUser);

        ReqRes response = usersManagementService.updateUser(1, updatedUser);

        assertEquals(200, response.getStatusCode());
        assertEquals("User updated successfully", response.getMessage());
        assertNotNull(response.getOurUsers());
        assertEquals("New Name", response.getOurUsers().getName());
    }

    @Test
    void testUpdateUserNotFound() {
        when(usersRepo.findById(1)).thenReturn(Optional.empty());

        ReqRes response = usersManagementService.updateUser(1, user);

        assertEquals(404, response.getStatusCode());
        assertEquals("User not found for update", response.getMessage());
    }

    @Test
    void testDeleteUserSuccess() {
        when(usersRepo.findById(1)).thenReturn(Optional.of(user));
        doNothing().when(usersRepo).deleteById(1);

        ReqRes response = usersManagementService.deleteUser(1);

        assertEquals(200, response.getStatusCode());
        assertEquals("User deleted successfully", response.getMessage());
    }

    @Test
    void testDeleteUserNotFound() {
        when(usersRepo.findById(1)).thenReturn(Optional.empty());

        ReqRes response = usersManagementService.deleteUser(1);

        assertEquals(404, response.getStatusCode());
        assertEquals("User not found for deletion", response.getMessage());
    }
}
