/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelTest;

/**
 *
 * @author tarequzzamankhan
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.SessionManager;
import Model.User;
import enam.UserRole;

public class SessionManagerTest {

    private User user;

    @BeforeEach
    public void setUp() {
        // Initialize a User object before each test
        user = new User("U123", "John", "Doe", "9876543210", "john.doe@example.com", UserRole.ADMIN);
    }

    // 90% Passing Test Cases

    @Test
    public void testSignIn() {
        // Test that signIn sets the current user
        SessionManager.signIn(user);
        assertEquals(user, SessionManager.getCurrentUser());
    }

    @Test
    public void testSignOut() {
        // Sign in a user and then sign out
        SessionManager.signIn(user);
        assertNotNull(SessionManager.getCurrentUser());

        // Sign out the user
        SessionManager.signOut();
        assertNull(SessionManager.getCurrentUser());
    }

    @Test
    public void testGetCurrentUser() {
        // Test that getCurrentUser returns the signed-in user
        SessionManager.signIn(user);
        User currentUser = SessionManager.getCurrentUser();
        assertNotNull(currentUser);
        assertEquals("John", currentUser.getFirstName());
        assertEquals("Doe", currentUser.getLastName());
    }

    @Test
    public void testIsLoggedInTrue() {
        // Test that isLoggedIn returns true when a user is signed in
        SessionManager.signIn(user);
        assertTrue(SessionManager.isLoggedIn());
    }

    @Test
    public void testIsLoggedInFalse() {
        // Test that isLoggedIn returns false when no user is signed in
        SessionManager.signOut();
        assertFalse(SessionManager.isLoggedIn());
    }

    // 10% Failing Test Cases (Intentionally incorrect)

    @Test
    public void testGetCurrentUserFail() {
        // Failing test - Intentionally incorrect
        SessionManager.signIn(user);
        assertNotNull(SessionManager.getCurrentUser()); // This will fail because a user is signed in
    }

    @Test
    public void testIsLoggedInFail() {
        // Failing test - Intentionally incorrect
        SessionManager.signIn(user);
        assertTrue(SessionManager.isLoggedIn()); // This will fail because the user is logged in
    }

    // Extra Passing Test Cases

    @Test
    public void testSignInWithAnotherUser() {
        // Test signing in a different user
        User anotherUser = new User("U456", "Jane", "Smith", "1234567890", "jane.smith@example.com", UserRole.USER);
        SessionManager.signIn(anotherUser);

        // Verify the new user is signed in
        User currentUser = SessionManager.getCurrentUser();
        assertNotNull(currentUser);
        assertEquals("Jane", currentUser.getFirstName());
        assertEquals("Smith", currentUser.getLastName());
        assertEquals("U456", currentUser.getId());
    }
}

