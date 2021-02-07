package com.att.orders.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.att.orders.model.SKU;
import com.att.orders.service.CatalogSKUService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "catalog", description = "catalog API")
public class CatalogController {

	Logger logger = LoggerFactory.getLogger(CatalogController.class);
	
	@Autowired
	CatalogSKUService CatalogSKUService;
	
	@Operation(summary = "GET SKU", description = "Return the sku details",
			tags = { "catalog" },operationId = "getSKUByID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "returns either SKU or HardSKU based on the type of the SKU"),
        @ApiResponse(responseCode = "400", description = "invalid ID supplied")})	
	@GetMapping (value="/catalog/sku/{skuId}")
	public @ResponseBody SKU getSKUByID(@PathVariable("skuId") String skuId) {
		return CatalogSKUService.getCatalogSKU(skuId);
	}
}
