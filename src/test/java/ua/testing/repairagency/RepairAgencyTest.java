package ua.testing.repairagency;

import org.junit.Test;
import ua.testing.repairagency.region.currency.CurrencyConversion;
import ua.testing.repairagency.region.transliteration.NameTransliteration;
import ua.testing.repairagency.util.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class RepairAgencyTest {

    public static final String LOGIN_PATH = Constants.LOGIN_PAGE_PATH;

    @Test
    public void whenCallDoGetThenServletReturnLoginPage() throws IOException, ServletException {
        final Servlet servlet = new Servlet();

        final HttpServletRequest request =  mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getAttribute(Constants.REDIRECT_ATTRIBUTE)).thenReturn(false);
        when(request.getRequestDispatcher(LOGIN_PATH)).thenReturn(dispatcher);
        servlet.doGet(request,response);

        verify(request,times(1)).getRequestDispatcher(LOGIN_PATH);
        verify(request,never()).getSession();
        verify(dispatcher).forward(request,response);
    }


    @Test
    public void whenCallConvertUsdToUah(){
        CurrencyConversion currencyConversion = new CurrencyConversion(Constants.EN_LOCALE);
        int price= 100;
        int expectedResult = 3000;
        assertEquals(expectedResult, currencyConversion.convert(Constants.UA_LOCALE, price));
    }

    @Test
    public void whenCallTransliterateNameFromCyrillicToLatin(){
        NameTransliteration nameTransliteration = new NameTransliteration();
        String name = "Дивак Вячеслав";
        String expectedString = "Dyvak Vyacheslav";
        assertEquals(expectedString,nameTransliteration.transliterate(Constants.EN_LOCALE, name));
    }


}
