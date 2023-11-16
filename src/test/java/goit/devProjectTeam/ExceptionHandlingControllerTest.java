package goit.devProjectTeam;

import goit.devProjectTeam.link.exception.ExceptionHandlingController;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ExceptionHandlingControllerTest {

    private ExceptionHandlingController exceptionHandlingController;

    @BeforeEach
    public void setUp() {
        exceptionHandlingController = new ExceptionHandlingController();
    }

    @Test
    public void testConflict() {
        assertEquals("Link has been expired", exceptionHandlingController.conflict());
    }

    @Test
    public void testExpireError() {
        String viewName = exceptionHandlingController.expireError();
        assertEquals("expireError", viewName);
    }

    @Test
    public void testHandleError() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        Exception exception = new Exception();
        when(request.getRequestURL()).thenReturn(new StringBuffer(""));
        ModelAndView modelAndView = exceptionHandlingController.handleError(request, exception);
        assertEquals("error", modelAndView.getViewName());
        assertEquals(exception, modelAndView.getModel().get("exception"));
        assertEquals("", modelAndView.getModel().get("url").toString());
    }
}