package com.niqactivate.niqactivateapi.dto;

import com.niqactivate.niqactivateapi.utils.ShelfItem;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;


@Data
public class ShopperDTO {
    @NotNull(message = "Shopper ID is required")
    @Pattern(regexp = "^S-.*", message = "Invalid Shopper Id")
    private String shopperId;
    private List<ShelfItem> shelfItemList;
}
