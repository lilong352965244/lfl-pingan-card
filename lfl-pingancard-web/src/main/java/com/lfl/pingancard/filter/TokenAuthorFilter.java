package com.lfl.pingancard.filter;

/**
 * @author: lifalong
 * @create: 2019-10-30 16:20
 * @description: token认证过滤器。拦截所有请求，除了 /boss/user/login
 **/
//@Component
//@WebFilter(urlPatterns = "/**", filterName = "tokenFilter") implements Filter
public class TokenAuthorFilter {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    private static final String[] excludePathPatterns = {"/boss/user/login"};
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
////        // 在请求处理之前进行调用（Controller方法调用之前）,返回true才会继续往下执行，返回false取消当前请求
////        boolean isFilter = false;
////        HttpServletRequest request = (HttpServletRequest) req;
////        HttpServletResponse response = (HttpServletResponse) resp;
////        // 不拦截登陆
////        String url = request.getRequestURI();
////        if (Arrays.asList(excludePathPatterns).contains(url)) {
////            chain.doFilter(request, response);
////            return;
////        }
////
////        String tokenCode = request.getHeader("T_TOKEN");
////        if (tokenCode != null && !"".equals(tokenCode)) {
////            // 查询未过期的
////            User user = this.userService.getUserInfoByToken(tokenCode);
////            if (user != null) {  //如果不为空，直接放行
////                System.out.printf("token=%s存在，允许放行\r\n", tokenCode);
////                this.userService.setRedisExpire(tokenCode);
////                //重新设置cookie有效时间
////                CookieUtils.setCookie(request, response, "T_TOKEN", tokenCode, 60 * 60 * 24 * 7);
////                chain.doFilter(request, response);
////                return;
////            }
////        }
////
////        response.setStatus(404);
////        System.out.printf("token=%s不存在或过期，请重新登录\r\n", tokenCode);
////
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
}
