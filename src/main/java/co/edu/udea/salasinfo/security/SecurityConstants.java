package co.edu.udea.salasinfo.security;

public class SecurityConstants {

        public static final String[] ADMIN_ENDPOINTS = {
                "/reservation/find-accept/**",
                "/application/**",
                "/implement/**",
                "/**"

                // Añade más endpoints según sea necesario
        };

        public static final String[] TEACHER_ENDPOINTS = {
                "/salasinfo/"
        };

        public static final String[] MONITOR_ENDPOINTS = {
                "/salasinfo/"
        };

        public static final String[] PUBLIC_ENDPOINTS = {
                "/application/rooms/**",
                "/auth/**",

                "/implement/rooms/**",
                "/reservation/find-all/**",
                "/reservation/free-all/**",
                "/reservation/save/**",
                "/reservation/find-by-room/**",
                "/room/find-all/**",
                "/room/find-by-id/**",
                "/room/find-by-application/**",
                "/room/find-by-implement/**"
        };

}

