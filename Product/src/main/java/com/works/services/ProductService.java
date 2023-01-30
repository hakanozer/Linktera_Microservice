package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;
    final DiscoveryClient discoveryClient;

    public ResponseEntity save(Product product) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, productRepository.save(product));
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public String loginControl() {
        List<ServiceInstance> ls = discoveryClient.getInstances("login");
        if ( ls != null && ls.size() > 0 ) {
            ServiceInstance instance = ls.get(0);
            String url = instance.getUri().toString();
            return url;
        }
        return "";
    }

}
