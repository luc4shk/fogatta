package com.fogatta.utility;

import javax.servlet.http.HttpServletRequest;

public class Utility {

    // Obtiene la URL del sitio 
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
    
}
