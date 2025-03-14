/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.servlet.http.HttpSession;

/**
 *
 * @author hanhhee
 */
public class AuthUtils {
    private static final String FOUNDER_ROLE = "Founder";
    private static final String MEMBER_ROLE = "Team Member";
    public static boolean isFounder(HttpSession session){
        return false;
    }
}
