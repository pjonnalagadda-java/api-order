package com.att.orders.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.att.orders.ApiOrdersApplication;
import com.att.orders.model.Catalog;
import com.att.orders.model.SKU;

@Component
public class CatalogSKUService {

	Logger logger = LoggerFactory.getLogger(CatalogSKUService.class);

	public SKU getCatalogSKU(String skuId) {
		logger.info(String.format("Into the getCatalogSKU to identify sku for %s",skuId));
		Catalog catalog = ApiOrdersApplication.attOrdersDataStore.attCatalogData;
		SKU sku = catalog.getSkus().stream().filter(catSku -> catSku.getId().equals(skuId)).findAny().orElse(null);
		return sku;
	}
}
