package org.banking.web_ui.controllers.adminControllers.user_controllers;

import org.banking.core.request.user.GetAllUsersRequest;
import org.banking.core.response.user.GetAllUsersResponse;
import org.banking.core.services.user.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllUsersController {

    @Autowired
    private GetAllUsersService service;


    @GetMapping(value = "/getAllUsers")
    public String showAllUsers(ModelMap modelMap) {
        GetAllUsersResponse response = service.execute(new GetAllUsersRequest());

        modelMap.addAttribute("users", response.getUserDTOS());

        return "getAllUsers";
    }
}