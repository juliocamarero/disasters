package gsi.xpert;

/**
 *
 * @author Luis Delgado
 */
public enum ResourceType {

    AMBULANCE, POLICE, FIREMEN;

    public static ResourceType getType(String str) {
        if (str.toLowerCase().equals("ambulance") ||
                str.toLowerCase().equals("ambulancia")) {
            return ResourceType.AMBULANCE;
        } else if (str.toLowerCase().equals("police_car") ||
                str.toLowerCase().equals("police car") ||
                str.toLowerCase().equals("police")) {
            return ResourceType.POLICE;
        } else if (str.toLowerCase().equals("fire_engine") ||
                str.toLowerCase().equals("fire_engine") ||
                str.toLowerCase().equals("firemen")) {
            return ResourceType.FIREMEN;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
