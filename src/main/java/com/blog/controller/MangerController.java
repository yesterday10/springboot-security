package com.blog.controller;

import com.blog.dto.RestCode;
import com.blog.entity.Manager;
import com.blog.service.ManagerService;
import com.blog.service.RoleService;
import com.blog.util.RestUtil;
import com.blog.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.security.Security;
import java.util.Set;

@Controller
public class MangerController {

    private Logger logger = LoggerFactory.getLogger(MangerController.class);

    @Autowired
    private ManagerService managerService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("sys/adminList")
    public String managerlist(@PageableDefault(sort = { "createdTime" }, direction = Sort.Direction.DESC)
                                      Pageable pageable, Model model, @Valid Manager manager){


        Page<Manager> managerList = managerService.getManagerList(pageable, manager);
        model.addAttribute("managerList",managerList);
        model.addAttribute("manger",manager);
        return "manager/managerList";
    }

    @RequestMapping("/sys/adminView")
    public String adminView(Model model, @RequestParam("mangerId") String mangerId){
        Manager manager = managerService.findManagerById(mangerId);
        model.addAttribute("manager",manager);
        return "manager/managerDetail";
    }


    @RequestMapping("/sys/adminEdit/")
    public String adminEdit(Model model,@RequestParam("managerId") String managerId,Principal principal){
        Authentication authentication = (Authentication) principal;
        Manager manager = managerService.findManagerById(managerId);


        model.addAttribute("manager",manager);
        model.addAttribute("currentId",managerId);
        return "manager/managerEdit";

    }

    /**
     * 上传文件
     * @param file  文件456456
     * @return
     */
    @RequestMapping("/sys/upload")
    @ResponseBody
    public ModelMap uploadAvatar(@RequestParam("file") MultipartFile file){
        String path;
        try {
            logger.error("名字"+file.getOriginalFilename());
            path = UploadUtil.uploadFile(file, file.getName(), "upload/");
            System.out.println("名字"+file.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return RestUtil.Success(RestCode.FILE_UPLOAD_ERR);
        }
        return RestUtil.Success(path);
    }


}
