package data;

import org.testng.annotations.DataProvider;

public class CommonDataProvider {

    public static final String NOT_FOUND_IDS = "notFoundIds";
    public static final String BAD_REQUEST_IDS = "badRequestIds";

    @DataProvider
    public Object[][] notFoundIds() {
        return new Object[][] {
                { -1 }, { 9999999 }
        };
    }

    @DataProvider
    public Object[][] badRequestIds() {
        return new Object[][] {
                { "test" }, { "%20" }, { Long.MAX_VALUE }
        };
    }
}
