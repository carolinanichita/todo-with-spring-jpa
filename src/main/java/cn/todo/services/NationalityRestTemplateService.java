package cn.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NationalityRestTemplateService {

    private RestTemplate restTemplate;

    @Autowired
    public NationalityRestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String predictNationality(String name) {
        return restTemplate.getForObject("https://api.nationalize.io?name={name}", String.class, name);
    }

}

