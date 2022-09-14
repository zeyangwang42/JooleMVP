package com.example.joolemvp.Controller;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.ProjectProduct;
import com.example.joolemvp.Entity.User;
import com.example.joolemvp.service.ProductService;
import com.example.joolemvp.service.ProjectProductService;
import com.example.joolemvp.service.ProjectService;
import com.example.joolemvp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/ProjectController")
public class ProjectController {
    @Autowired
    private ProjectService service;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Project> list= service.findAll();
        if(list == null){
            return new ResponseEntity<>("this table not inited", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam("projectId")Integer id) {
        Project project = service.findById(id);
        if(project == null){
            return new ResponseEntity<>("this Id is not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);

    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam("projectId") Integer projectId) {
        Project project = service.findById(projectId);
        //System.out.println(project);

        if(project == null){
            return new ResponseEntity<>("this project is not exist", HttpStatus.BAD_REQUEST);
        }
        User user = project.getUser();
        user.getProjects().remove(project);
        project.setUser(null);

        service.deleteById(projectId);
        return new ResponseEntity<>("Project deleted", HttpStatus.OK);
    }

    @PostMapping("/addProductToProject")
    public ResponseEntity<?>addProductToProject(@RequestParam("projectId") Integer projectId,
                                                @RequestParam("productId") Integer productId){
        Project project = service.findById(projectId);
        Product product = productService.findById(productId);
        if(project==null||product==null){
            return new ResponseEntity("product or project not exsit",HttpStatus.BAD_REQUEST);
        }

        projectProductService.addProductToProject(project,product);
        return new ResponseEntity(project.getProducts(),HttpStatus.OK);

    }
    @DeleteMapping("/removePorductFromProduct")
    public ResponseEntity<?> deleteById(@RequestParam("ProjectId") Integer projectId,
                                        @RequestParam("ProductId") Integer productId) {

        Project project = service.findById(projectId);
        Product product = productService.findById(productId);

        if(project == null || product == null){
            return new ResponseEntity<>("The project or product not exist",HttpStatus.BAD_REQUEST);
        }
        ProjectProduct projectProduct = projectProductService.findByProjectAndProduct(project,product);
        if(projectProduct==null){
            return new ResponseEntity<>("The relation between this project and product is not exist",HttpStatus.BAD_REQUEST);
        }
        product.getProjectProducts().remove(projectProduct);
        project.getProducts().remove(projectProduct);
        projectProductService.deleteById(projectProduct.getProject_product_id());
        return new ResponseEntity("The relation between this project and product deleted", HttpStatus.OK);
    }

    @GetMapping("/findRelationBetweenProjectAndProduct")
    public  ResponseEntity<?> findRelationBetweenProjectAndProduct(@RequestParam("ProjectId") Integer projectId,
                                                                   @RequestParam("ProductId") Integer productId){
        Project project = service.findById(projectId);
        Product product = productService.findById(productId);

        if(project == null || product == null){
            return new ResponseEntity<>("The project or product not exist",HttpStatus.BAD_REQUEST);
        }
        ProjectProduct projectProduct = projectProductService.findByProjectAndProduct(project,product);
        if(projectProduct==null){
            return new ResponseEntity<>("The relation between this project and product is not exist",HttpStatus.OK);
        }
        return new ResponseEntity(projectProduct, HttpStatus.OK);
    }

    @GetMapping("/ListAllProductInProject")
    public ResponseEntity<?> listAllRelationBetweenProductAndProject(){
        List<ProjectProduct> list =projectProductService.findAll();
        return new ResponseEntity(list,HttpStatus.OK);
    }
}
