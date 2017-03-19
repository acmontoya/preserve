package Objects;

import android.content.Context;
import android.database.Cursor;
import android.iics.module4_sqlite_demo.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Viana on 9/25/2016.
 */
public class UserCursorAdapter extends CursorAdapter
{
    public UserCursorAdapter(Context context, Cursor cursor)
    {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(R.layout.user_record_template, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView nameView = (TextView)view.findViewById(R.id.textView_NameTemp);
        TextView detailsView = (TextView)view.findViewById(R.id.textView_DetailsTemp);

        nameView.setText(cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserDetails.NAME_COLUMN)));
        detailsView.setText(cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserDetails.DETAILS_COLUMN)));
    }
}
