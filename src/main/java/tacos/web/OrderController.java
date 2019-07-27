package tacos.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import tacos.Order;
import tacos.Taco;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;
    private TacoRepository tacoRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo, TacoRepository tacoRepo) {
        this.orderRepo = orderRepo;
        this.tacoRepo = tacoRepo;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) return "orderForm";

        for (Taco taco : order.getTacos()) {
            taco = tacoRepo.save(taco);
        }
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}