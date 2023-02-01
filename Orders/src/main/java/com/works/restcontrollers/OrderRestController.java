package com.works.restcontrollers;

import com.works.IFeigns.INews;
import com.works.IFeigns.IProduct;
import com.works.IFeigns.Idummy;
import com.works.props.JWTLogin;
import com.works.props.JWTUser;
import com.works.props.News;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderRestController {

    final IProduct iProduct;
    final HttpServletRequest req;
    final INews iNews;
    final Idummy idummy;

    @GetMapping("/order")
    public Object order() {
        String token = req.getHeader("token");
        System.out.println("order Token : " + token);
        return iProduct.single(token, 1l);
    }


    @GetMapping("/news")
    public News news() {
        String apiKey = "38a9e086f10b445faabb4461c4aa71f8";
        return iNews.news(apiKey);
    }

    @GetMapping("/jwt")
    public JWTLogin jwt() {
        JWTUser user = new JWTUser();
        user.setUsername("kminchelle");
        user.setPassword("0lelplR");
        return idummy.login(user);
    }


}
