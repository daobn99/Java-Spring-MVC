package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    // user table list
    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        // System.out.println(">>> check user: " + users);
        model.addAttribute("users1", users);
        return "admin/user/table-users";
    }

    // display user detail
    @RequestMapping("/admin/user/{id}") // id đặt tên thế nào cũng được
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user); // "user" này chính là cái tên biến dùng để ${user.} bên file jsp
        model.addAttribute("id", id); // "id" này tương tự "user" phía trên
        return "admin/user/userDetail";
    }

    // create new user
    @RequestMapping("/admin/user/create") // GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User()); // newUser là tên trùng với modelAttribute của file create.jsp
        return "admin/user/create";
    }

    // userCreateを保存する
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User userCreate) {
        this.userService.handleSaveUser(userCreate);
        return "redirect:/admin/user";
    }

    // ユーザーの情報を作成フォームに表示させる
    @RequestMapping("/admin/user/update/{id}") // GET
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    // Updateしたユーザーの情報を保存する
    @PostMapping("/admin/user/update") // dùng method = RequestMethod.POST cũng được
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User userUpdate) {
        User currentUser = this.userService.getUserById(userUpdate.getId());
        if (currentUser != null) {
            currentUser.setAddress(userUpdate.getAddress());
            currentUser.setFullName(userUpdate.getFullName());
            currentUser.setPhone(userUpdate.getPhone());
            this.userService.handleSaveUser(userUpdate);
        }
        return "redirect:/admin/user";
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