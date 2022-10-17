package com.rookies.assignment.dto.request;

import com.rookies.assignment.dto.flat.CategoriesDtoFlat;
import com.rookies.assignment.dto.flat.ModelImageDtoFlat;
import com.rookies.assignment.dto.response.ProductResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class ModelRequestUpdateDto {
    private List<ProductResponseDto> listProduct;
    private List<ModelImageDtoFlat> listImages;
    private List<CategoriesDtoFlat> listCategories;
    private UUID    id;
    private String name;
    private BigDecimal priceRoot;
    private String description;
    private boolean status;
    private Timestamp timeCreate;
    private Timestamp timeUpdate;
}