package com.rookies.assignment.serviceImpl;

import com.rookies.assignment.data.repository.IProductModelRepository;
import com.rookies.assignment.service.impl.ProductModelServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductModelServiceImplTest {

    @Mock
    IProductModelRepository repository;

    @InjectMocks
    ProductModelServiceImpl service;


    @Test
    void  changeStart_shouldThrowError_WhenStartNull(){
//        Validationex
//        service.
    }

}
