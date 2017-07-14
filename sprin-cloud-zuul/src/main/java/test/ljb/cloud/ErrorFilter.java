package test.ljb.cloud;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 自定义过滤器 需要继承ZuulFilter 需要重写实现下面四个方法
 * @Description:TODO(这里用一句话描述这个类的作用) 
 * @author liujb
 * @date 2017年6月16日 上午9:24:54 
 *
 */
public class ErrorFilter extends ZuulFilter {

	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String parameter = request.getParameter("a");
        String parameter2 = request.getParameter("b");
        if(parameter.equals(parameter2)){
        	ctx.setSendZuulResponse(false);
        	ctx.setResponseStatusCode(400);
        }
        return null;
	}

	
	/**
	 * shouldFilter：返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	
	/**
	 * filterOrder：通过int值来定义过滤器的执行顺序
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	
	/**
	 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
		pre：可以在请求被路由之前调用
		routing：在路由请求时候被调用
		post：在routing和error过滤器之后被调用
		error：处理请求时发生错误时被调用
	 */
	@Override
	public String filterType() {
		return "error";
	}

}
