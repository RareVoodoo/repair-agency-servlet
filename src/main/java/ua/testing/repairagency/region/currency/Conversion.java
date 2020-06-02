package ua.testing.repairagency.region.currency;

import ua.testing.repairagency.region.transliteration.NameTransliteration;

import java.util.Locale;

public class Conversion {
    private final static long CURRENCY_CONSTANT=  30;

    public long UsdToUahConvert(long price, Locale currentLocale){
        return currentLocale
                .equals(NameTransliteration.UA_LOCALE)? price: price * CURRENCY_CONSTANT;
    }

    public long UahToUsdConvert(long price, Locale currentLocale){
        return currentLocale
                .equals(NameTransliteration.EN_LOCALE)? price: price / CURRENCY_CONSTANT;
    }
}
