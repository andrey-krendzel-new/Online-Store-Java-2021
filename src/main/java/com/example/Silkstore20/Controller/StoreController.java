package com.example.Silkstore20.Controller;

import com.example.Silkstore20.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class StoreController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    //GET requests to / or /productlist. This is the main page

    @RequestMapping(value = {"/", "/productlist"}, method = RequestMethod.GET)
    public String productList(Model model) {
        // Fetch all products from product repository
        model.addAttribute("products", productRepository.findAll());

        // Gets current user's username. currentUsername is the attribute
        model.addAttribute("currentUsername", SecurityContextHolder.getContext().getAuthentication().getName());

        // Gets current user's object
        Usero currentUser = userRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );

        // If user object exists, gets id. currentId is the attribute
        if (currentUser != null) {
            model.addAttribute("currentId", currentUser.getId());
        }

        //Template: productlist.html
        return "productlist";
    }

    //GET requets to /userlist

    @RequestMapping(value = {"/userlist"}, method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userlist";
    }

    //Login page
    @RequestMapping(value="/login")
    public String login() {
        return "login";
    }

    // REST productlist
    @RequestMapping(value="/products", method = RequestMethod.GET)
    public @ResponseBody
    List<Product> productListRest() {
        return (List<Product>) productRepository.findAll();
    }

    // REST single product
    @RequestMapping(value="/products/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Product> findProductRest(@PathVariable("id") Long productId) {
        return productRepository.findById(productId);
    }

    // Add new product:

    @RequestMapping(value = "/add")
    public String addProduct(Model model){
        // Create new product object
        Product dummyProduct = new Product();
        // Set the seller to be the User that is currently logged in (find User object whose name matches current logged in name)
        dummyProduct.setSeller(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("product", dummyProduct);
        model.addAttribute("categories", categoryRepository.findAll());

        // addProduct.html
        return "addProduct";
    }

    // Save form
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Product product) throws IOException {
        // Sets the seller to be the User that is currently logged in, again. Just in case to prevent future errors.
        product.setSeller(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

      //  String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());


       Product savedProduct = productRepository.save(product);

   //    String uploadDir = "product-photos/" + savedProduct.getId();

   //    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
   //    System.out.println("fileName: " + fileName + "product imageurl: " + product.getImageUrl() + "upload dir: " + uploadDir);

        // Saves product to repository
        //productRepository.save(product);
        // Redirect to main page
        return "redirect:productlist";
    }

    // Deposit money form
    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String deposit(Usero usero) {


        userRepository.save(usero);

        // Redirect to main page
        return "redirect:view/" + usero.getId();
    }

    // Delete product

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") Long productId, Model model) {
        // Select the product by id
        Product specificProduct = productRepository.findById(productId).orElse(null);
        // Fet seller username from the selected product
        String sellerName = specificProduct.getSellerUsername();
        if (sellerName.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            productRepository.deleteById(productId);
        }
        System.out.println(sellerName + SecurityContextHolder.getContext().getAuthentication().getName());
        return "redirect:../productlist";
    }

    // Edit product

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable("id") Long id, Model model) {

        model.addAttribute("product", productRepository.findById(id));
        model.addAttribute("categories", categoryRepository.findAll());

        // editProduct.html
        return "editProduct";
    }

    // View PROFILE

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewProfile(@PathVariable("id") Long id, Model model) {
        // Find user by  id in the URL
        // Using lambda function, different from orElse(null) that I would normally use
        userRepository.findById(id).ifPresent(o -> model.addAttribute("usero", o));
        // Determine current user (object)
        Usero currentUser = userRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );
        // Get current user id, send it to view
        if (currentUser != null) {
            model.addAttribute("currentId", currentUser.getId());
            model.addAttribute("usero", currentUser);
        }

        // profile.html
        return "profile";
    }


}
