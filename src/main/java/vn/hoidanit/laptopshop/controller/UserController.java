package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {
    /**
     * đây có thể hiểu là tạo 属性 của class trong java core
     * Spring Frameworkでは、UserService と UserRepository という依存オブジェクトは、
     * コンストラクタにより注入（DI: Dependency Injection）されます。
     * Springは、これらのオブジェクトのインスタンスを自動的に作成し、
     * このコントローラに渡してくれます。このようにして、UserController 内で
     * userService や userRepository を利用できるようになります。
     */

    private final UserService userService;

    // dùng tool generate constructor cho nhanh
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("2@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("dao", "test");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User()); // newUser là tên trùng với modelAttribute của file create.jsp
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User user1) {
        System.out.println(" run here " + user1);
        this.userService.handleSaveUser(user1);
        return "hello";
    }

}
// @RestController
// public class UserController {
// // DI: dependency injection
// private UserService userService;

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("/")
// public String getHomePage() {
// return this.userService.handleHello();
// }
// }