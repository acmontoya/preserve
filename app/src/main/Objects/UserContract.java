package Objects;

import android.provider.BaseColumns;

/**
 * Created by Viana on 9/25/2016.
 */
public class UserContract
{
    private UserContract()
    {
        //To avoid instantiating anywhere
    }

    public static class UserDetails implements BaseColumns
    {
        public static final String TABLE_NAME = "USER_DETAILS_TABLE";
        public static final String NAME_COLUMN = "USER_COLUMN_NAME";
        public static final String DETAILS_COLUMN = "USER_COLUMN_DETAILS";
    }
}
