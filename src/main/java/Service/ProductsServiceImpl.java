package Service;

import domain.Product;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductService{

    List<Product> products = new ArrayList<>(Arrays.asList(

            new Product(1, "laptop", 799.99, 10),
            new Product(2, "Smartphone", 499.99, 25),
            new Product(3, "Tablet", 299.99, 15),
            new Product(4, "Smartwatch", 199.99, 30)
    ));

    @Override
    public List<Product> getProducts(){
        return products;
    }



}
