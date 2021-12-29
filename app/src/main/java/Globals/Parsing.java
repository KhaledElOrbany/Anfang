package Globals;

public class Parsing {
    public static boolean toBool(Object value) {
        if (value != null) {
            return Boolean.parseBoolean((String) value);
        }
        return false;
    }

    /*
    *   public static bool ObjectoToBool(this object data)
        {

            try
            {
                if (data != null)
                {
                    return data.ToString() == "True" || data.ToString() == "true";
                }
                else
                {
                    return false;
                }

            }
            catch (Exception)
            {
                return false;
            }
        }
    * */
}
