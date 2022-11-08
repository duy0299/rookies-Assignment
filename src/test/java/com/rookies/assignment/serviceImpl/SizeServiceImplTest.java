package com.rookies.assignment.serviceImpl;

import com.rookies.assignment.data.entity.Size;
import com.rookies.assignment.data.repository.IProductModelRepository;
import com.rookies.assignment.data.repository.ISizeRepository;
import com.rookies.assignment.service.impl.ProductModelServiceImpl;
import com.rookies.assignment.service.impl.SizeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SizeServiceImplTest {
    @MockBean
    ISizeRepository repository;

    @Autowired
    SizeServiceImpl service;


    @BeforeEach
    void setupTest(){

    }

    @Test
    void whenGetAll_shouldReturnList() {
        // 1. create mock data
        List<Integer> mockSize = mock(List.class);

        when(mockSize.add(anyInt())).thenThrow(new Exception("Test "));

        mockSize.add(123);

        // 2. define behavior of Repository
//        when(repository.findAll()).thenReturn(mockBooks);
//
//        // 3. call service method
//        List<Size> actualBooks = service.getAll();
//
//        // 4. assert the result
//        assertThat(actualBooks.size()).isEqualTo(mockBooks.size());
//
//        // 4.1 ensure repository is called
//        verify(bookRepository).findAll();
    }
}
