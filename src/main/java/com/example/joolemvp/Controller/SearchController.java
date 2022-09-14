package com.example.joolemvp.Controller;

import com.example.joolemvp.Entity.Product;
import com.example.joolemvp.Enums.ProductAccessories;
import com.example.joolemvp.Enums.ProductApplication;
import com.example.joolemvp.Enums.ProductMountingLocation;
import com.example.joolemvp.Enums.ProductUseType;
import com.example.joolemvp.service.ProductService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Search")
public class SearchController {
    @Autowired
    private ProductService productService;

    @GetMapping("/findByType")
    public ResponseEntity<?> findByType(@RequestParam(value = "useType",defaultValue = "industrial") ProductUseType useType,
                                        @RequestParam(value = "application",defaultValue = "indoor") ProductApplication application,
                                        @RequestParam(value = "mountingLocation",defaultValue = "freeStanding") ProductMountingLocation mountingLocation,
                                        @RequestParam(value = "accessories",defaultValue = "withoutLight") ProductAccessories accessories){
        List<Product> products = productService.findProductByUseTypeAndApplicationAndMountingLocationAndAccessories(useType,application,mountingLocation,accessories);
        if(products==null){
            return new ResponseEntity("No product match the filter conditions",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @GetMapping("/findByModelYear")
    public ResponseEntity<?> findByModelYear(@RequestParam(value = "from",required = false,defaultValue = "0") String from,
                                             @RequestParam(value = "to",required = false,defaultValue = "10000") String to){
        List<Product> products = productService.findByModelYearBetween(Integer.valueOf(from),Integer.valueOf(to));
        return new ResponseEntity(products,HttpStatus.OK);

    }

    @GetMapping("/findByAirflow")
    public ResponseEntity<?> findByAirflow (@RequestParam(value = "from",required = false,defaultValue = "0") String from,
                                             @RequestParam(value = "to",required = false,defaultValue = "10000") String to){
        List<Product> products = productService.findByAirflow(Integer.valueOf(from),Integer.valueOf(to));
        return new ResponseEntity(products,HttpStatus.OK);

    }

    @GetMapping("/findByMaxPower")
    public ResponseEntity<?> findByMaxPower (@RequestParam(value = "from",required = false,defaultValue = "0") String from,
                                            @RequestParam(value = "to",required = false,defaultValue = "100000") String to){
        List<Product> products = productService.findByPowerMax(Integer.valueOf(from),Integer.valueOf(to));
        return new ResponseEntity(products,HttpStatus.OK);

    }

}
