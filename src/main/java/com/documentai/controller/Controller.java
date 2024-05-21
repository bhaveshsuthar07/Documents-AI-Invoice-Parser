package com.documentai.controller;

import com.documentai.entity.Entity;
import com.documentai.service.DocumentServiceImpl;
import com.fasterxml.jackson.databind.node.POJONode;
import com.google.cloud.documentai.v1.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@org.springframework.stereotype.Controller
@PropertySource("classpath:common.properties")
public class Controller {

    @Autowired
    private Environment env;

    @Autowired
    private DocumentServiceImpl documentService;

    @RequestMapping("/")
    public String Document(){

        return "document";
    }

    @RequestMapping(value = "/get_invoice",method = RequestMethod.POST)
    public String GetDocumentData(@RequestParam("doc")MultipartFile multipartFile, @ModelAttribute("entity") Entity entity, Model model)
            throws IOException, ExecutionException, InterruptedException, TimeoutException {

      byte[] byteImg =   multipartFile.getBytes();

        List<Document.Entity> response = documentService.DocumentParser(env.getProperty("PROJECT_ID"), env.getProperty("LOCATION"), env.getProperty("PROCESSOR_ID"), byteImg);

        model.addAttribute("response",response);

        return "response";
    }


}
