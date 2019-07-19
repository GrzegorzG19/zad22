package pl.golan.zadanie22;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class ShopController {
    private ProductRepository productRepository;

    public ShopController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String shop(Model model) {
        Set<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "main";
    }

    @GetMapping("/lista")
    public String details(Model model) {
        Set<Product> products = productRepository.findAll();
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }

        if (products != null) {
            model.addAttribute("products", products);
            model.addAttribute("sum", sum);
            return "lista"; // -> /resources/templates/lista.html
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/tabela")
    public String table(Model model) {
        Set<Product> products = productRepository.findAll();
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }

        if (products != null) {
            model.addAttribute("products", products);
            model.addAttribute("sum", sum);
            return "tabela"; // -> /resources/templates/lista.html
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        double price2 = Double.parseDouble(price);

        if (StringUtils.isEmpty(name)) {
            return "redirect:/err.html";
        } else {
            Product user = new Product(name, price2);
            productRepository.add(user);
            return "redirect:/success.html";
        }
    }

}
