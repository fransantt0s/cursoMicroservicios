package com.fransantt0s.springboot.app.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);

    @Override
    //Indicamos el tipo de filtro, puede ser "pre","post","route"
    //En este caso estamos implementando un pre.
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    //Valida si se va a ejecutar o no el filtro
    //Vamos a ponerlo en true para que lo ejecute siempre, para probar
    public boolean shouldFilter() {
        return true;
    }

    @Override
    //Resuelve la logica de nuestro filtro.
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request enrutado a %s",request.getMethod(),request.getRequestURL().toString()));
        Long tiempoInicio = System.currentTimeMillis();
        request.setAttribute("tiempoInicio",tiempoInicio);
        return null;
    }
}
