package ua.testing.repairagency.region.currency;

import ua.testing.repairagency.util.Constants;

import java.util.Locale;

public class Conversion {

    public long UsdToUahConvert(long price, Locale currentLocale){
        return currentLocale
                .equals(Constants.UA_LOCALE)? price: price * Constants.CURRENCY_RATE_CONSTANT;
    }

    public long UahToUsdConvert(long price, Locale currentLocale){
        return currentLocale
                .equals(Constants.EN_LOCALE)? price: price / Constants.CURRENCY_RATE_CONSTANT;
    }
}
