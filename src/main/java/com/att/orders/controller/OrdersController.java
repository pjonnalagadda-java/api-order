package com.att.orders.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.att.orders.model.Order;
import com.att.orders.postModel.AcceptShipDateRequestBody;
import com.att.orders.postModel.CancelRequestBody;
import com.att.orders.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author pjonnalagadda
 *
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "orders", description = "orders API")
public class OrdersController {

	@Autowired
	OrderService orderservice;

	@Operation(summary = "Find orders", description = "all orders", tags = { "orders" }, operationId = "getOrders")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping(path = "/orders")
	public @ResponseBody List<Order> getOrders() {
		return orderservice.buildOrders();
	}

	@Operation(summary = "Find Order by ID", description = "Returns a single order", tags = {
			"orders" }, operationId = "getOrderById")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "400", description = "invalid ID supplied") })
	@GetMapping(path = "/orders/{orderId}")
	public ResponseEntity<String> getOrderById(@PathVariable("orderId") Integer orderId) {
		Order order = orderservice.getOrderById(orderId);
		if (null == order) {
			return ResponseEntity.badRequest().body("invalid ID supplied");
		}
		return ResponseEntity.badRequest().body(order.toString());
	}

	@Operation(summary = "accept ship date change", description = "capture the client intent to accept ship date change", tags = {
			"orders" }, operationId = "acceptShipDate")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "400", description = "invalid input or order/item not found") })
	@PostMapping(path = "/orderactions/acceptShipDate", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> acceptShipDate(HttpServletRequest request,
			@RequestBody List<AcceptShipDateRequestBody> requestBody) {
		int orderId = Integer.valueOf(request.getParameter("orderId"));
		int itemId = Integer.valueOf(request.getParameter("itemId"));
		int response = orderservice.acceptShipDate(orderId, itemId, requestBody.get(0));
		if (response == 400) {
			return ResponseEntity.badRequest().body("Invalid Input or order/item not found");
		}
		if (response == 409) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Order/Item is not in the valid state to accept the date. "
							+ "This happens when the user already accepted the newEstimatedShipDate or the item is canceled.0000000");
		}
		return ResponseEntity.ok().body("successful operation");
	}

	@Operation(summary = "cancel Item", description = "cancel all the items in the order", tags = {
			"orders" }, operationId = "cancelItem")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "400", description = "invalid input or order/item not found") })
	@PostMapping(value = "/orderactions/cancelItem", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> cancelItem(HttpServletRequest request,
			@RequestBody List<CancelRequestBody> requestBody) {
		int orderId = Integer.valueOf(request.getParameter("orderId"));
		int itemId = Integer.valueOf(request.getParameter("itemId"));
		int response = orderservice.cancelItem(orderId, itemId, requestBody.get(0));
		if (response == 400) {
			return ResponseEntity.badRequest().body("Invalid Input or order/item not found");
		}
		if (response == 409) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Order/Item is not in the valid state to cancel. "
					+ "This happens when item is shipped or already canceled.");
		}
		return ResponseEntity.ok().body("successful operation");
	}

	@Operation(summary = "cancel order", description = "cancel all orders", tags = {
			"orders" }, operationId = "cancelOrder")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "400", description = "invalid input or order/item not found") })
	@PostMapping(value = "/orderactions/cancelOrder", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<String> cancelOrder(HttpServletRequest request,
			@RequestBody List<CancelRequestBody> requestBody) {
		int orderId = Integer.valueOf(request.getParameter("orderId"));
		int response = orderservice.cancelOrder(orderId, requestBody.get(0));
		if (response == 400) {
			return ResponseEntity.badRequest().body("Invalid Input or order/item not found");
		}
		if (response == 409) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Order or few Items are not in the valid state to cancel. "
							+ "This happens when item is shipped or already canceled.");
		}
		return ResponseEntity.ok().body("successful operation");
	}

}
