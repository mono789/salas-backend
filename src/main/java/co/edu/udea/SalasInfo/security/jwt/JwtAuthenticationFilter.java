package co.edu.udea.SalasInfo.security.jwt;

import co.edu.udea.SalasInfo.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

//para filtrar y garantizar queel filtro solo se ejecuete 1 vez por peticion http
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtener el token del request
        final String token= getTokenFromRequest(request);
        final String username;

    //si es nulo devuelvo a la cadena de filtros el control y retorno
    if(token==null){
        filterChain.doFilter(request, response);
        return;
    }
    //si no es nulo

    username= jwtUtils.getUsernameFromToken(token);

    //username no es nulo pero no lo podemos acceder -->buscamos DB
   if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null){
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        //evaluo si el token es valido
        if(jwtUtils.isTokenValid(token,userDetails)){
            //actualizo el SecurityCH
            UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());

            // Print the authorities (roles) associated with the user
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            System.out.println("Roles: " + authorities);
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
    filterChain.doFilter(request,response);

    }

    private String getTokenFromRequest(HttpServletRequest request) {
        //capturo el item de autenticacion
        final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
        //si el token tiene estas caracteristicas despues del Bearer esta el token
        if(StringUtils.hasText(authHeader)&& authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }

        return null;
    }
}
