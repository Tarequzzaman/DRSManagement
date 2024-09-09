/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constant;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tarequzzamankhan
 */
public class Responder {

    public static final String[] DISASTER_STATUS = {"pending", "ongoing", "complete"};

    private static final Map<String, String> ROLE_TO_DEPARTMENT_MAP = new HashMap<>();

    static {
        ROLE_TO_DEPARTMENT_MAP.put("RESPONDER_FIRE_DEPARTMENT", "Fire Department");
        ROLE_TO_DEPARTMENT_MAP.put("RESPONDER_FLOOD_DEPARTMENT", "Flood Department");
        ROLE_TO_DEPARTMENT_MAP.put("RESPONDER_HEALTH_DEPARTMENT", "Health Department");
        ROLE_TO_DEPARTMENT_MAP.put("RESPONDER_DESERT_DEPARTMENT", "Desert Department");
    }

    public static String getDepartmentByRole(String userRole) {
        return ROLE_TO_DEPARTMENT_MAP.getOrDefault(userRole, "Unknown Department");
    }

}
