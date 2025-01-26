package observer;
/*
Test-Driven Development (TDD):

TDD is a software development process where tests are written before the actual code implementation.
Steps:
Write a failing test for a feature.
Implement the feature to make the test pass.
Refactor the code while ensuring tests still pass.
Benefits: Ensures better code quality, reduces bugs, and leads to more maintainable systems.


Unit Testing:

Unit testing involves testing individual units (like classes or methods) of the software
in isolation to ensure they function correctly.
It focuses on testing specific functionalities and edge cases of a single module without external dependencies.
*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainSpreaderTest {
    private MainSpreader mainSpreader;

    @BeforeEach
    void setUp() {
        mainSpreader = new MainSpreader();
    }

    //registerTrustedSourceTests

    @Test
    void testRegisterTrustedSourceValid() {
        boolean result = mainSpreader.registerTrustedSource("Source1", "password123");
        assertTrue(result, "The source should be successfully registered.");
    }

    @Test
    void testRegisterTrustedSourceInvalidNullSource() {
        boolean result = mainSpreader.registerTrustedSource(null, "password123");
        assertFalse(result, "The source registration should fail for null source.");
    }

    @Test
    void testRegisterTrustedSourceInvalidNullPassword() {
        boolean result = mainSpreader.registerTrustedSource("Source1", null);
        assertFalse(result, "The source registration should fail for null password.");
    }

    @Test
    void testRegisterTrustedSourceInvalidEmptyPassword() {
        boolean result = mainSpreader.registerTrustedSource("Source1", "");
        assertFalse(result, "The source registration should fail for empty password.");
    }

    @Test
    void testRegisterTrustedSourceDuplicate() {
        mainSpreader.registerTrustedSource("Source1", "password123");
        boolean result = mainSpreader.registerTrustedSource("Source1", "newpassword");
        assertFalse(result, "The source registration should fail for duplicate source.");
    }



    //SpreadNewsTests

    @Test
    void testSpreadNewsValid() throws AuthenticationException, UntrustedSourceException, BlockedContentException {
        mainSpreader.registerTrustedSource("Source1", "password123");
        String result = mainSpreader.spreadNews("Breaking news!", "Source1", "password123");
        assertEquals("Breaking news!", result, "The news should be successfully spread.");
    }

    @Test
    void testSpreadNewsUntrustedSource() {
        mainSpreader.registerTrustedSource("Source1", "password123");

        try {
            // This will throw UntrustedSourceException because "Source2" is not registered
            mainSpreader.spreadNews("Fake news", "Source2", "password123");
            fail("Expected UntrustedSourceException to be thrown");
        } catch (UntrustedSourceException | AuthenticationException | BlockedContentException e) {
            // If exception is thrown, the test will pass.
            // Optionally, you can add assertions to check the exception message or other details:
            assertEquals("News spreader: untrusted source: Source that you provided is not registered!", e.getMessage());
        }
    }

    @Test
    void testSpreadNewsIncorrectPassword() {
        mainSpreader.registerTrustedSource("Source1", "password123");

        try {
            // This will throw AuthenticationException because the password is incorrect
            mainSpreader.spreadNews("Fake news", "Source1", "wrongpassword");
            fail("Expected AuthenticationException to be thrown");
        } catch (AuthenticationException e) {
            // Assert that the exception message matches the expected message
            assertEquals("News spreader: Authentication failure: Wrong password!", e.getMessage());
        } catch (Exception e) {
            // If any other exception is thrown, the test will fail
            fail("Unexpected exception type thrown: " + e.getClass().getName());
        }
    }

    @Test
    void testSpreadNewsBlockedContent() {
        mainSpreader.registerTrustedSource("Source1", "password123");
        mainSpreader.blockWord("blocked", false); // Block the word without redaction

        try {
            // This will throw BlockedContentException because the word "blocked" is not redacted
            mainSpreader.spreadNews("This news contains blocked content", "Source1", "password123");
            fail("Expected BlockedContentException to be thrown");
        } catch (BlockedContentException e) {
            // Assert that the exception message matches the expected message
            assertEquals("News spreader: Blocked content: Your news has blocked content", e.getMessage());
        } catch (Exception e) {
            // If any other exception is thrown, the test will fail
            fail("Unexpected exception type thrown: " + e.getClass().getName());
        }
    }

    @Test
    void testSpreadNewsNullArgument() {
        mainSpreader.registerTrustedSource("Source1", "password123");

        try {
            // This will throw IllegalArgumentException because the news argument is null
            mainSpreader.spreadNews(null, "Source1", "password123");
            fail("Expected IllegalArgumentException to be thrown");
        } catch (java.lang.IllegalArgumentException e) {
            // Assert that the exception message matches the expected message
            assertEquals("One of arguments is null!", e.getMessage());
        } catch (Exception e) {
            // If any other exception is thrown, the test will fail
            fail("Unexpected exception type thrown: " + e.getClass().getName());
        }
    }


    //BlockWordTests

    @Test
    void testBlockWordValid() {
        boolean result = mainSpreader.blockWord("blocked", true);
        assertTrue(result, "The word should be blocked successfully.");
    }

    @Test
    void testBlockWordInvalid() {
        boolean result = mainSpreader.blockWord("blocked123", true);
        assertFalse(result, "Non-alphabetic words should not be blocked.");
    }

    //UnblockWordTests

    @Test
    void testUnblockWordValid() {
        mainSpreader.blockWord("blocked", true);
        boolean result = mainSpreader.unblockWord("blocked");
        assertTrue(result, "The word should be unblocked successfully.");
    }

    @Test
    void testUnblockWordInvalid() {
        boolean result = mainSpreader.unblockWord(null);
        assertFalse(result, "Null words should not be unblocked.");
    }

}