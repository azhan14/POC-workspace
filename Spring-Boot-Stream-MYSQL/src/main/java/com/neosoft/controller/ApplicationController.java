package com.neosoft.controller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.entity.Order;
import com.neosoft.entity.Product;
import com.neosoft.repository.CustomerRepository;
import com.neosoft.repository.OrderRepository;
import com.neosoft.repository.ProductRepository;

@RestController
public class ApplicationController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/product/book/price/{price}")
	public List<Product> getProductByBooksPrice(@PathVariable int price) {
		
		List<Product> result = productRepository.findAll().
				stream()
				.filter(p -> p.getCategory().equalsIgnoreCase("Books"))
				.filter(p -> p.getPrice() > price)
				.collect(Collectors.toList());
		
		return result;
	}
	
	@GetMapping("/order/products/category/{category}")
	public List<Order> getOrderByProductsCategory(@PathVariable String category){
		
		List<Order> result = orderRepository.findAll()
				.stream()
				.filter(o -> o.getProducts()
						.stream()
						.anyMatch(p -> p.getCategory().equalsIgnoreCase(category)))
				.collect(Collectors.toList());
		
		return result;
	}
	
	@GetMapping("/product/discount/category/{category}")
	public List<Product> getProductsWithCategoryandDiscount(@PathVariable String category){
		
		List<Product> result = productRepository.findAll()
				.stream()
				.filter(p -> p.getCategory().equalsIgnoreCase(category))
				.map(p -> p.withPrice(p.getPrice() * 0.9))
				.collect(Collectors.toList());
		
		return result;
	}
	
	@GetMapping("/product/customer/tier/{tier}")
	public List<Product> getProductByCustomersTier(@PathVariable int tier){
		
		List<Product> result = orderRepository.findAll()
				.stream()
				.filter(o -> o.getCustomer().getTier() == tier)
				.filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
				.filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
				.flatMap(o -> o.getProducts().stream())
				.distinct()
				.collect(Collectors.toList());
		
		return result;

	}
	
	@GetMapping("/product/cheapest/category/{category}")
	public Optional<Product> getCheapestProductFromCategory(@PathVariable String category){
		
		Optional<Product> result = productRepository.findAll()
				.stream()
				.filter(p -> p.getCategory().equalsIgnoreCase(category))
				.sorted(Comparator.comparing(Product::getPrice))
				.findFirst();
		
		return result;
	}

	
	@GetMapping("/order/recent/{limit}")
	public List<Order> getRecentOrderByLimit(@PathVariable int limit){
		
		List<Order> result = orderRepository.findAll()
				.stream()
				.sorted(Comparator.comparing(Order::getOrderDate).reversed())
				.limit(limit)
				.collect(Collectors.toList());
		
		return result;
	}
	
	@GetMapping("/order/total")
	public Double getTotalOfOrders() {
		
		Double result = orderRepository.findAll()
				.stream()
				.filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
				.filter(o -> o.getOrderDate().compareTo(LocalDate.of(2021, 3, 1)) <= 0)
				.flatMap(o -> o.getProducts().stream())
				.mapToDouble(p -> p.getPrice())
				.sum();
		
		return result;
	}
	
	@GetMapping("/order/average")
	public Double getAverageOfOrder() {
		
		Double result = orderRepository.findAll()
				.stream()
				.filter(o -> o.getOrderDate().isEqual(LocalDate.of(2021, 3, 15)))
				.flatMap(o -> o.getProducts().stream())
				.mapToDouble(p -> p.getPrice())
				.average().getAsDouble();
		
		return result;
	}
	
	@GetMapping("/product/statistic/category/{category}")
	public String getStatisticOfCategory(@PathVariable String category) {
		
		DoubleSummaryStatistics statistics = productRepository.findAll()
				.stream()
				.filter(p -> p.getCategory().equalsIgnoreCase(category))
				.mapToDouble(p -> p.getPrice())
				.summaryStatistics();
		
		String result = "count = "+statistics.getCount()+" average = "+statistics.getAverage()+
				" max = "+statistics.getMax()+" min = "+statistics.getMin()+
				" sum = "+statistics.getSum();
		
		return result;
	}
	
}
