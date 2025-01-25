package org.banking.web_ui.controllers.adminControllers;

import org.banking.core.request.user.AddUserRequest;
import org.banking.core.response.user.AddUserResponse;
import org.banking.core.services.user.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddUserController {

    @Autowired
    private AddUserService addUserService;

    @GetMapping(value = "/addUser")
    public String showAddUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddUserRequest());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String processAddUserRequest(@ModelAttribute(value = "request") AddUserRequest request,
                                        ModelMap modelMap) {
        AddUserResponse response = addUserService.execute(request);
        if (response.getErrorList().isEmpty()) {
            return "addUserSuccess";
        } else {
            modelMap.addAttribute("errors", response.getErrors());
            return "addUser";
        }
    }
}
