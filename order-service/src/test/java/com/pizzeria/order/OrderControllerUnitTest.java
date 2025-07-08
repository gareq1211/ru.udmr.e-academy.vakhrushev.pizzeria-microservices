//package com.pizzeria.order;
//
//import com.pizzeria.order.dto.OrderStatusUpdateRequest;
//import com.pizzeria.order.repository.OrderRepository;
//import com.pizzeria.order.rest.OrderController;
//import com.pizzeria.order.service.OrderService;
//import com.pizzeria.shared.dto.OrderDto;
//import com.pizzeria.shared.entity.Order;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//class OrderControllerUnitTest {
//
//    private OrderService orderService;
//    private OrderRepository repository;
//    private OrderController controller;
//
//    @BeforeEach
//    void setUp() {
//        orderService = mock(OrderService.class);
//        repository = mock(OrderRepository.class);
//        controller = new OrderController(orderService, repository);
//    }
//
//    @Test
//    void createOrder_shouldReturnSavedOrderDto() {
//        Order order = new Order(null, 2L, 1L, "WAITING");
//        OrderDto expectedDto = new OrderDto(100L, "Иван", "Пепперони", "WAITING");
//
//        when(orderService.create(order)).thenReturn(expectedDto);
//
//        OrderDto result = controller.create(order);
//
//        assertThat(result).isEqualTo(expectedDto);
//        verify(orderService).create(order);
//    }
//
//    @Test
//    void getAllOrders_shouldReturnListOfOrderDtos() {
//        OrderDto dto = new OrderDto(1L, "Иван", "Пепперони", "WAITING");
//        when(orderService.getAll()).thenReturn(List.of(dto));
//
//        List<OrderDto> result = controller.getAll();
//
//        assertThat(result).hasSize(1);
//        assertThat(result.get(0).getClientName()).isEqualTo("Иван");
//        verify(orderService).getAll();
//    }
//
//    @Test
//    void updateOrderStatus_shouldReturnUpdatedDto() {
//        Long orderId = 1L;
//        OrderStatusUpdateRequest request = new OrderStatusUpdateRequest("READY");
//        OrderDto dto = new OrderDto(1L, "Иван", "Пепперони", "READY");
//
//        when(orderService.updateStatus(orderId, "READY")).thenReturn(dto);
//
//        OrderDto result = controller.updateStatus(orderId, request);
//
//        assertThat(result.getStatus()).isEqualTo("READY");
//        verify(orderService).updateStatus(orderId, "READY");
//    }
//
//    @Test
//    void getById_shouldReturnDto() {
//        Order order = new Order(5L, 2L, 1L, "READY");
//        OrderDto dto = new OrderDto(5L, "Ольга", "Маргарита", "READY");
//
//        when(repository.findById(5L)).thenReturn(java.util.Optional.of(order));
//        when(orderService.toOrderDto(order)).thenReturn(dto);
//
//        OrderDto result = controller.getById(5L);
//
//        assertThat(result).isEqualTo(dto);
//        verify(repository).findById(5L);
//        verify(orderService).toOrderDto(order);
//    }
//}
