/**
 * @copyright 2014 IEEE
 * @package org.ieeecs.communities.bean
 * @created June 18, 2014
 * @description Object that will hold any portlet custom preferences
 */
package org.ieeecs.communities.bean;

public class CustomPreferences {
    private String name;
    private String value;
    private String defaultValue;

    public CustomPreferences( String name, String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getDefaultValue() {
        return defaultValue;
    }
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}