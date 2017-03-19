package Objects;

/**
 * Created by Viana on 9/25/2016.
 */
public class User
{
    private String _name;
    private String _details;

    public User()
    {
        this._name = "";
        this._details = "";
    }

    public String getName()
    {
        return this._name;
    }

    public String getDetails()
    {
        return this._details;
    }

    public void setName(String name)
    {
        this._name =  name;
    }

    public void setDetails(String details)
    {
        this._details = details;
    }

}
