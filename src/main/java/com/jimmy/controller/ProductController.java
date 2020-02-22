package com.jimmy.controller;

import com.jimmy.domain.OrderItem;
import com.jimmy.domain.Product;
import com.jimmy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Product> all = service.findAll();
        model.addAttribute("productList",all);
        return "list";
    }

    @RequestMapping("/addOrderItem")
    public void addOrderItem(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Integer num = Integer.parseInt(request.getParameter("num"));
        Integer pid = Integer.parseInt(request.getParameter("pid"));

        // 根据pid获取product对象
        Product product = service.findById(pid);

        // 判断
        if (product!=null){
            // 将OI装入session中的容器
            ArrayList<OrderItem> orderItems = null;
            orderItems = (ArrayList<OrderItem>) request.getSession().getAttribute("OrderItems");
            if (orderItems==null){
                orderItems = new ArrayList<OrderItem>();
            }
            boolean flag = false;
            // 如果list中已经有product就直接加num
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getProduct().getId()==pid){
                    orderItem.setNum(orderItem.getNum()+num);
                    flag=true;
                }
            }
            if (!flag) {
                // 新建OrderItem对象
                OrderItem item = new OrderItem();
                item.setNum(num);
                item.setProduct(product);
                orderItems.add(item);
            }
            request.getSession().setAttribute("OrderItems",orderItems);
            response.setContentType("text/html;charset=utf-8");
//            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{msg:添加成功}");
        }
//        response.sendRedirect(request.getContextPath()+"/product/findAll");
    }

    @RequestMapping("removeOrderItem")
    public String removeOrderItem(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String productName = request.getParameter("name");
        // 获取Item list
        HttpSession session = request.getSession();
        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("OrderItems");
        OrderItem todel = null;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProduct().getName().equals(productName)){
                todel = orderItem;
            }
        }
        orderItems.remove(todel);
        session.setAttribute("OrderItems",orderItems);
        return "cart";
    }


}
