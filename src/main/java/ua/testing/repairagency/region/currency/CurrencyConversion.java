package ua.testing.repairagency.region.currency;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static ua.testing.repairagency.util.Constants.EN_LOCALE;
import static ua.testing.repairagency.util.Constants.UA_LOCALE;

public class CurrencyConversion {

    private Locale locale;
    private Conversion conversion = new Conversion();
    private Map<Locale, CurrencyConverter> conversionMap = new HashMap<>();

    public CurrencyConversion(Locale locale) {
        this.locale = locale;
    }


    {
        conversionMap.put(EN_LOCALE, price -> conversion.UahToUsdConvert(price, locale));
        conversionMap.put(UA_LOCALE, price -> conversion.UsdToUahConvert(price, locale));
    }

    public long convert(Locale locale, long price) {
        return conversionMap.get(locale).convert(price);
    }
}
