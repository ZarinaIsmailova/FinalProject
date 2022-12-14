package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.enumm.Status;
import com.example.springsecurityapplication.models.*;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.repositories.OrderRepository;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.services.OrderService;
import com.example.springsecurityapplication.services.PersonService;
import com.example.springsecurityapplication.services.ProductService;
import com.example.springsecurityapplication.util.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;
    private final OrderService orderService;
    private final ProductValidator productValidator;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final PersonService personService;
    private final OrderRepository orderRepository;
    @Autowired
    public AdminController(OrderService orderService, ProductValidator productValidator, ProductService productService, CategoryRepository categoryRepository, PersonService personService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.productValidator = productValidator;
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.personService = personService;
        this.orderRepository = orderRepository;
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('')")
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('')")

    // ?????????? ???? ?????????????????????? ?????????????? ???????????????? ???????????????????????????? ?? ?????????????? ??????????????
    @GetMapping()
    public String admin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        String role = personDetails.getPerson().getRole();

        if(role.equals("ROLE_USER")){
            return "redirect:/index";
        }
        model.addAttribute("products", productService.getAllProduct());
        return "admin/admin";
    }

    // ?????????? ???? ?????????????????????? ?????????? ????????????????????
    @GetMapping("/product/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
      //  System.out.println(categoryRepository.findAll().size());
        return "product/addProduct";
    }

    // ?????????? ???? ???????????????????? ?????????????? ?? ?????????? ?? ?????????????? product
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one") MultipartFile file_one, @RequestParam("file_two") MultipartFile file_two, @RequestParam("file_three") MultipartFile file_three, @RequestParam("file_four") MultipartFile file_four, @RequestParam("file_five") MultipartFile file_five) throws IOException {

        productValidator.validate(product, bindingResult);
        if(bindingResult.hasErrors()){
            return "product/addProduct";
        }
        // ???????????????? ???? ?????????????? ??????????
        if(file_one != null){
            // ?????????????????????? ???? ???????????????????? ??????????
            File uploadDir = new File(uploadPath);
            // ???????? ???????????? ?????????????????????? ???? ???????? ???? ????????????????????
            if(!uploadDir.exists()){
                // ?????????????? ???????????? ??????????????????????
                uploadDir.mkdir();
            }
            // ?????????????? ???????????????????? ?????? ??????????
            // UUID ???????????????????????? ???????????????????? ?????????????????????????? ???????????????????? ??????????????????????????
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - ???????????????????????? ?????????? ?? ??????????
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            // ?????????????????? ???????? ???? ???????????????????? ????????
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // ???????????????? ???? ?????????????? ??????????
        if(file_two != null){
            // ?????????????????????? ???? ???????????????????? ??????????
            File uploadDir = new File(uploadPath);
            // ???????? ???????????? ?????????????????????? ???? ???????? ???? ????????????????????
            if(!uploadDir.exists()){
                // ?????????????? ???????????? ??????????????????????
                uploadDir.mkdir();
            }
            // ?????????????? ???????????????????? ?????? ??????????
            // UUID ???????????????????????? ???????????????????? ?????????????????????????? ???????????????????? ??????????????????????????
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - ???????????????????????? ?????????? ?? ??????????
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            // ?????????????????? ???????? ???? ???????????????????? ????????
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // ???????????????? ???? ?????????????? ??????????
        if(file_three != null){
            // ?????????????????????? ???? ???????????????????? ??????????
            File uploadDir = new File(uploadPath);
            // ???????? ???????????? ?????????????????????? ???? ???????? ???? ????????????????????
            if(!uploadDir.exists()){
                // ?????????????? ???????????? ??????????????????????
                uploadDir.mkdir();
            }
            // ?????????????? ???????????????????? ?????? ??????????
            // UUID ???????????????????????? ???????????????????? ?????????????????????????? ???????????????????? ??????????????????????????
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - ???????????????????????? ?????????? ?? ??????????
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            // ?????????????????? ???????? ???? ???????????????????? ????????
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // ???????????????? ???? ?????????????? ??????????
        if(file_four != null){
            // ?????????????????????? ???? ???????????????????? ??????????
            File uploadDir = new File(uploadPath);
            // ???????? ???????????? ?????????????????????? ???? ???????? ???? ????????????????????
            if(!uploadDir.exists()){
                // ?????????????? ???????????? ??????????????????????
                uploadDir.mkdir();
            }
            // ?????????????? ???????????????????? ?????? ??????????
            // UUID ???????????????????????? ???????????????????? ?????????????????????????? ???????????????????? ??????????????????????????
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - ???????????????????????? ?????????? ?? ??????????
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            // ?????????????????? ???????? ???? ???????????????????? ????????
            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // ???????????????? ???? ?????????????? ??????????
        if(file_five != null){
            // ?????????????????????? ???? ???????????????????? ??????????
            File uploadDir = new File(uploadPath);
            // ???????? ???????????? ?????????????????????? ???? ???????? ???? ????????????????????
            if(!uploadDir.exists()){
                // ?????????????? ???????????? ??????????????????????
                uploadDir.mkdir();
            }
            // ?????????????? ???????????????????? ?????? ??????????
            // UUID ???????????????????????? ???????????????????? ?????????????????????????? ???????????????????? ??????????????????????????
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - ???????????????????????? ?????????? ?? ??????????
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            // ?????????????????? ???????? ???? ???????????????????? ????????
            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        productService.saveProduct(product);
        return "redirect:/admin";
    }

    // ?????????? ???? ???????????????? ???????????? ???? id
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    // ?????????? ???? ?????????????????? ???????????? ???? id ?? ?????????????????????? ?????????????? ????????????????????????????
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("editProduct", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("editProduct") Product product, @PathVariable("id") int id){
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }

    // ?????????? ???????????????????? ???????????????? ?? ?????????????? ?????????????????????????? ?? ???????????? ???????????? ???????????????????????? ?? ????????????
    @GetMapping("/person")
    public String person(Model model){;
        model.addAttribute("person", personService.getAllPerson());
        return "person/person";
    }


    // ?????????? ???????????????????? ???????????????? ?? ?????????????????? ?????????????????????? ?? ????????????????????????
    @GetMapping("/person/info/{id}")
    public String infoPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.getPersonById(id));
        return "person/personInfo";
    }

    // ?????????? ???????????????????? ???????????????? ?? ???????????? ???????????????????????????? ???????????????????????? ?? ???????????????? ?? ???????????? ???????????? ???????????????????????????? ???????????????????????? ???? id
    @GetMapping("/person/edit/{id}")
    public String editPerson(@PathVariable("id")int id, Model model){
        model.addAttribute("editPerson", personService.getPersonById(id));
        return "person/editPerson";
    }

    // ?????????? ?????????????????? ???????????? ?? ?????????? ?? ?????????????????? ????????????????????????
    @PostMapping("/person/edit/{id}")
    public String editPerson(@ModelAttribute("editPerson") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "person/editPerson";
        }
        personService.updatePerson(id, person);
        return "redirect:/admin/person";
    }

    // ?????????? ???? ???????????????? ??????????????????????????
    @GetMapping("/person/delete/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personService.deletePerson(id);
        return "redirect:/admin/person";
    }

// ?????????? ???????????????????? ???????????????? ?? ?????????????? ??????????????
    @GetMapping("/order")
    public String orders(Model model){;
    model.addAttribute("order", orderService.getAllOrder());
    return "order/order";
    }

    // ?????????? ???????????????????? ???????????????? ?? ?????????????????? ?????????????????????? ?? ????????????
    @GetMapping("/order/info/{id}")
    public String infoOrders(@PathVariable("id") int id, Model model){
        model.addAttribute("order", orderService.getOrderById(id));
        return "order/orderInfo";
    }


    //   ?????????? ???????????????????? ???????????????? ?? ???????????? ???????????????????????????? ?????????? ?? ???????????????? ?? ???????????? ???????????? ???????????????????????????? ???????????? ???? id
    @GetMapping("/order/edit/{id}")
    public String editOrder(@PathVariable("id")int id, Model model){
        model.addAttribute("editOrder", orderService.getOrderById(id));
        return "order/editOrder";
    }
    //
    // ?????????? ?????????????????? ???????????? ?? ?????????? ?? ?????????????????? ????????????
    @PostMapping("/order/edit/{id}")
    public String changeStatus(@PathVariable("id") int id, @RequestParam("status") Status status){
        Order order_status=orderService.getOrderById(id);
        order_status.setStatus(status);
        orderService.updateOrderStatus(order_status);
        return "redirect:/admin/order";
    }

    // ?????????? ???? ???????????????? ????????????
    @GetMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable("id") int id){
        orderService.deleteOrder(id);
        return "redirect:/admin/order";
    }



    @PostMapping("admin/order/searching_order")
    public String searching_order(@RequestParam("search") String search, Model model) {
        model.addAttribute("search_order", orderRepository.findByNumberEndingWith(search));
        model.addAttribute("value_search", search);
        model.addAttribute("order", orderService.getAllOrder());
        return "/order/order";
    }

}
