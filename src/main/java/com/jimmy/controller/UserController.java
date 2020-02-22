package com.jimmy.controller;

import com.jimmy.dao.OrderDao;
import com.jimmy.dao.OrderItemDao;
import com.jimmy.domain.Order;
import com.jimmy.domain.OrderItem;
import com.jimmy.domain.User;
import com.jimmy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
// 将指定的values存入session中
@SessionAttributes({"user"})
public class UserController {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private UserService service;

    @RequestMapping("/login")
    public ModelAndView login(User user, String checkCode, HttpServletRequest request){
        System.out.println(user);
        System.out.println(checkCode);

        ModelAndView mv = new ModelAndView();
        // 验证码验证
        String TrueCode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        // 马上把code移除，防止下次登陆继续使用
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        if (!TrueCode.equalsIgnoreCase(checkCode)){
            mv.addObject("error_msg","验证码错误，请重试。");
            mv.setViewName("error");
            return mv;
        }
        // 进行用户名和密码检查
        User u = service.findUser(user);
        if (u==null){
            mv.addObject("error_msg","用户名或密码错误，请重试。");
            mv.setViewName("error");
        }else{
            mv.addObject("user",u);
            mv.setViewName("success");
        }
        return mv;
    }

    @RequestMapping("/userCart")
    public String userCart(){
        return "cart";
    }

    @RequestMapping("/createOrder")
    public String createOrder(HttpServletRequest request, HttpServletResponse response){
        // 获取用户创建订单
        User user = (User) request.getSession().getAttribute("user");
        Order order = new Order();
        order.setUser(user);
        orderDao.saveOrder(order);
        System.out.println("自增长id："+order.getId());

        // 获取 item list并将每一个item添加进库
        List<OrderItem> list = (List<OrderItem>) request.getSession().getAttribute("OrderItems");
        for (OrderItem item : list) {
            item.setOrder(order);
            orderItemDao.saveOrderItem(item);
        }

        list.clear();

        return "cart";
    }

}
