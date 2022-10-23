package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.ProductModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.ProductModel} entity
 */
@Data
@NoArgsConstructor
public class ProductModelDtoFlat{
    private  UUID id;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private String name;
    @NotNull
    @NotEmpty
    @Min(value = 0)
    private BigDecimal priceRoot;
    @NotNull
    @NotEmpty
    private String description;
    private  boolean status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    public ProductModelDtoFlat(ProductModel model) {
        id = model.getId();
        name = model.getName();
        priceRoot = model.getPriceRoot();
        description = model.getDescription();
        status = model.isStatus();
        timeCreate = model.getTimeCreate();
        timeUpdate = model.getTimeUpdate();
    }

    public ProductModel changeToProductModelFlat() {
        ProductModel model = new ProductModel();
        model.setId(id);
        model.setStatus(status);
        model.setName(name);
        model.setPriceRoot(priceRoot);
        model.setDescription(description);
        model.setTimeCreate(timeCreate);
        model.setTimeUpdate(timeUpdate);
        return model;
    }
}