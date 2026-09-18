package api.endpoints;

import api.utilities.ConfigReader;

public class Routes {

    public static String base_url =
            ConfigReader.getProperty("base_url");

    public static String post_url =
            base_url + "/user";

    public static String get_url =
            base_url + "/user/{username}";

    public static String update_url =
            base_url + "/user/{username}";

    public static String delete_url =
            base_url + "/user/{username}";
}