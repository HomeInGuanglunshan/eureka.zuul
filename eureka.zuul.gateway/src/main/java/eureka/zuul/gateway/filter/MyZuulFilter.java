package eureka.zuul.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

//@Component
public class MyZuulFilter extends ZuulFilter {

	@Autowired
	HttpServletRequest request;

	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		requestContext.addZuulRequestHeader("Cookie", "kk=gg");
		requestContext.setRequest(requestContext.getRequest());
//		System.out.println(requestContext.getRequest().getCookies()[0].getName());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
